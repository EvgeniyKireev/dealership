package com.example.dealership.service;

import com.example.dealership.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarRepository carRepository;



    @Test
    public void findById() { // не вижу смысла дальше описывать, все по аналогии
        int id = 25;
        assertEquals(carRepository.findById(Long.valueOf(id)).get().getId(), id);
    }
}