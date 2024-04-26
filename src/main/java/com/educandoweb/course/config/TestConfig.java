package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration // Indicar ao Spring que é uma classe de configuração
@Profile("test") // Indicar ao Spring que é uma classe de configuração específica, de nome
					// "test", que é igual ao application-test.properties
public class TestConfig implements CommandLineRunner {
	/*
	 * Para ser utilizada, por enquanto como database seeding, que é para popular o
	 * banco de dados com alguns objetos.
	 */

	@Autowired // Anotation para que seja feito injeção de dependencia atráves do spring
	private UserRepository userRepository;
	
	@Autowired 
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * tudo que for colocado dentro desse método, será executado assim que a
		 * aplicação for iniciada
		 * 
		 */
	

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.CANCELED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.SHIPPED ,u1);

		/*
		 * Para salvar no banco de dados: userRepository = objeto que acessa os dados
		 * .saveAll() = vai ser passada uma lista de objetos que serão salvas no banco
		 * de dados arrays.asList()= criar a lista
		 */

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));

	}

}

/*
 * Injeção de dependencia = Quando um serviço depende de outro, essa dependencia
 * tem que ser fraca, desaclopada. Para que a instancia do userRepository possa
 * ser associada a classe TestConfig.
 */
