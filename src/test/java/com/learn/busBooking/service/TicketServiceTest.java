package com.learn.busBooking.service;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.learn.busBooking.Dto.TicketDto;
import com.learn.busBooking.Dto.TicketResponseDto;
import com.learn.busBooking.exception.BusNotFoundException;
import com.learn.busBooking.exception.TicketsNotAvailableException;
import com.learn.busBooking.model.Bus;
import com.learn.busBooking.model.Ticket;
import com.learn.busBooking.model.User;
import com.learn.busBooking.repository.BusRepository;
import com.learn.busBooking.repository.TicketRepository;
import com.learn.busBooking.repository.UserRepository;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketServiceTest {
	@InjectMocks
	TicketService ticketService;
	
	@Mock
	TicketRepository ticketRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BusRepository busRepository;
	
	
	@Test(expected = NoSuchElementException.class)
	public void testBookTicketForException() throws BusNotFoundException  {
		TicketDto ticket1=new TicketDto();
		Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenThrow(NullPointerException.class);
		Ticket resTicket = ticketService.bookTicket(ticket1);
	}
	
	@Test
	public void testBookTicket(){
		TicketResponseDto ticketResponseDto = new TicketResponseDto();
		ticketResponseDto.setBusId(1);
		ticketResponseDto.setUserId(20);
		ticketResponseDto.setTicketNumber(6);
		ticketResponseDto.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
		TicketDto ticketDto = new TicketDto();
		ticketDto.setBusId(1);
		ticketDto.setUserId(20);
		ticketDto.setNoOfTickets(6);
		User user = new User();
		user.setId(20L);
		user.setFirstname("gsp");
		user.setLastname("chinnu");
		user.setEmail("hi@gm.cm");
		user.setActivated(true);
		user.setPhoneNumber("9876543210");
		userRepository.save(user);
		
		Optional<User> user1 = userRepository.findById(20L);
		Bus bus = new Bus();
		bus.setId(1L);
		bus.setSource("sklm");
		bus.setDestination("vizag");
		bus.setArrivalDate("2020-05-21");
		bus.setDepartureDate("2020-05-20");
		bus.setAvailableTickets(20);
		busRepository.save(bus);
		Optional<Bus> bus1 = busRepository.findById(1L);
		if(bus.getAvailableTickets()>ticketDto.noOfTickets) {
		
			bus.setAvailableTickets(bus.getAvailableTickets()-ticketDto.noOfTickets);
			busRepository.save(bus);
			Ticket ticket1=new Ticket();
			ticket1.setUser(user);
			ticket1.setBus(bus);
			ticket1.setTicketNumber(ticketDto.noOfTickets);
			ticket1.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
			Mockito.when(ticketRepository.save(ticket1)).thenReturn((ticket1));
			Assert.assertEquals(ticketResponseDto.getTicketNumber(), ticket1.getTicketNumber());
			
		}
	}
	@Test
	public void testBookTicketForExce(){
		TicketResponseDto ticketResponseDto = new TicketResponseDto();
		ticketResponseDto.setBusId(1);
		ticketResponseDto.setUserId(20);
		ticketResponseDto.setTicketNumber(6);
		ticketResponseDto.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
		TicketDto ticketDto = new TicketDto();
		ticketDto.setBusId(1);
		ticketDto.setUserId(20);
		ticketDto.setNoOfTickets(6);
		User user = new User();
		user.setId(20L);
		user.setFirstname("gsp");
		user.setLastname("chinnu");
		user.setEmail("hi@gm.cm");
		user.setActivated(true);
		user.setPhoneNumber("9876543210");
		userRepository.save(user);
		
		Optional<User> user1 = userRepository.findById(20L);
		Bus bus = new Bus();
		bus.setId(1L);
		bus.setSource("sklm");
		bus.setDestination("vizag");
		bus.setArrivalDate("2020-05-21");
		bus.setDepartureDate("2020-05-20");
		bus.setAvailableTickets(20);
		busRepository.save(bus);
		Optional<Bus> bus1 = busRepository.findById(1L);
		if(bus.getAvailableTickets()>ticketDto.noOfTickets) {
		
			bus.setAvailableTickets(bus.getAvailableTickets()-ticketDto.noOfTickets);
			busRepository.save(bus);
			Ticket ticket1=new Ticket();
			ticket1.setUser(user);
			ticket1.setBus(bus);
			ticket1.setTicketNumber(ticketDto.noOfTickets);
			ticket1.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
			Mockito.when(ticketRepository.save(Mockito.any())).thenThrow(TicketsNotAvailableException.class);
			Assert.assertNotNull(ticket1);
			
		}
	}
	

}
