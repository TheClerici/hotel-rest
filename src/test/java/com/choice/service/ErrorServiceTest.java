package com.choice.service;

import com.choice.model.Hotel;
import com.choice.wsdl.ServiceStatus;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ErrorServiceTest {
    ErrorService errorService = new ErrorService();

    @Test
    public void checkHotelShouldThrowBecauseNoBody() {
        Hotel hotel = new Hotel(null, null, null);
        try {
            errorService.checkHotel(hotel);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Hotel has no body. Please fill!", e.getReason());
        }
    }

    @Test
    public void checkHotelShouldThrowBecauseNoName() {
        Hotel hotel = new Hotel(null, "Kino 404", 5);
        try {
            errorService.checkHotel(hotel);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Hotel has no Name. Please fill!", e.getReason());
        }
    }

    @Test
    public void checkHotelShouldThrowBecauseNoAddress() {
        Hotel hotel = new Hotel("Gandara", null, 5);
        try {
            errorService.checkHotel(hotel);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Hotel has no Address. Please fill!", e.getReason());
        }
    }

    @Test
    public void checkHotelShouldThrowBecauseNoRating() {
        Hotel hotel = new Hotel("Gandara", "Blvd Kino", null);
        try {
            errorService.checkHotel(hotel);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Hotel has no Rating. Please fill!", e.getReason());
        }
    }

    @Test
    public void checkHotelShouldThrowBecauseRatingIsLessThan0OrMoreThan5() {
        Hotel hotel = new Hotel("Gandara", "Blvd Kino", 9);
        try {
            errorService.checkHotel(hotel);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("Hotel Rating should be between 0 and 5", e.getReason());
        }
    }

    @Test
    public void statusShouldThrowBadRequest() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("BAD_REQUEST");
        serviceStatus.setMessage("New Bad Request");
        try {
            errorService.checkStatus(serviceStatus);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertEquals("New Bad Request", e.getReason());
        }
    }
    @Test
    public void statusShouldThrowNotFound() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("NOT_FOUND");
        serviceStatus.setMessage("New Not Found");
        try {
            errorService.checkStatus(serviceStatus);
            fail();
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            assertEquals("New Not Found", e.getReason());
        }
    }
}