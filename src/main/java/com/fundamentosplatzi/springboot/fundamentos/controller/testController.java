package com.fundamentosplatzi.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {
//funcion para testear que el puerto y el path se desplega correctamente
    @RequestMapping("/") //acepta todas las solicitudes dentro de este metodo a nivel Http
    @ResponseBody //para responder un cuerpo a nivel de servicio

    //retorno
    public ResponseEntity <String> function(){
        return new ResponseEntity<>("hell888877", HttpStatus.OK);
    }

}
