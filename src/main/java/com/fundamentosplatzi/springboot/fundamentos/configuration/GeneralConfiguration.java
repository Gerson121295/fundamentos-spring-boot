package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


//uso de properties y valores
@Configuration
@PropertySource("classpath:connection.properties")//llamar a nuestras propiedades de properties para la conexion a la BD: pasar el nombre del archivo connection.properties
@EnableConfigurationProperties(UserPojo.class) //la clase UsePojo se va presentar como propiedades en nuestra app //habilitada la clase pojo ya la podemos inyectat
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;


    //llamar a los valores del archivo connection.properties para la conexion a la BD
    @Value("${jdbc.url}")  //valor
    private String jdbcUrl; //lo relaciono con la variable
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;




    //llamar a un bean para usarlas
    @Bean
    public MyBeanWithProperties function(){ //MyBeanWithProperties es la interfaz
        return new MyBeanWithPropertiesImplement(name, apellido); // MyBeanWithPropertiesImplement es la implementacion es la clase que representa a ese bean
    }

    //forma de conectar a la BD pasandole variables relacionadas con el archivo connection.properties
    // Configuracion Manual para la conexion a la base de datos
    //Cuando inyectemos la funcion DataSource ya podremos usar toda esa configuracion
    @Bean
    //Dentro de este metodo relaciona toda la configuracion a la BD
    public DataSource dataSource(){ //Llamamos DataSource y le nombramos dataSource
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); //llama al metodo de DataSourceBuilder.create();
        //La sig. configuration es la misma que se agregaria en application.properties si se hiciese la config en ese archivo
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build(); //Retornamos la implementacion
    }


/*
//Otra forma de realizar la configuracion la conexion a la base de datos.
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
*/



}
