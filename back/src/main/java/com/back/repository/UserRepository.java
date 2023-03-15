package com.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findFirstByEmail(String email);
    //Boolean existsByEmail(String email);
}
