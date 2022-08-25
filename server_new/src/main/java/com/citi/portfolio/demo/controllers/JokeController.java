package com.citi.portfolio.demo.controllers;

import com.citi.portfolio.demo.ResponseHandler;
import com.citi.portfolio.demo.entities.joke;
import com.citi.portfolio.demo.repos.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@CrossOrigin
public class JokeController {
    @Autowired
    private JokeRepository jdao;

    @GetMapping(value ="/joke")
    public ResponseEntity<String> getJoke()
    {
        try {
            int max = 7;
            int min = 1;
            int range = max - min + 1;
            int id = (int)(Math.random() * range) + min;
            Optional<joke> retrievedJoke = jdao.findById(id);
            String actualJoke = retrievedJoke.get().getJoke();
            return new ResponseEntity<>(actualJoke,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}

