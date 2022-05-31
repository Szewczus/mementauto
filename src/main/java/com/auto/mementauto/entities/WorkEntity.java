package com.auto.mementauto.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_entity")
public class WorkEntity {
    private static final long serialVersionUID = 1047487366908754525L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String workName;
    private String category;
    private Date date;
    private String hour;
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleEntity vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
