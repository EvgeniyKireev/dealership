package com.example.dealership.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Integer year;
    private String color;
    private String category;
    private Integer power;
    private Integer price;

    public Car(String model, Integer year, String color, String category, Integer power, Integer price) {
        this.model = model;
        this.year = year;
        this.color = color;
        this.category = category;
        this.power = power;
        this.price = price;
    }

    /**
     * связь с автосалонами
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showroom_id", referencedColumnName = "id")
    private CarShowRoom showroom_id;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Записать модель
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Записать год
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Установить цвет
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Записать категорию
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return power
     */
    public Integer getPower() {
        return power;
    }

    /**
     * Записывает мощность
     * @param power
     */
    public void setPower(Integer power) {
        this.power = power;
    }

    /**
     *
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Записать цену
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     *
     * @return showroom_id
     */
    public CarShowRoom getShowroom_id() {
        return showroom_id;
    }

    /**
     * Записать id автосалонаS
     * @param showroom_id
     */
    public void setShowroom_id(CarShowRoom showroom_id) {
        this.showroom_id = showroom_id;
    }
}