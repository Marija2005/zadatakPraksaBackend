package com.back.contoller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.dto.AuthUserDTO;
import com.back.dto.UserDTO;
import com.back.dto.KorisnikPromenaLozinkeDto;
import com.back.dto.UserRegistrationDTO;
import com.back.model.Users;
import com.back.security.TokenUtils;
import com.back.service.UserService;
import com.back.support.UserDTOToUser;
import com.back.support.UserToUserDTO;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOToUser toUser;
    @Autowired
    private UserToUserDTO toUserDto;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserRegistrationDTO dto){
        if(dto.getId() != null || !dto.getPassword().equals(dto.getRepeatPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter za njega
        // ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
        Users user = toUser.convert(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);

        return new ResponseEntity<>(toUserDto.convert(userService.save(user)), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasAnyRole('VIEWER', 'EDITOR')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO){
        if(!id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Users user = toUser.convert(userDTO);

        return new ResponseEntity<>(toUserDto.convert(userService.save(user)),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<UserDTO> get(@PathVariable Long id){
        Optional<Users> user = userService.findOne(id);

        if(user.isPresent()) {
            return new ResponseEntity<>(toUserDto.convert(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@PreAuthorize("hasRole('VIEWER')")
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "promenaLozinke")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody KorisnikPromenaLozinkeDto dto){
        // ova metoda se "okida" kada se primi PUT /korisnici?promenaLozinke
        // pogrešno bi bilo mapirati na npr. PUT /korisnici/lozinke, pošto "lozinka" nije punopravan REST resurs!

        if(!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean rezultat;
        try {
            rezultat = userService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(rezultat) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @GetMapping(value = "/name") 
    @ResponseBody
    public ResponseEntity getUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	Object user = userService.findbyEmail(currentPrincipalName);
 
    	return ResponseEntity.ok(user);
    }

    //@PreAuthorize("hasRole('EDITOR')")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDTO dto) {
        // Perform the authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            // Reload user details so we can generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
            String email = userDetails.getUsername();
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
