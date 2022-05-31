package com.auto.mementauto.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class WorkDto {
    private Long id;
    private String workName;
    private String category;
    private Long vehicle;
    private Date date;
    private String hour;

}
