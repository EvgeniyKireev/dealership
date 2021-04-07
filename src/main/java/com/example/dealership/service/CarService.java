package com.example.dealership.service;

import com.example.dealership.entity.Car;
import com.example.dealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void create(Car car){
        carRepository.save(car);
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }
    public String DeleteById(Long id) {
        carRepository.deleteById(id);

        return  "Новость удалена";
    }
}
