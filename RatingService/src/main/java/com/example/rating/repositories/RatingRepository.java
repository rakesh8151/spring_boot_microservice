package com.example.rating.repositories;

import com.example.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
