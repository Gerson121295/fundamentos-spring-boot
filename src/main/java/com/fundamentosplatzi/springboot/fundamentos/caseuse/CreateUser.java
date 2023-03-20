package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService; //agregar la interfaz tipo service

    public CreateUser(UserService userService) { //Inyectar la interfaz tipo service
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
