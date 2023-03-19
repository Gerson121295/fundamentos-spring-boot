package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner { /* Implementa la interfaz CommandLineRunner*/

	//Ejemplo de log: libreria apache-commons
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class); // se ele envia la clase en la que nos encontramos


	private ComponentDependency componentDependency; /*inyectar la dependencia con spring boot, y agrega el nombre componentDependency */
	private MyBean myBean; 	/*inyecto la dependecia*/
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo; //inyecta la clase UserPojo como dependencia

	//Inyectar el repositorio como dependencia
	private UserRepository userRepository;

	/*Por medio del constructor se agrega la interfaces*/
	@Autowired /* ya no es obligatorio en versiones recientes */
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {  /*constructor recibe la dependencia para poderla inyectar, y el nombre de la dependencia es componentDependency */
		/*@Qualifier permite elegir la dependencia que queremos inyectaran, debido a que hay 2 dependecias, la anterior y la actual" en este caso es ComponenetTwoImplementi*/
		this.componentDependency = componentDependency; //llamada de la propiedad y la igualo al parametro dado.
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo; //llamamos a la propiedad de la clase UserPojo con this y la igualamos al parameto del constructor
		this.userRepository = userRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//lamada para su implementacion.
	/*Utilizacion de la dependencia como implementacion dentro de otro objeto o clase*/
	@Override
	public void run(String... args) {  	//	throws Exception { /*Este metodo ejecuta todo lo que querramos mostrar */
		//ejemplosAnteriores();
		saveUsersInDataBase(); //llamada del metodo //luego ejecutar y en el servidor se muestran los log de sentencias sql
	}

	//Metodo para persistir nuestra informacion
	private  void saveUsersInDataBase(){
		User user1 = new User("John", "John@domain.com", LocalDate.of(21,03,20));
		User user2 = new User("Julie", "Julie@domain.com", LocalDate.of(20,07,10));
		User user3 = new User("user3", "user3@domain.com", LocalDate.of(18,03,12));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(22,04,20));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(21,06,03));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(20,05,18));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(20,02,10));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(22,03,2));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(21,8,12));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(20,02,15));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(18,01,21));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(19,8,22));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save); //por cada uno de los usuarios se hace un registro en la BD
	}

	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

	private void ejemplosAnteriores(){
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

