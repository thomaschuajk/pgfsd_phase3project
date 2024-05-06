package com.bookcabcloud.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingsRepository extends JpaRepository<Booking, Integer>{

}
