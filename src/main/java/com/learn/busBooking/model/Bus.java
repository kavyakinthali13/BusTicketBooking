package com.learn.busBooking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Bus {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	   
	    private String source;
	   
	    private String destination;
	  
	    private String departureDate;
	    
	    private String arrivalDate;
	    
	    private int availableTickets;
	    private int totalSeats;
	   
	    @OneToMany(mappedBy = "bus", cascade = CascadeType.REMOVE)
	    @JsonIgnore
	    private List<Ticket> tickets;
	   
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getDepartureDate() {
			return departureDate;
		}

		public void setDepartureDate(String departureDate) {
			this.departureDate = departureDate;
		}

		public String getArrivalDate() {
			return arrivalDate;
		}

		public void setArrivalDate(String arrivalDate) {
			this.arrivalDate = arrivalDate;
		}

		public List<Ticket> getTickets() {
			return tickets;
		}

		public void setTickets(List<Ticket> tickets) {
			this.tickets = tickets;
		}

		public int getAvailableTickets() {
			return availableTickets;
		}

		public void setAvailableTickets(int availableTickets) {
			this.availableTickets = availableTickets;
		}

		public int getTotalSeats() {
			return totalSeats;
		}

		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}
		
	    
}
