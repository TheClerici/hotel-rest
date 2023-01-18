package com.choice.service;

import org.springframework.http.ResponseEntity;

public interface CrudService<T> {

    ResponseEntity<?> getHotels();

    ResponseEntity<?> getHotel(int id);

    ResponseEntity<?> createHotel(T t);

    ResponseEntity<?> updateHotel(T t, int id);

    ResponseEntity deleteHotels();

    ResponseEntity<?> deleteHotel(int id);
}
