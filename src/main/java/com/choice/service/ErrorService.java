package com.choice.service;

import com.choice.model.Hotel;
import com.choice.wsdl.ServiceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ErrorService {
    public void checkHotel(Hotel hotel) {
        if (hotel.getName() == null && hotel.getAddress() == null && hotel.getRating() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has no body. Please fill!");
        else if (hotel.getName() == null || hotel.getName().length() < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has no Name. Please fill!");
        else if (hotel.getAddress() == null || hotel.getAddress().length() < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has no Address. Please fill!");
        else if (hotel.getRating() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel has no Rating. Please fill!");

        if (hotel.getRating() < 0 || hotel.getRating() > 5)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel Rating should be between 0 and 5");
    }

    public void checkStatus(ServiceStatus serviceStatus){
        if (serviceStatus.getStatusCode().equals("BAD_REQUEST"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, serviceStatus.getMessage());
        if (serviceStatus.getStatusCode().equals("NOT_FOUND"))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, serviceStatus.getMessage());
    }
}
