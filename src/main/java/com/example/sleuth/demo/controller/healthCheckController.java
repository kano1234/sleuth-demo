package com.example.sleuth.demo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class healthCheckController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("access");
        return new ResponseEntity<>("success response message", HttpStatus.OK);
    }
}
