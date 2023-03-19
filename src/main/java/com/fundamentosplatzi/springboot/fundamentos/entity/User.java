package com.fundamentosplatzi.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id  //Obligado xq representa la entidad a nivel de la tabbla de la BD
    @GeneratedValue(strategy = GenerationType.AUTO) //para que se cree un id unico, .auto  //para que se autoincremente al agregar un user
    @Column(name = "id_user", nullable = false, unique = true)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    private LocalDate cumpleanios;


    //Relacion con la entidad post //un usuario tiene muchos post
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   //mapearlo con user que esta en la entidad post
    @JsonManagedReference //para que cuando  accedamos a este servicio a nivel rest no de un error relacionado con stackoverflow
    private List<Post> posts = new ArrayList<>();  //lista de los post llamada post y se inicializa con new ArrayList


    public User(){  }  //Creacion de un constructor vacio

    //constructor con propiedades de User no va el id, ni el constructor vacio.

    public User(String name, String email, LocalDate cumpleanios) {
        this.name = name;
        this.email = email;
        this.cumpleanios = cumpleanios;
    }


//    public User(String name, String email, LocalDate birthday) {
//        this.name = name;
//        this.email = email;
//        this.birthday = birthday;
//    }



//    //Getters and Setters: todas las propiedades de User: incluye id, y Post

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(LocalDate cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthDate(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    //Generacion de metodo ToString: todas las propiedades de User: incluye id, y Post

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cumpleanios=" + cumpleanios +
                ", posts=" + posts +
                '}';
    }


//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", birthday=" + birthday +
//                ", posts=" + posts +
//                '}';
//    }



}
