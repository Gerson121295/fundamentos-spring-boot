package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//uso de properties y valores
@Configuration
@EnableConfigurationProperties(UserPojo.class) //la clase UsePojo se va presentar como propiedades en nuestra app //habilitada la clase pojo ya la podemos inyectat
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


    // Configuracion Manual para la conexion a la base de datos
    //Cuando inyectemos la funcion DataSource ya podremos usar toda esa configuracion
    @Bean
    //Dentro de este metodo relaciona toda la configuracion a la BD
    public DataSource dataSource(){ //Llamamos DataSource y le nombramos dataSource
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); //llama al metodo de DataSourceBuilder.create();
        //La sig. configuration es la misma que se agregaria en application.properties si se hiciese la config en ese archivo
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build(); //Retornamos la implementacion
    }





}
