package com.personal.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.hotel.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}
