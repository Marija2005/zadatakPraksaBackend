package com.back.support;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.back.dto.UserDTO;
import com.back.model.Users;


@Component
public class UserToUserDTO implements Converter<Users, UserDTO>{

    @Override
    public UserDTO convert(Users user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public List<UserDTO> convert(List<Users> users){
        List<UserDTO> userDTOs = new ArrayList<>();

        for(Users u : users) {
            UserDTO dto = convert(u);
            userDTOs.add(dto);
        }

        return userDTOs;
    }
}
