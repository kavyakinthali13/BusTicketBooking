package com.learn.busBooking.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.busBooking.Dto.TicketDto;
import com.learn.busBooking.Dto.TicketResponseDto;
import com.learn.busBooking.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
		
	@PostMapping("/book")
	public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketDto ticket) {
		TicketResponseDto ticketResponseDto =new TicketResponseDto();
			ticketService.bookTicket(ticket);
			 ticketResponseDto.setBusId(ticket.getBusId());
			 ticketResponseDto.setUserId(ticket.getUserId());
			 ticketResponseDto.setReservationDate(Date.valueOf(java.time.LocalDate.now()));
			 ticketResponseDto.setTicketNumber(ticket.getNoOfTickets());
			ticketResponseDto.setMessage("tickets booked successfully");
			return new ResponseEntity<TicketResponseDto>(ticketResponseDto,HttpStatus.OK);
		
	}
	
}
