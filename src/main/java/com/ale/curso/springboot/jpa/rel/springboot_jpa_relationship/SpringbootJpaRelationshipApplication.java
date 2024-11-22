package com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.entities.Address;
import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.entities.Client;
import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.entities.Invoice;
import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.repositories.ClientRepository;
import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		removeAddressFindById();
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Villa Victoria", 144);
			Address address2 = new Address("Pura Pura", 24);
			client.setAddresses(Arrays.asList(address1, address2));
			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println("Cliente con eliminación: ");

				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Alejandro", "Ticona");
		Address address1 = new Address("Villa Victoria", 144);
		Address address2 = new Address("Pura Pura", 24);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		clientRepository.save(client);
		System.out.println("=======================ELIMINAR=======================");
		System.out.println("Cliente sin modificaciones: ");
		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println("Cliente con eliminación: ");

			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("Villa Victoria", 144);
			Address address2 = new Address("Pura Pura", 24);
			client.setAddresses(Arrays.asList(address1, address2));
			clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Alejandro", "Ticona");
		Address address1 = new Address("Villa Victoria", 144);
		Address address2 = new Address("Pura Pura", 24);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);
		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> optionalClient = clientRepository.findById(1L);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

}
