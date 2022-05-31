package com.auto.mementauto.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String profilePicture;
    private Boolean isNotificationOn;
    private String email;
}
