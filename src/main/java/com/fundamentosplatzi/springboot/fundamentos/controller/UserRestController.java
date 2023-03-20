package com.fundamentosplatzi.springboot.fundamentos.controller;


import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//este archivo tiene relacion con paquete caseuse

@RestController //esta anotacion hereda de Controller y asu vez permite que todos los metodos creados aca se formateen con el formato JSON
@RequestMapping("/api/users")     //Ruta en la que va a ser consumida este servicio
//Esta clase tendra todos los servicios Rest que seran consumidos por un cliente
public class UserRestController {
//capas relacionada con los servicios a desplegar: create, get, delete, update

    private GetUser getUser; //inyectar por dependencia nuestro caso de uso

    public UserRestController(GetUser getUser) { //inyeccion de getUser por constructos
        this.getUser = getUser;
    }

    //url: http://localhost:8080/app/api/users/
    @GetMapping("/")    //donde va a ser consumido a nivel Http
    List<User> get(){ //metodo va a devolver todos los usuarios
        return getUser.getAll();  //llamar a la dependencia getUser
    }
}



