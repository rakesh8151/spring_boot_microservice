package com.example.user.service.external.services;

import com.example.user.service.entities.Hotel.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotelByHotelId(@PathVariable String hotelId);
}
