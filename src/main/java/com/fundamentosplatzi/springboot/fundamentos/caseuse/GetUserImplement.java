package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser { //implentar la interfaz GetUser e implementar sus metodos

    private UserService userService; //llamar a la dependencia UserService e inyectarla a partir del constuctor

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers(); //este metodo retribuye todos los usuarios
    }

    //luego configurar los casos de usos a nivel de inyeccion de dependencia- en configuration-CaseUseConfiguration
}
