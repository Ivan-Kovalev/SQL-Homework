package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/faculties")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = service.find(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        Collection<Faculty> faculty = service.getAllFaculty();
        return ResponseEntity.ok(faculty);
    }

    @GetMapping(path = "/color/{color}")
    public ResponseEntity<Collection<Faculty>> getAllFacultyByColor(@PathVariable String color) {
        Collection<Faculty> faculty = service.getAllFacultyByColor(color);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping(path = "/searchTerm/{searchTerm}")
    public ResponseEntity<List<Faculty>> getFacultyByNameOrColor(@PathVariable String searchTerm) {
        List<Faculty> faculties = service.getFacultiesByNameOrColor(searchTerm);
        if (!faculties.isEmpty()) {
            return new ResponseEntity<>(faculties, OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/longest-name")
    public String getFacultyWithLongestName() {
        return service.getFacultyWithLongestName();
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updated = service.edit(faculty);
        if (updated != null) {
            return new ResponseEntity<>(updated, OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        service.delete(id);
    }

}
