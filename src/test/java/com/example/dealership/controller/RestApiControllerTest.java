package com.example.dealership.controller;
import com.example.dealership.entity.Car;
import com.example.dealership.entity.CarShowRoom;
import com.example.dealership.repository.CarRepository;
import com.example.dealership.repository.CarShowRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @Autowired
    public CarShowRoomRepository carShowRoomRepository;

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

    @Test
    public void createCar() {
        String model = "bmw";
        Integer year = 2020;
        String color = "red";
        String category = "B";
        Integer power = 2222;
        Integer price = 666666;

        Car car = new Car(model, year, color, category, power, price);
        try {
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:5000/api/cars")
                    .content(JSONToStr(car))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findAllCarShowRoom() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:5000/api/carshowroom")) // отправляем запрос
                .andDo(MockMvcResultHandlers.print()) // выводим в консоль
                .andExpect(status().is2xxSuccessful()) // проверяем пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // записываем то что пришло в строку
                    JSONArray arr = new JSONArray(body); // переделываем в JSON
                    assertEquals(carShowRoomRepository.findAll().size(), arr.length()); // сверяем длину массива через репозиторий и длину пришедшешго массива
                })
                .andReturn();
    }

    @Test
    public void findShowRoomById() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:5000/api/carshowroom/7"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void createCarShowRoom() {
        CarShowRoom carShowRoom = new CarShowRoom("BMW", "Moscow", "9245415442", "10:00-20:00");
        try {
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:5000/api/carshowroom")
                    .content(JSONToStr(carShowRoom))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String JSONToStr(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonToString = mapper.writeValueAsString(object);
            return jsonToString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}