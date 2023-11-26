package com.loja.demo;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loja.demo.model.entity.categoria.Categoria;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.model.entity.pedido.Pedido;
import com.loja.demo.model.entity.produto.Produto;
import com.loja.demo.repository.categoria.CategoriaRepository;
import com.loja.demo.repository.cliente.ClienteRepository;
import com.loja.demo.repository.pedido.PedidoRepository;
import com.loja.demo.repository.produto.ProdutoRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
		CategoriaRepository catRep,
		ClienteRepository clRep,
		PedidoRepository pRepository,
		ProdutoRepository produtoRepository) {
		return (args) -> {
			catRep.save(new Categoria(null, "Banana"));
			catRep.save(new Categoria(null, "Maçã"));
			catRep.save(new Categoria(null, "Juliete"));

			clRep.save(new Cliente(null, "Daniel", "123123", "danielk872@gmail.com", 100.0));
			clRep.save(new Cliente(null, "Roberto", "223123", "roberto2@gmail.com", 20.0));

			Date data = new Date(20023, 12, 12);
			Pedido pedido = new Pedido(1, data, 12.5, null);
			pRepository.save(pedido);

			Produto produto = new Produto(null, "sabonete", 100, 12.0);
			Produto produto2 = new Produto(null, "Banana", 20, 5.0);
			Produto produto3 = new Produto(null, "juliete", 5, 50.5);

			produtoRepository.save(produto);
			produtoRepository.save(produto2);
			produtoRepository.save(produto3);
		};
	}
}
