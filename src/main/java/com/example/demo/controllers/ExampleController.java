package com.example.demo.controllers;


import com.example.demo.logics.SecurityObject;
import com.example.demo.logics.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.util.Pair;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/securityObjectCheck")
    SecurityObject securityObjectCheck() {
        List<Object> list = new LinkedList<>();
        list.add(new User("avi","avi@walla.com"));
        SecurityObject so = new SecurityObject("123456", "add User", list);
        return so;
    }


    /**
     * demo post req -> {"reqID":"123456","functionName":"add User","object":[{"name":"avi","mail":"avi@walla.com"}]}
     * @param securityObject
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/postSecurity",
            method = RequestMethod.POST)
    public boolean process(@RequestBody SecurityObject securityObject)
            throws Exception {
        if(securityObject.getReqID().equals("123456")){
           if(securityObject.getFunctionName().equals("add User")){
               return Users.add(new User((LinkedHashMap<String,String>)securityObject.getObject().get(0)));
           }
        }
        return false;
    }


    /***
     * demo post req ->
     * {
     *     "ReqID":  "123456",
     *     "User":{
     *         "name": "ko",
     *         "mail": "kobigal@kal"
     *     }
     * }
     * @param payload
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/process",
            method = RequestMethod.POST)
    public boolean process(@RequestBody Map<String, Object> payload)
            throws Exception {
        if(payload.containsKey("ReqID") && payload.get("ReqID").equals("123456")){
                Object temp = payload.get("User") ;
//            ObjectMapper objectMapper = new ObjectMapper();
//            //String json = objectMapper.writeValueAsString(temp);
//            String json = "{ \"name\" : \"lior\", \"mail\" : \"BMW@gamai\" }";
//            User userT = objectMapper.readValue(json, User.class);
//          //  System.out.println(temp.toString());
//          //
            if(temp instanceof Map) {
                String name = ((Map) temp).get("name").toString();
                String mail = ((Map) temp).get("mail").toString();
                return Users.add(new User(name,mail));
            }

        }
        return false;
    }

}
