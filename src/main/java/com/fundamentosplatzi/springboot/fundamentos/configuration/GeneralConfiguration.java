package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//uso de properties y valores
@Configuration
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    //llamar a un bean para usarlas
    @Bean
    public MyBeanWithProperties function(){ //MyBeanWithProperties es la interfaz
        return new MyBeanWithPropertiesImplement(name, apellido); // MyBeanWithPropertiesImplement es la implementacion es la clase que representa a ese bean
    }
}
