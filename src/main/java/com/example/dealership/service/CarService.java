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

    public List<Car> update(Car car){
        var updatedCar = carRepository.findById(car.getId());

        if (updatedCar.isPresent())
        {
            var _updatedCar = updatedCar.get();
            _updatedCar.setCategory(car.getCategory() != null ? car.getCategory() : _updatedCar.getCategory());
            _updatedCar.setColor(car.getColor() != null ? car.getColor() : _updatedCar.getColor());
            _updatedCar.setModel(car.getModel() != null ? car.getModel() : _updatedCar.getModel());
            _updatedCar.setPower(car.getPower() != null ? car.getPower() : _updatedCar.getPower());
            _updatedCar.setPrice(car.getPrice() != null ? car.getPrice() : _updatedCar.getPrice());
            _updatedCar.setShowroom_id(car.getShowroom_id() != null ? car.getShowroom_id() : _updatedCar.getShowroom_id());
            _updatedCar.setYear(car.getYear() != null ? car.getYear() : _updatedCar.getYear());
            carRepository.save(_updatedCar);
        }

        return carRepository.findAll();
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
