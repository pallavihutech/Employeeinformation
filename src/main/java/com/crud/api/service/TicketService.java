package com.crud.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.api.dao.TicketDao;
import com.crud.api.model.Ticket;

@Service
public class TicketService {
	@Autowired
	private TicketDao ticketDao;

	public String save(Ticket tickets) {
		ticketDao.save(tickets);
		return "Ticket saved";
	}

	public List<Ticket> findAll() {
		return (List<Ticket>) ticketDao.findAll();
	}

	public String deleteById(int id) {
		ticketDao.deleteById(id);
		return "ticket deleted";
	}

	public String savepvr(Ticket tck) {
		ticketDao.save(tck);
		return "saved";
	}

	public Optional<Ticket> findById(int id) {
		return ticketDao.findById(id);
	}
	
}
