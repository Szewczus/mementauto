package com.auto.mementauto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_entity")
public class VehicleEntity {
    private static final long serialVersionUID = 2725515065113810939L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String brand;
    private String model;
    private String productionYear;
    private Double mileage;
    private String carPicture;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity owner;

    @Transient
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WorkEntity> works;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public UserEntity getOwner(){
        return owner;
    }

    public Set<WorkEntity> getWorks() {
        return works;
    }

    public void setWorks(Set<WorkEntity> works) {
        this.works = works;
    }
}
