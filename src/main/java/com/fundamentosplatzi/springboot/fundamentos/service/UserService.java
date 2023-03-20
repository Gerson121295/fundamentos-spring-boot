package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   private final Log LOG = LogFactory.getLog(UserService.class);   //Crear el log
    private UserRepository userRepository; //inyectar nuestra dependencia useRepository

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
   // Transactional permite hacer rollback de las transacciones que estemos realizando a nivel de BD.
    //Metodo para guardar informacion

    @Transactional   //sirve si existe un error en el insert hace un rollback de todas las transacciones que se realizaron en la BD
    //ejemplo de transaction: SI se hace un registro luego otro, pero en el 3er. registro hubo un error entonces se hace un rollback ningun registro anterior se agrego a la BD.
    public void saveTransactional(List<User> users){
    users.stream()  //por cada users hacer un Stream
            .peek(user -> LOG.info("Usuario Insertado: "+ user))  //peek para  mostrar en pantalla lo que vengo registrando
            //.forEach(user -> userRepository.save(user) );   //por cada usuario
            .forEach(userRepository::save);//otra forma de hacer la linea de codigo anterior
    }

    //Metodo para devolver una lista de usuarios.
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
