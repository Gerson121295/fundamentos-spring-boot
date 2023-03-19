package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//JpaRepostory tiene  metodos definidos presionar ctrl + clic para ver los metodos como getById delete etc.
public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository recibe 2 parametros 1ro la entidad a mapear y 2do. el tipo de dato de id de esa entidad


}

