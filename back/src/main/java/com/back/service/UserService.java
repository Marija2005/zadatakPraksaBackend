package com.back.service;

import java.util.List;
import java.util.Optional;

import com.back.dto.KorisnikPromenaLozinkeDto;
import com.back.model.Users;


public interface UserService {
    Optional<Users> findOne(Long id);
    List<Users> findAll();
    Users save(Users user);
    void delete(Long id);
    Optional<Users> findbyEmail(String email);
    boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto);
}
