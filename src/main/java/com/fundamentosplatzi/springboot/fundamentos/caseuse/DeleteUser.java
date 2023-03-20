package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component //anotacion cuando se haga algo muy general en este caso son casos de uso
public class DeleteUser {
    private UserService userService; //agregar la interfaz tipo service

    public DeleteUser(UserService userService) { //Inyectar la interfaz tipo service
        this.userService = userService;
    }

    public void remove(long id) {
        userService.delete(id); //llamada al servicio al metodo delete y le pasamos el id
    }
}
