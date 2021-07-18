package com.rafaelchaves.crud_web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelchaves.crud_web.model.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
