package com.auto.mementauto.dtos;

import lombok.Data;

@Data
public class VehicleDto {
    private Long id;
    private String brand;
    private String model;
    private String productionYear;
    private Double mileage;
    private String carPicture;
    private Long owner;
    private Long works;
}
