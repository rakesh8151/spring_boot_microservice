package com.example.hotel.services;

import com.example.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotelById(String id);
}
