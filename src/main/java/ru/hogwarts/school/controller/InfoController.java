package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("production")
public class InfoController {

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping(path = "/port")
    public ResponseEntity<Integer> getServerPort() {
        return ResponseEntity.ok(serverProperties.getPort());
    }
}
