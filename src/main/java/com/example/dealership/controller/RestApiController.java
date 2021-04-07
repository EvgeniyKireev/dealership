package com.example.dealership.controller;

import com.example.dealership.entity.Car;
import com.example.dealership.entity.CarShowRoom;
import com.example.dealership.service.CarService;
import com.example.dealership.service.CarShowRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {
    private final CarService carService;
    private final CarShowRoomService carShowRoomService;

    @Autowired
    public RestApiController(CarService carService, CarShowRoomService carShowRoomService) {
        this.carService = carService;
        this.carShowRoomService = carShowRoomService;
    }

    //car controller
    @GetMapping(value = "/api/cars")
    public ResponseEntity<List<Car>> findAllCars(){
        final List<Car> newsList = carService.findAll();

        return newsList != null && !newsList.isEmpty()
                ? new ResponseEntity<>(newsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/cars/{id}")
    public ResponseEntity<Optional<Car>> findById(@PathVariable(name = "id") Long id) {
        final Optional<Car> news= carService.findById(id);

        return news.isPresent()
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/carshowroom/{id}")
    public ResponseEntity<List<Car>> findShowRoomById(@PathVariable(name = "id") Long id) {
        final Optional<CarShowRoom> news= carShowRoomService.findById(id);

//

        return news.isPresent()
                ? new ResponseEntity<>(news.get().cars, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/cars")
    public ResponseEntity<?> createClient(@RequestBody Car car){
        carService.create(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
