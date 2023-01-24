package com.choice.controller;

import com.choice.model.Hotel;
import com.choice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<?> getHotels() {
        return hotelService.getHotels();
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable int hotelId) {
        return hotelService.getHotel(hotelId);
    }

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @PutMapping("/hotels/{hotelId}")
    public HttpEntity<?> updateHotel(@PathVariable int hotelId, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(hotel, hotelId);
    }

    @DeleteMapping("/hotels")
    public ResponseEntity<?> deleteHotels() {
        return hotelService.deleteHotels();
    }

    @DeleteMapping("/hotels/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable int hotelId) {
        return hotelService.deleteHotel(hotelId);
    }
}
