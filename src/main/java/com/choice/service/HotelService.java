package com.choice.service;

import com.choice.model.Hotel;
import com.choice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Service
public class HotelService implements CrudService<Hotel> {

    private Integer idCounter = 1;
    //Java collection that works as repository until integration.
    private HashMap<Integer, Hotel> hotels = new HashMap<>();

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public ResponseEntity<?> getHotels() {

        //TODO: Error handling

        return ResponseEntity.ok(hotels.values());
    }

    @Override
    public ResponseEntity<?> getHotel(int id) {

        //TODO: Error handling

        return ResponseEntity.ok(hotels.get(id));
    }

    @Override
    public ResponseEntity<?> createHotel(Hotel hotel) {

        //TODO: Error handling

        Hotel newHotel = new Hotel(idCounter, hotel.getName(), hotel.getAddress(), hotel.getRating());
        hotels.put(idCounter, newHotel);
        return new ResponseEntity<>(hotels.get(idCounter++), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateHotel(Hotel hotel, int id) {
        if (hotels.get(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found." );
        Hotel updatedHotel = hotels.get(id);

        if (hotel.getName() != null) updatedHotel.setName(hotel.getName());
        if (hotel.getAddress() != null) updatedHotel.setAddress(hotel.getAddress());
        if (hotel.getRating() <= 5 || hotel.getRating() >= 0) updatedHotel.setRating(hotel.getRating());

        hotels.put(id, updatedHotel);
        return ResponseEntity.ok(hotels.get(id));
    }

    @Override
    public ResponseEntity<?> deleteHotels() {
        if (hotels.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hotels found to delete.");
        hotels = new HashMap<>();
        idCounter = 1;
        return ResponseEntity.ok(hotels.values());
    }

    @Override
    public ResponseEntity<?> deleteHotel(int id) {
        if (hotels.get(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found.");
        hotels.remove(id);
        return ResponseEntity.ok(hotels.values());
    }
}
