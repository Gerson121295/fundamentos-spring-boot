package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner { /* Implementa la interfaz CommandLineRunner*/

	//Ejemplo de log: libreria apache-commons
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class); // se ele envia la clase en la que nos encontramos


	private ComponentDependency componentDependency; /*inyectar la dependencia con spring boot, y agrega el nombre componentDependency */
	private MyBean myBean; 	/*inyecto la dependecia*/
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo; //inyecta la clase UserPojo como dependencia

	/*Por medio del constructor se agrega la interfaces*/
	@Autowired /* ya no es obligatorio en versiones recientes */
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {  /*constructor recibe la dependencia para poderla inyectar, y el nombre de la dependencia es componentDependency */
		/*@Qualifier permite elegir la dependencia que queremos inyectaran, debido a que hay 2 dependecias, la anterior y la actual" en este caso es ComponenetTwoImplementi*/
		this.componentDependency = componentDependency; //llamada de la propiedad y la igualo al parametro dado.
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo; //llamamos a la propiedad de la clase UserPojo con this y la igualamos al parameto del constructor
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
		System.out.println(myBeanWithProperties.function());//llamado de la funcion(devuelve nombre+apellido) implementada en esa propiedad
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword()+"-"+userPojo.getAge());

		//ejemplo de log
		try {
			int value = 10/0;		//Generamos un error al dividir
			LOGGER.info("Mi valor: " +value); // esto no se ejecutará porqur anterior hubo un error pasa al catch
		}catch (Exception e){
			LOGGER.error("Esto es un error por dividir en 0"+e.getMessage());  //llamado del log nivel de error
		}//en consola vemos los log.Crtl + F : y pegar el mensaje "Esto es un error por dividir en 0" para ver el error
	}
}
