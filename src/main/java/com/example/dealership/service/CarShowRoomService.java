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
    /**
     * подключение репозитори
     */
    @Autowired
    private CarShowRoomRepository carShowRoomRepository;

    /**
     * Создать автосалон
     * @param carShowRoom объект carShowRoom
     */
    public void create(CarShowRoom carShowRoom){
        carShowRoomRepository.save(carShowRoom);
    }

    /**
     * Поиск всех автосалонов
     * @return Лист CarShowRoom
     */
    public List<CarShowRoom> findAll(){
        return carShowRoomRepository.findAll();
    }

    /**
     * Поиск автосалона по id
     * @param id id CarShowRoom
     * @return CarshowRoom
     */
    public Optional<CarShowRoom> findById(Long id) {
        return carShowRoomRepository.findById(id);
    }
    public String DeleteById(Long id) {
        carShowRoomRepository.deleteById(id);

        return  "Новость удалена";
    }
}
