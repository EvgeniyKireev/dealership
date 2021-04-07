package com.example.dealership.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "car_show_room")
@Data
public class CarShowRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String address;
    public String phone;
    public String workingHours;
    public Integer price;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "showroom_id")
    public List<Car> cars;
}