package br.com.henriquepadua.javaspringvscodeexample;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaSpringVscodeExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringVscodeExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		 return args -> {

			String[] beansNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beansNames);
			for(String beanName : beansNames){
				System.out.println(beanName);
			}
		 };
	}
}