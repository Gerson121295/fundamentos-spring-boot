package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;

import java.util.List;

public interface GetUser { //retribuye(retorna la lista de los usuarios que estan en la BD

    List<User> getAll();

}
