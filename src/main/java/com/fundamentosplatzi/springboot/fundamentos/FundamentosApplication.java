package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner { /* Implementa la interfaz CommandLineRunner*/

	private ComponentDependency componentDependency; /*inyectar la dependencia con spring boot */
	@Autowired /* ya no es obligatorio en versiones recientes */
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency) {  /*constructor recibe la dependencia para poderla inyectar, y el nombre de la dependencia es componentDependency */
		/*@Qualifier permite elegir la dependencia que queremos inyectaran, debido a que hay 2 dependecias, la anterior y la actual" en este caso es ComponenetTwoImplement*/
		this.componentDependency = componentDependency;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	/*Utilizacion de la dependencia como implementacion dentro de otro objeto o clase*/
	@Override
	public void run(String... args) throws Exception { /*Este metodo ejecuta todo lo que querramos mostrar */
		componentDependency.saludar();
	}
}
