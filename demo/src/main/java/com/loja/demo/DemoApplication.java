package com.loja.demo;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loja.demo.model.Categoria;
import com.loja.demo.model.Cliente;
import com.loja.demo.model.Pedido;
import com.loja.demo.repository.CategoriaRepository;
import com.loja.demo.repository.ClienteRepository;
import com.loja.demo.repository.PedidoRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		CategoriaRepository catRep,
		ClienteRepository clRep,
		PedidoRepository pRepository) {
		return (args) -> {
			catRep.save(new Categoria(null, "Banana"));
			catRep.save(new Categoria(null, "Maçã"));
			catRep.save(new Categoria(null, "Juliete"));

			clRep.save(new Cliente(null, "Daniel", "123123", "danielk872@gmail.com", 100.0));
			clRep.save(new Cliente(null, "Roberto", "223123", "roberto2@gmail.com", 20.0));

			Date data = new Date(20023, 12, 12);
			Pedido pedido = new Pedido(1, data, 12.5, null);
			pRepository.save(pedido);
		};
	}
}
