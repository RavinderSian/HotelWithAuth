package com.personal.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.hotel.model.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {

}
