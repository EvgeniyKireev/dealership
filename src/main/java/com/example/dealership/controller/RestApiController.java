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

    /**
     * конструктор апи
     * @param carService сервис для машин
     * @param carShowRoomService сервис для автосалонов
     */
    @Autowired
    public RestApiController(CarService carService, CarShowRoomService carShowRoomService) {
        this.carService = carService;
        this.carShowRoomService = carShowRoomService;
    }

    //car controller

    /**
     * Поиск всех автомобилей
     * @return возвращает Лист объектов Car
     */{}
    @GetMapping(value = "/api/cars")
    public ResponseEntity<List<Car>> findAllCars(){
        final List<Car> newsList = carService.findAll();

        return newsList != null && !newsList.isEmpty()
                ? new ResponseEntity<>(newsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Поиск авто по id
     * @param id id авто
     * @return возвращает Car
     */
    @GetMapping("/api/cars/{id}")
    public ResponseEntity<Optional<Car>> findById(@PathVariable(name = "id") Long id) {
        final Optional<Car> news= carService.findById(id);

        return news.isPresent()
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Создать автомобилей
     * @param car объект Car
     * @return возвращает Лист Cars
     */
    @PostMapping(value = "/api/cars")
    public List<Car> createCar(@RequestBody Car car){
        carService.create(car);
        return carService.findAll();
    }

    /**
     * Обновить авто
     * @param car объект Car
     * @return Возвращает 200
     */
    @PutMapping("/api/cars/update")
    public ResponseEntity<List<Car>> updateCar(@RequestBody Car car) {
        final List<Car> cars = carService.update(car);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Удалить авто
     * @param id id Car
     * @return Возвращает 200
     */
    @DeleteMapping(value = "/api/cars/{id}")
    public ResponseEntity<String> DeleteNewsById(@PathVariable(name = "id") Long id) {
        String message = carService.DeleteById(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // carshowroom controller

    /**
     * Все автосалоны
     * @return Лист CarShowRoom
     */
    @GetMapping(value = "/api/carshowroom")
    public ResponseEntity<List<CarShowRoom>> findAllCarShowRoom(){
        final List<CarShowRoom> newsList = carShowRoomService.findAll();

        return newsList != null && !newsList.isEmpty()
                ? new ResponseEntity<>(newsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Автосалон по id
     * @param id id CarShowRoom
     * @return Объект CarShowRoom
     */
    @GetMapping("/api/carshowroom/{id}")
    public ResponseEntity<List<Car>> findShowRoomById(@PathVariable(name = "id") Long id) {
        final Optional<CarShowRoom> news= carShowRoomService.findById(id);

//

        return news.isPresent()
                ? new ResponseEntity<>(news.get().cars, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Создает автосалон
     * @param carShowRoom объект carShowRoom
     * @return Листa Carshowroom
     */
    @PostMapping(value = "/api/carshowroom")
    public List<CarShowRoom> createCarShowRoom(@RequestBody CarShowRoom carShowRoom){
        carShowRoomService.create(carShowRoom);
        return carShowRoomService.findAll();
    }


}
