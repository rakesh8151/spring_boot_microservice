package com.example.rating.services;

import com.example.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
