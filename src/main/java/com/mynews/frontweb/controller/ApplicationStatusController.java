package com.mynews.frontweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.Callable;

@Controller
public class ApplicationStatusController {

    @GetMapping("/actuator/info")
    public Callable<ResponseEntity<String>> getInfo() {
        return ()->ResponseEntity.ok("application front-end is alive");
    }
}
