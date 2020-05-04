package com.example.demo.controllers;


import com.example.demo.logics.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

/**
 * ExampleController
 *
 * @author danielpadua
 *
 */
@RestController
public class ExampleController {

    private List<User> Users = new LinkedList<>();
    public ExampleController(){
        Users.add(new User("david" , "david@gmai.com"));
        Users.add(new User("eitan" , "eitan@gmai.com"));
        Users.add(new User("lior" , "lior@gmai.com"));
    }
    @RequestMapping("/hi")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @RequestMapping("/Users")
    public List<User> getUsers() {
        return Users;
    }

    @RequestMapping("/Users/{name}")
    public User getUsers(@PathVariable String name) {
        return Users.stream().filter(t-> t.getName().equals(name)).findFirst().get() ;
    }

    @PostMapping("/addUser")
    boolean newEmployee(@RequestBody User newUser) {
        return Users.add(newUser);
    }

}
