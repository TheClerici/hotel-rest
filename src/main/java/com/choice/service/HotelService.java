package com.choice.service;

import com.choice.model.Hotel;
import com.choice.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements CrudService<Hotel> {

    private HotelRepository repository;

    public HotelService(HotelRepository hotelRepository) {
        repository = hotelRepository;
    }

    @Override
    public List<Hotel> list() {
        return repository.findAll();
    }

    @Override
    public Hotel create(Hotel hotel) {
        return null;
    }

    @Override
    public Optional<Hotel> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Hotel hotel, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
