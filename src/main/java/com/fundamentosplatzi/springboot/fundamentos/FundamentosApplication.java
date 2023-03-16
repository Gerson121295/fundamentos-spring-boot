package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner { /* Implementa la interfaz CommandLineRunner*/

	private ComponentDependency componentDependency; /*inyectar la dependencia con spring boot, y agrega el nombre componentDependency */
	private MyBean myBean; 	/*inyecto la dependecia*/
	private MyBeanWithDependency myBeanWithDependency;

	/*Por medio del constructor se agrega la interfaces*/
	@Autowired /* ya no es obligatorio en versiones recientes */
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency) {  /*constructor recibe la dependencia para poderla inyectar, y el nombre de la dependencia es componentDependency */
		/*@Qualifier permite elegir la dependencia que queremos inyectaran, debido a que hay 2 dependecias, la anterior y la actual" en este caso es ComponenetTwoImplementi*/
		this.componentDependency = componentDependency; //llamada de la propiedad y la igualo al parametro dado.
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//lamada para su implementacion.
	/*Utilizacion de la dependencia como implementacion dentro de otro objeto o clase*/
	@Override
	public void run(String... args) throws Exception { /*Este metodo ejecuta todo lo que querramos mostrar */
		componentDependency.saludar();
		myBean.print();//llamada de la dependencia y su implementacion(es el metodo: print(); al dar crtl + clic izq, sobre esto nos lleva a la interfaz.
		myBeanWithDependency.printWithDependency(); //implementacion es la que esta en la clase

	}
}
