package com.example.dealership.service;

import com.example.dealership.entity.Car;
import com.example.dealership.entity.CarShowRoom;
import com.example.dealership.repository.CarRepository;
import com.example.dealership.repository.CarShowRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarShowRoomService {
    @Autowired
    private CarShowRoomRepository carShowRoomRepository;

    public void create(CarShowRoom carShowRoom){
        carShowRoomRepository.save(carShowRoom);
    }

    public List<CarShowRoom> findAll(){
        return carShowRoomRepository.findAll();
    }

    public Optional<CarShowRoom> findById(Long id) {
        return carShowRoomRepository.findById(id);
    }
    public String DeleteById(Long id) {
        carShowRoomRepository.deleteById(id);

        return  "Новость удалена";
    }
}
