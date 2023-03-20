package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

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
	private UserService userService;



	/*Por medio del constructor se agrega la interfaces*/
	@Autowired /* ya no es obligatorio en versiones recientes */
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency,   //pasados como atributos
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {  /*constructor recibe la dependencia para poderla inyectar, y el nombre de la dependencia es componentDependency */
		/*@Qualifier permite elegir la dependencia que queremos inyectaran, debido a que hay 2 dependecias, la anterior y la actual" en este caso es ComponenetTwoImplementi*/
		this.componentDependency = componentDependency; //llamada de la propiedad y la igualo al parametro dado.
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo; //llamamos a la propiedad de la clase UserPojo con this y la igualamos al parameto del constructor
		this.userRepository = userRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//lamada para su implementacion.
	/*Utilizacion de la dependencia como implementacion dentro de otro objeto o clase*/
	@Override
	public void run(String... args) {  	//	throws Exception { /*Este metodo ejecuta todo lo que querramos mostrar */
		// ejemplosAnteriores();
		 saveUsersInDataBase(); //llamada del metodo //luego ejecutar y en el servidor se muestran los log de sentencias sql
		// getInformationJpqlFromUser();
		//saveWithErrorTransactional();
	}


	//Metodo para transactional C25
	private void saveWithErrorTransactional(){
		//instancias de las entidades
		//Ejemplo de que se genere un rollback en user se agrego unique a email el correo debe ser unico
		// si el correo se repite en el 3r registro(es igual al 1ro) se generará un rollaback ninguno de los registros insertados anteriores se guardará en la BD.

		User test1  = new User("TestTransactional1", "TestTransactional1@gmail.com", LocalDate.now()); //para agregar nuevos usuarios nombre, correo, fecha de hoy(now)
		User test2  = new User("TestTransactional2", "TestTransactional2@gmail.com", LocalDate.now());
		User test3  = new User("TestTransactional3", "TestTransactional1@gmail.com", LocalDate.now());
		User test4  = new User("TestTransactional4", "TestTransactional4@gmail.com", LocalDate.now());


		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una exception dentro del metodo transaccional " + e);
		}

		userService.getAllUsers().stream()  //todolos users con stream(lo muestra en pantalla)
		.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccion "+user));

	}



	//Metodo para JPQL
	private void getInformationJpqlFromUser() {



		LOGGER.info("Usuario con el metodo findByUserEmail " + userRepository.findByUserEmail("John@domain.com")    //llamar a la dependencia userRepositorio.finByEmail() - metodo escrito por nosotros
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario"))); //orElseThrow en caso q no lo encuentre, muestre el mensaje


		//con JPQL podemos crear una funcion para enviar parametros y ordenarlo de manera descendente o ascendente(ascending()
		//Muestra todos con los usuarios parecidos a user y los ordena descendente
		userRepository.findAndSort("user", Sort.by("id").descending()) //user(no se envia el name completo y un parametro de la clase sort para que se ordene a partir de una propiedad id de manera descendente
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		//22 -----Crear Query Methods
		//LLamada del Query Method que se creo en UserRepository
		userRepository.findByName("John")//trae todos los nombre con John
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method finByEmailAndName" + userRepository.findByEmailAndName("Julie@domain.com", "Julie")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));


		//23 Uso de query methods con Or, and, OrderBy, Between, Sort
		userRepository.findByNameLike("%J%") //busca usuarios que enpiezen  con user %user% o una "u" %u%  //Esto es como un like en SQL, seria un select where like
				.stream()
				.forEach(user ->LOGGER.info("Usuario findBYNameLike "+ user));

		userRepository.findByNameOrEmail("user5",null )//en el nombre no se envio, se dejo null, solo el correo se agrego, el usuariop puede ser buscado por nombre o correo
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail "+ user));

		userRepository.findBybirthDateBetween(LocalDate.of(2021,01,01), LocalDate.of(2022,05,01))  //retornar por intervalo de fecha
				.stream()  //debido a que es una lista
				.forEach(user -> LOGGER.info("Usuario con intervalo de fecha: "+user));


		userRepository.findByNameLikeOrderByIdDesc("%user%") //trae a los usuarios con nombre similar a user y los ordena descendente
		.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado descendente: "+user));


		userRepository.findByNameContainingOrderByIdDesc("user") //trae a los usuarios con nombre similar a user, esta vez no se asigno los % %
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado descendente: "+user));


		// 24- Uso de JPQL con named parameters - Query a nivel de JPQL con named Parameters

		LOGGER.info("El usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,03,20), "John@domain.com") //se envia de los parametros de nombre o email a buscar en la BD
				.orElseThrow(()->new RuntimeException("No se a encontrado elusuario a parti del named parameter")));



	}


	//Metodo para persistir nuestra informacion
	private  void saveUsersInDataBase(){
		User user1 = new User("John", "John@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("Julie", "Julie@domain.com", LocalDate.of(2020,07,10));
		User user3 = new User("Johny", "Johny@domain.com", LocalDate.of(2018,03,12));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2022,04,20));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,06,03));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2020,05,18));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2020,02,10));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2022,03,02));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,04,12));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2020,02,15));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2018,01,21));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2019,11,22));

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

