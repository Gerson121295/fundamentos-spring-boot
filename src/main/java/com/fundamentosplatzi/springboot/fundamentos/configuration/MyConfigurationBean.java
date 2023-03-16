package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  /*Para que springboot determine que tendremos una configuracion adicional de nuestros beans*/
public class MyConfigurationBean {
    @Bean
    /*Implementacion del Bean y devolvemos la interfaz*/
    public MyBean beanOperation(){
        return new MyBean2Implement(); //cambio de MyBeanImplement() Aqui se puede cambiar el componente para que muestre en fundamentosApplication(ya esta configurado) /*se agrega la palabra reservada new para instanciar esa implementacion*/
    }

    // otra implementacion del Bean- de la interfaz MyOperation
    @Bean
    /*Implementacion del Bean y devolvemos la interfaz*/
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement(); //cambio de MyBeanImplement() Aqui se puede cambiar el componente para que muestre en fundamentosApplication(ya esta configurado) /*se agrega la palabra reservada new para instanciar esa implementacion*/
    }

    @Bean
    /*Implementacion del Bean y devolvemos la interfaz*/
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){ //se pasa la interfaz
        return new MyBeanWithDependencyImplement(myOperation); //Esta trae parametro
    }

}
