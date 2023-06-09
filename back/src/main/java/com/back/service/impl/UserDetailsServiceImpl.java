package com.back.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.back.model.Users;
import com.back.service.UserService;


@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  /* Zelimo da predstavimo korisnika preko UserDetails klase - nacina
  *  na koji Spring boot predstavlja korisnika. Ucitamo na osnovu korisnickog imena
  *  korisnika iz nase mysql baze i u okviru UserDetails namapiramo njegove podatke
  *  - kredencijale i rolu kroz GrantedAuthorities. */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = userService.findbyEmail(email).orElse(null);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
    } else {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // korisnik moze imati vise od jedne uloge te za svaku ulogu mogu biti definisana prava
        String role = "ROLE_" + user.getRole().toString();
        //String role = korisnik.getUloga().toString();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
        		user.getEmail().trim(),
        		user.getPassword().trim(),
                grantedAuthorities);
    }
  }
}
