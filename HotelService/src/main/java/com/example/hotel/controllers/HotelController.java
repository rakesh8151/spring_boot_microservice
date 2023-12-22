package com.example.hotel.controllers;

import com.example.hotel.entities.Hotel;
import com.example.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable String hotelId){
        return  ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

    @GetMapping()
    public ResponseEntity<List<Hotel>> createHotel(){
        return  ResponseEntity.ok(hotelService.getAllHotel());
    }

}
