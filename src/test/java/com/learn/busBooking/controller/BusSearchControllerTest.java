package com.learn.busBooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.learn.busBooking.Dto.BusRouteDto;
import com.learn.busBooking.exception.BusNotFoundException;
import com.learn.busBooking.model.Bus;
import com.learn.busBooking.service.BusSearchService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)

public class BusSearchControllerTest {

	@InjectMocks

	BusSearchController busSearchController;

	@Mock

	BusSearchService busSearchService;

	@Test

	public void testFindBySourceAndDestinationAndDateForPostive() throws BusNotFoundException {

		List<Bus> buses = new ArrayList<Bus>();

		BusRouteDto busRouteDto = new BusRouteDto();

		busRouteDto.setSource("srikakulam");

		busRouteDto.setDestination("hyderabad");

		busRouteDto.setDepatureDate("2020-04-13");

		Mockito.when(busSearchService.findBySourceAndDestinationAndDepatureDate(busRouteDto)).thenReturn(buses);

		ResponseEntity<List<Bus>> bus1 = busSearchController.viewBuses(busRouteDto);

		Assert.assertNotNull(bus1);

		Assert.assertEquals(busRouteDto.getDestination(), "hyderabad");

	}

	@Test(expected = Exception.class)

	public void testFindBySourceAndDestinationAndDateForExce() throws BusNotFoundException {

		List<Bus> buses = new ArrayList<Bus>();

		BusRouteDto busRouteDto = new BusRouteDto();

		busRouteDto.setSource("srikakulam");

		busRouteDto.setDestination("hyderabad");

		busRouteDto.setDepatureDate("2020-04-13");

		Mockito.when(busSearchService.findBySourceAndDestinationAndDepatureDate(busRouteDto))
				.thenThrow(Exception.class);

		ResponseEntity<List<Bus>> bus1 = busSearchController.viewBuses(busRouteDto);

		Assert.assertNotNull(bus1);

		Assert.assertEquals(busRouteDto.getDestination(), "vizag");

	}

	@Test

	public void testFindBySourceAndDestinationAndDateForNegative() throws BusNotFoundException {

		List<Bus> buses = new ArrayList<Bus>();

		BusRouteDto busRouteDto = new BusRouteDto();

		busRouteDto.setSource("srikakulam");

		busRouteDto.setDestination("hyderabad");

		busRouteDto.setDepatureDate("2020-04-13");

		busRouteDto.setSource("hyderabad");

		busRouteDto.setDestination("chennai");

		busRouteDto.setDepatureDate("2020-04-13");

		Mockito.when(busSearchService.findBySourceAndDestinationAndDepatureDate(busRouteDto)).thenReturn(buses);

		ResponseEntity<List<Bus>> bus1 = busSearchController.viewBuses(busRouteDto);

		Assert.assertNotNull(bus1);

		Assert.assertEquals(busRouteDto.getDestination(), "chennai");

	}

}

