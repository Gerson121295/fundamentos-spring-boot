package com.fundamentosplatzi.springboot.fundamentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post")  //nombre de la tabla
public class Post {
    @Id  //Obligado xq representa la entidad a nivel de la tabbla de la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que se cree un id unico

    @Column(name="id_post", nullable = false, unique = true)  //nombre de la columna, nullable = false()(inpide que se inserten datos  nulos), unique=true(valor unico=true)
    private Long id; //representar la columna de la BD  como una propiedad a nivel de clase

    @Column(name = "description", length = 255)
    private String description;

    //Relacion con la entidad User
    @ManyToOne // Muchos Post tienen un usuario y un usuario puede tener muchos post
    private User user;

    //Creacion de Constructor vacio
    public Post() {
    }

    //Creacion de Constructor con parametros
    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }


    //Agregar los metodos Getters and Setters: clic en generate -> getters and Setter -> seleccionar todos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //Generacion del toString
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}

