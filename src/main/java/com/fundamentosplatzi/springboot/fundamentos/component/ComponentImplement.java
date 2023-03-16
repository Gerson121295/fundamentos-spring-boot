package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency { /*implementa la interfaz ComponenetDependency */

    @Override
    public void saludar() {
        System.out.println("hola mundo desde mi componente");
    }
}
