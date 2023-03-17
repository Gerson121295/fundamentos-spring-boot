package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log  LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation; //llamado de la dependencia

    //crear un constructor: clic derecho, generate, constructor, ok
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }


    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWhitDependency");
        int numero=1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operation es :"+numero);
        System.out.println(myOperation.sum(numero)); //imprime llamado de la operacion, de la dependencia
        System.out.println("Hi desde la implementacion de un bean con dependencia");
    }
}
