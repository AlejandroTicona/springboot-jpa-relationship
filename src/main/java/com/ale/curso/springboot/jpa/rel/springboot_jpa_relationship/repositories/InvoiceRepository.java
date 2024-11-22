package com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
