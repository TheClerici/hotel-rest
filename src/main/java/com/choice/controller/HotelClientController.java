package com.choice.controller;

import com.choice.model.Hotel;
import com.choice.service.ErrorService;
import com.choice.service.HotelClientService;
import com.choice.wsdl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HotelClientController {
    private final HotelClientService hotelClientService;
    private final ErrorService errorService;

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<HotelInfo> getHotel(@PathVariable Long hotelId) {
        GetHotelByIdResponse response = hotelClientService.getHotelById(hotelId);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @GetMapping(value = "/hotels", params = {"pageNumber", "pageSize", "nameFilter"})
    public ResponseEntity<List<HotelInfo>> getHotels(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String nameFilter) {
        GetAllHotelsWithFilteringResponse response = hotelClientService.getAllHotelsWithFiltering(pageNumber, pageSize, nameFilter);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo().stream().toList());
    }

    @PostMapping("/hotel")
    public ResponseEntity<HotelInfo> createHotel(@RequestBody Hotel hotel) {
        errorService.checkHotel(hotel);
        AddHotelResponse response = hotelClientService.addHotel(hotel.getName(), hotel.getAddress(), hotel.getRating());
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @PutMapping("/hotels/{hotelId}")
    public HttpEntity<HotelInfo> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) {
        errorService.checkHotel(hotel);
        UpdateHotelResponse response = hotelClientService.updateHotel(hotelId, hotel);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getHotelInfo());
    }

    @DeleteMapping("/hotels/{hotelId}")
    public ResponseEntity<ServiceStatus> deleteHotel(@PathVariable Long hotelId) {
        DeleteHotelResponse response = hotelClientService.deleteHotel(hotelId);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getServiceStatus());
    }

    @PostMapping("/hotel/{hotelId}/amenity/{amenityId}")
    public ResponseEntity<AmenityInfo> addAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {
        AddAmenityResponse response = hotelClientService.addAmenity(amenityId, hotelId);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getAmenityInfo());
    }

    @DeleteMapping("/hotel/{hotelId}/amenity/{amenityId}")
    public ResponseEntity<ServiceStatus> deleteAmenity(@PathVariable Long hotelId, @PathVariable Long amenityId) {
        DeleteAmenityResponse response = hotelClientService.deleteAmenity(amenityId, hotelId);
        errorService.checkStatus(response.getServiceStatus());
        return ResponseEntity.ok(response.getServiceStatus());
    }
}

