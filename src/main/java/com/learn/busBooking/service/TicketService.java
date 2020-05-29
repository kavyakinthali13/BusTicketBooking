package com.learn.busBooking.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.busBooking.Dto.TicketDto;
import com.learn.busBooking.exception.BusNotFoundException;
import com.learn.busBooking.exception.TicketsNotAvailableException;
import com.learn.busBooking.exception.UserNotFound;
import com.learn.busBooking.exception.UserNotLoggedIn;
import com.learn.busBooking.model.Bus;
import com.learn.busBooking.model.Ticket;
import com.learn.busBooking.model.User;
import com.learn.busBooking.repository.BusRepository;
import com.learn.busBooking.repository.TicketRepository;
import com.learn.busBooking.repository.UserRepository;

@Service
public class TicketService {
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BusRepository busRepository;
	
	public Ticket bookTicket(TicketDto ticket) throws BusNotFoundException,UserNotFound {
		
		User user = new User();
		user = userRepository.findById(ticket.userId).orElseThrow(()->new UserNotFound("user not found"));
		Bus bus = new Bus();
		bus = busRepository.findById(ticket.busId).orElseThrow(()->new BusNotFoundException());
		if(user.isActivated()) {
			
				if(bus.getAvailableTickets()>ticket.noOfTickets) {
					bus.setAvailableTickets(bus.getAvailableTickets()-ticket.noOfTickets);
					busRepository.save(bus);
					Ticket ticket1=new Ticket();
					ticket1.setUser(user);
					ticket1.setBus(bus);
					ticket1.setTicketNumber(ticket.noOfTickets);
					ticket1.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
					ticketRepository.save(ticket1);
					return ticket1;
				}
				else {
					throw new TicketsNotAvailableException();
				}
			
			
		}else {
			throw new UserNotLoggedIn();
		}
	}
	
}
	
