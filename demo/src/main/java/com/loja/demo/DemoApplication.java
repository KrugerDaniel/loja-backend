package com.loja.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loja.demo.model.Categoria;
import com.loja.demo.model.Cliente;
import com.loja.demo.repository.CategoriaRepository;
import com.loja.demo.repository.ClienteRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		CategoriaRepository catRep,
		ClienteRepository clRep) {
		return (args) -> {
			catRep.save(new Categoria(null, "Banana"));
			catRep.save(new Categoria(null, "Maçã"));
			catRep.save(new Categoria(null, "Juliete"));

			clRep.save(new Cliente(null, "Daniel", "123123", "danielk872@gmail.com", 100.0));
			clRep.save(new Cliente(null, "Roberto", "223123", "roberto2@gmail.com", 20.0));
		};
	}
}
