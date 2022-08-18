package com.crud.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.api.model.Ticket;

@Repository
public interface TicketDao extends CrudRepository<Ticket, Integer>{

	Ticket findByCategory(String category);

}
