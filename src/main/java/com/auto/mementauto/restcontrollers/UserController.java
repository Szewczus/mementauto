package com.auto.mementauto.restcontrollers;

import com.auto.mementauto.dtos.UserDto;
import com.auto.mementauto.entities.UserEntity;
import com.auto.mementauto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserDto userDto){
        UserEntity user = userService.saveUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/getLogin")
    public ResponseEntity<String> getLogin(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("/findUserEntityByLogin/{login}")
    public ResponseEntity <UserEntity> findUserEntityByLogin(@PathVariable("login") String login){
        return ResponseEntity.ok(userService.findUserEntityByLogin(login));
    }
}
