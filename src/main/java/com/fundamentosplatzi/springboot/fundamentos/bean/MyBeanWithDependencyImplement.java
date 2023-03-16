package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation; //llamado de la dependencia

    //crear un constructor: clic derecho, generate, constructor, ok
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }


    @Override
    public void printWithDependency() {

        int numero=1;
        System.out.println(myOperation.sum(numero)); //imprime llamado de la operacion, de la dependencia
        System.out.println("Hi desde la implementacion de un bean con dependencia");
    }
}
