package com.choice.controller;

import com.choice.model.Hotel;
import com.choice.service.HotelClientService;
import com.choice.wsdl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HotelClientController {
    private final HotelClientService hotelClientService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello tomcat");
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable Long hotelId) {

        GetHotelByIdResponse response = hotelClientService.getHotelById(hotelId);
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getHotelInfo());
    }

    @GetMapping(value = "/hotels", params = {"pageNumber", "pageSize", "nameFilter"})
    public ResponseEntity<?> getHotels(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "") String nameFilter) {

        GetAllHotelsWithFilteringResponse response = hotelClientService.getAllHotelsWithFiltering(pageNumber, pageSize, nameFilter);

        return ResponseEntity.ok(response.getHotelInfo().stream());
    }

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {

        checkHotel(hotel);
        AddHotelResponse response = hotelClientService.addHotel(hotel.getName(), hotel.getAddress(), hotel.getRating());
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getHotelInfo());
    }

    @PutMapping("/hotels/{hotelId}")
    public HttpEntity<?> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) {

        checkHotel(hotel);
        UpdateHotelResponse response = hotelClientService.updateHotel(hotelId, hotel);
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getHotelInfo());
    }

    @DeleteMapping("/hotels/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {

        DeleteHotelResponse response = hotelClientService.deleteHotel(hotelId);
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getServiceStatus());
    }

    @PostMapping("/hotel/{hotelId}/amenity/{amenityId}")
    public ResponseEntity<?> addAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {

        AddAmenityResponse response = hotelClientService.addAmenity(amenityId, hotelId);
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getAmenityInfo());
    }

    @DeleteMapping("/hotel/{hotelId}/amenity/{amenityId}")
    public ResponseEntity<?> deleteAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {

        DeleteAmenityResponse response = hotelClientService.deleteAmenity(amenityId, hotelId);
        checkStatus(response.getServiceStatus());

        return ResponseEntity.ok(response.getServiceStatus());
    }

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

