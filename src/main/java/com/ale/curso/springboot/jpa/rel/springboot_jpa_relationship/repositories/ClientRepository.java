package com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ale.curso.springboot.jpa.rel.springboot_jpa_relationship.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c JOIN FETCH c.addresses")
    Optional<Client> findOne(Long id);
}
