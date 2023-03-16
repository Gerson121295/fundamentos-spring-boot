package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplement implements MyBean{  /*Se implementa la interfaz Mybean, clic derecho y clic implementar metodo */
    @Override
    public void print() {
        System.out.println("HOla desde my implementacion propia del bean");
    }


}
