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

    public User save(User newUser) {
        return userRepository.save(newUser); //con esto registramos en BD
    }

    public void delete(long id) {
        userRepository.delete(new User(id)); //Se pasa el id para eliminar un registro, pide crear este constructor en la entidad User ya que en el constructor de la entidad User no fue agreago el id
    }

    public User update(User newUser, Long id) { //setear los usuarios en la BD
        return
        userRepository.findById(id) //metodo findById se hace un map para obtener la informacion, y al final hacer el update, con save(user)
            .map(
                    user -> {
                        user.setEmail(newUser.getEmail());
                        user.setBirthDate(newUser.getBirthDate());
                        user.setName(newUser.getName());

                        return userRepository.save(user);  //guardar la entidad ya mapeada con los nuevos valores
                    }
            ).get();  //por que findById devuelve un Opcional y ese opcional es apartir de User, debemos retornar el metodo get()
    }
}
