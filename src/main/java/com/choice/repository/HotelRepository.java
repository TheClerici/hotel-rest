package com.choice.repository;

import com.choice.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepository implements CrudRepository<Hotel> {

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel = new Hotel(1, "Holiday Inn", "Street 12 num 1", 5);
        hotels.add(hotel);
        return hotels;
    }
}
