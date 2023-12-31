package com.example.user.service.services.impl;

import com.example.user.service.entities.Hotel.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.services.HotelService;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
       String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of user repository
       User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User is not found on the server with given id: "+userId));
       //fetch rating of the above user from RATING SERVICE
        Rating[] ratingsforUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings= Arrays.stream(ratingsforUser).toList();
       List<Rating> ratingList= ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel http://localhost:8082/hotels/{hotelid}
          // ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);   // communication by RestTemplate
         //  Hotel hotel= forEntity.getBody();
           Hotel hotel= hotelService.getHotelByHotelId(rating.getHotelId());
             //set the hotel to rating
            //return the rating
           rating.setHotel(hotel);
            return  rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
       return user;
    }
}
