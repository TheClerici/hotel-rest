package com.choice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel{
    private Long hotelId;
    private String name;
    private String address;
    private Integer rating;

    public Hotel(String name, String address, Integer rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }
}
