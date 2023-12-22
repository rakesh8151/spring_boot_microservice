package com.example.rating.controllers;

import com.example.rating.entities.Rating;
import com.example.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return  ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return  ResponseEntity.ok(ratingService.getAllRating());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingHotelId(@PathVariable String hotelId){
        return  ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
