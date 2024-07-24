package ru.hogwarts.school.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("!production")
public class infoControllerTest {

    @GetMapping(path = "/port")
    public ResponseEntity<Integer> getServerPort() {
        return ResponseEntity.ok(0);
    }
}
