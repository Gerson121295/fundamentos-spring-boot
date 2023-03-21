package com.fundamentosplatzi.springboot.fundamentos.controller;


import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//este archivo tiene relacion con paquete caseuse

@RestController //esta anotacion hereda de Controller y asu vez permite que todos los metodos creados aca se formateen con el formato JSON
@RequestMapping("/api/users")     //Ruta en la que va a ser consumida este servicio
//Esta clase tendra todos los servicios Rest que seran consumidos por un cliente
public class UserRestController {
//capas relacionada con los servicios a desplegar: create, get, delete, update

    //Inyeccion por dependencia
    private GetUser getUser; //inyectar por dependencia nuestro caso de uso
    private CreateUser createUser; //inyectar el caso de uso
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private UserRepository userRepository; //para construir un metodo para la paginacion

    //inyeccion por constructor
    public UserRestController(GetUser getUser, CreateUser createUser,  //inyeccion de dependencias como getUser por constructos
                              DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    //Metodos a implementar

    //Metodo Get es para traer a los usuarios    //url: http://localhost:8080/app/api/users/
    @GetMapping("/")    //donde va a ser consumido a nivel Http
    List<User> get(){ //metodo va a devolver todos los usuarios
        return getUser.getAll();  //llamar a la dependencia getUser
    }

    //Metodo Post para crear los usuarios
    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){ //RequestBody para tener un cuerpo de entrada cuando se consuma este servicio va a venir un cuerpo el cual estara en un variable User(entidad) y el nombre newUser
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED); //Create devuelve un 201 de creado el registro
    }

    //Metodo Delete
    @DeleteMapping("/{id}")  //recibe un id del usuario a eliminar
    ResponseEntity deleteUser(@PathVariable long id){ //se borrará por id
        deleteUser.remove(id); //llama al metodo de remove y le pasa el id de parametro
        return new ResponseEntity(HttpStatus.NO_CONTENT); //NO_CONTENT fue exitoso pero no tenemos un contenido de respuesta del servicio
    }

    @PutMapping("/{id}")  //recibe un id del usuario a eliminar
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){ //recibe como parametro un cuerpo y el id
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK); //llama al metodo update(en CaseUse) y se le envia 2 parametros el newUser y el id, devuelve una nueva instancia de ResponseEntity ok de 200 exitoso
    }

    //Metodo relacionado Para consumir todo sobre la Paginacion
    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size){     //2Parametros: 1ro. la pagina en la que nos encontramos, 2do.size

        return userRepository.findAll(PageRequest.of(page,size)).getContent();   // en of pasamos la Pagina, y (size)el peso que se va a mostrar en cada pagina, findAll retribuye el metodo getContent y este metodo retribuye la lista de los usuarios.

    }

}



