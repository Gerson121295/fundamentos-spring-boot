package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {

    //propiedades definidas en GeneralConfiguration
    private String name;
    private String apellido;

    //Constructor
    public MyBeanWithPropertiesImplement(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }


    @Override
    public String function() {
        return name +"-"+apellido;
    } //implementamos el bean MybeanWithProperties


}
