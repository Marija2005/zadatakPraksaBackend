package com.back.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.back.dto.KorisnikPromenaLozinkeDto;
import com.back.enumeration.UserRole;
import com.back.model.Users;
import com.back.repository.UserRepository;
import com.back.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Users> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users save(Users user) {
        user.setRole(UserRole.VIEWER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findbyEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto) {
        Optional<Users> rezultat = userRepository.findById(id);

        if(!rezultat.isPresent()) {
            throw new EntityNotFoundException();
        }

        Users korisnik = rezultat.get();

        if(!korisnik.getEmail().equals(korisnikPromenaLozinkeDto.getKorisnickoIme())
                || !korisnik.getPassword().equals(korisnikPromenaLozinkeDto.getLozinka())){
            return false;
        }

        // dodatak za zadatak 2
        String password = korisnikPromenaLozinkeDto.getLozinka();
        if (!korisnikPromenaLozinkeDto.getLozinka().equals("")) {
            password = passwordEncoder.encode(korisnikPromenaLozinkeDto.getLozinka());
        }

        korisnik.setPassword(password);

        userRepository.save(korisnik);

        return true;
    }
}
