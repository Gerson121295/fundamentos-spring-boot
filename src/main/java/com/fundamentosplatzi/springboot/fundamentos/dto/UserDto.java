package com.fundamentosplatzi.springboot.fundamentos.dto;

import java.time.LocalDate;

public class UserDto {
    //representar los valore que queremos obtener de nuestra Query
    //DTO representa a nivel de sentencia de JPQL -- clase usada en UserRepository

    private Long id;
    private String name;
    private LocalDate birthDate;

    //constructor con sus propiedades

    public UserDto(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }


    //metodos Get y set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    //toString ///si se va a sobreescribir


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
