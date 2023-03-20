package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    //Uso de query methods con Or, and, OrderBy, Between, Sort
    List<User> findByNameLike(String name);  //Encontrar al usuario por  el nombre

    List<User> findByNameOrEmail(String name, String email); //or lista de user por nombre o email

    List<User> findBybirthDateBetween(LocalDate begin, LocalDate end); //retorna informacion por intervalo de fecha

    List<User> findByNameLikeOrderByIdDesc(String name); //Asc = ascendente: -Encontrar por el id del nombre y lo ordena descendente

    //Otra forma de hace consulta pero enviandole el parametro: agregando Containing
    List<User> findByNameContainingOrderByIdDesc(String name); //encuentra todos que inicien con el parametro enviado

    // 24- Uso de JPQL con named parameters - Query a nivel de JPQL con named Parameters- DTO, una clase de tipo Data transfer Object osea transferir objetos a nivel de la aplicacion
    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate )" +  //Sentencia JPQl usamos el DTO para representar los datos que bamos a retribuir de la base de datos, se pega el paquete de la clase UserDto(com.fundamentos....(UserDto()(la clase a user y un constructor con las propiedades de UserDto"(u.id,)" )
            "FROM User u  " +        //creacion de sentencia FROM y llamo a la entidad(User) y la representamos con una "u" esta traera los registros de la BD
            "where u.birthDate=:parametroFecha "+ //where u.birthDate=nameParameter(un nombre cualquiera con que se quiera representar este parametro
            "and u.email=:parametroEmail")
            Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date, //con @Param se hace que los Parametros anteriores se representan con las de la variable del metodo que estamos creando aca
                                                        @Param("parametroEmail") String email);   //<UserDto es nuestro DTO, getgetAllByBirthDateAndEmail(es el metodo), que recibe 2 parametros (date y email)
}

