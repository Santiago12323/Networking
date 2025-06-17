package com.edu.escuelaing.arsw.networking.Networking.infrastructure.controller;


import com.edu.escuelaing.arsw.networking.Networking.domain.ports.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    UrlService urlService;

    @GetMapping(value = "/read-html", produces = "text/html; charset=UTF-8")
    public ResponseEntity<?> readHtml(@RequestParam String url) {
        try {
            return ResponseEntity.ok(urlService.readHtml(url));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/analyzeUrl")
    public ResponseEntity<?> analyzeUrl(){
        try {
            return ResponseEntity.accepted().body(Map.of("lista de diferentes metodos, con ejemplo de facebook" +
                    " : ", urlService.analyzeUrl()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error inesperado", e.getMessage().toString()));
        }
    }
}
