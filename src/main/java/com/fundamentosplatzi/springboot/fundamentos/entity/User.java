package com.fundamentosplatzi.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(length = 50, unique = true) //unique sig. que el email sera unico si hay un email con el mismo se generará un error
    private String email;

    private LocalDate birthDate;

    //Relacion con la entidad post //un usuario tiene muchos post
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   //mapearlo con user que esta en la entidad post
    //@JsonManagedReference //para que cuando  accedamos a este servicio a nivel rest no de un error relacionado con stackoverflow
    //@JsonIgnore
    private List<Post> posts = new ArrayList<>();  //lista de los post llamada post y se inicializa con new ArrayList


    public User(){  }  //Creacion de un constructor vacio

    //constructor con propiedades de User no va el id, ni el constructor vacio.

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public User(Long id) { //requerido para eliminar por id- UserServices
        this.id = id;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    //Generacion de metodo ToString: todas las propiedades de User: incluye id, y Post

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", posts=" + posts +
                '}';
    }

}
