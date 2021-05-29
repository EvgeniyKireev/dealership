package com.example.dealership.controller;
import com.example.dealership.repository.CarRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerTest {
    @Autowired
    public MockMvc mvc;

    @Autowired
    public CarRepository carRepository;

    @Test
    public void findAllCars() throws Exception{
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:5000/api/cars")) // отправляем запрос
                    .andDo(MockMvcResultHandlers.print()) // выводим в консоль
                    .andExpect(status().is2xxSuccessful()) // проверяем пришел ли статус 200
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(); // записываем то что пришло в строку
                        JSONArray arr = new JSONArray(body); // переделываем в JSON
                        assertEquals(carRepository.findAll().size(), arr.length()); // сверяем длину массива через репозиторий и длину пришедшешго массива
                    })
                    .andReturn();
    }

    @Test
    public void findById() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:5000/api/cars/25"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString();
                    JSONObject obj = new JSONObject(body);
                    assertEquals(obj.get("id"), 25);
                })
                .andReturn();
    }
}