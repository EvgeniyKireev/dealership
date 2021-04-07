package com.example.dealership.repository;


import com.example.dealership.entity.CarShowRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarShowRoomRepository extends JpaRepository<CarShowRoom,Long> {
}