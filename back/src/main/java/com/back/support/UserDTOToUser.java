package com.back.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.UserDTO;
import com.back.model.Users;
import com.back.service.UserService;


@Component
public class UserDTOToUser implements Converter<UserDTO, Users> {

    @Autowired
    private UserService userService;

    @Override
    public Users convert(UserDTO userDTO) {
        Users user = null;
        if(userDTO.getId() != null) {
        	user = userService.findOne(userDTO.getId()).get();
        }

        if(user == null) {
        	user = new Users();
        }

        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

}
