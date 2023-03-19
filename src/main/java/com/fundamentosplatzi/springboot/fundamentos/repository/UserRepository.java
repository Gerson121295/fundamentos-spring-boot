package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
//JpaRepostory tiene  metodos definidos presionar ctrl + clic para ver los metodos como getById delete etc.
public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository recibe 2 parametros 1ro la entidad a mapear y 2do. el tipo de dato de id de esa entidad

    //ejemplos de JPQL
    //Crear un metodo que devolvera un opcional

    @Query("Select u from User u Where u.email=?1")  //agregamos la consulta JPQL se trabaja con objetos. el obejto sera User y seleccionamos  u (es el apodo) from User(entidad) where u.email=?1(parametro que es el que se recibe en el metodo de la linea siguiente) se busco por email pudo haber sido por nombre, etc.
    Optional<User> findByUserEmail(String email);

    //ordenar a partir de un parametro que vamos a enviar
    @Query("Select u from User u where u.name like ?1%")  //buscar y ordenar a partir del nombre
    List<User> findAndSort(String name, Sort sort);   //1ro clase name, 2do. clase Sort


    //Crear un Query Method
    List<User> findByName(String name); //implementacion, retorna una lista y encuentra por el nombre(recibe el nombre)
    Optional<User> findByEmailAndName(String email, String name); //Retorna un solo usuario

}

