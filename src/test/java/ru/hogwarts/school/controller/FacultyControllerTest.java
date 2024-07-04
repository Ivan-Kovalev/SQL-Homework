package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void getFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/1L", Faculty.class))
                .isNotNull();
    }

    @Test
    void getAllFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties", Collection.class))
                .isNotNull();
    }

    @Test
    void getAllFacultyByColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/color/red", Collection.class))
                .isNotNull();
    }

    @Test
    void getFacultyByNameOrColor() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("red");
        faculty.setId(1L);

        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity("/faculties", faculty, Faculty.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/searchTerm/red", List.class))
                .isNotNull();
    }

    @Test
    void addFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Gryffindor");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", faculty, Faculty.class))
                .isNotNull();
    }

    @Test
    void updateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("red");
        faculty.setId(1L);

        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity("/faculties", faculty, Faculty.class);
        Long savedFacultyId = requireNonNull(facultyResponseEntity.getBody()).getId();

        Faculty facultyChanging = facultyResponseEntity.getBody();
        facultyChanging.setName("Slytherin");
        facultyChanging.setColor("green");
        facultyChanging.setStudents(new ArrayList<>());

        restTemplate.put("/faculties", facultyChanging);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/" + savedFacultyId, Faculty.class);

        assertEquals(response.getBody(), facultyChanging);
    }

    @Test
    void deleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("red");
        faculty.setId(1L);

        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.postForEntity("/faculties", faculty, Faculty.class);
        Long savedFacultyId = requireNonNull(facultyResponseEntity.getBody()).getId();

        ResponseEntity<Faculty> findResponse = restTemplate.getForEntity("/faculties/" + savedFacultyId, Faculty.class);
        Assertions.assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        restTemplate.delete("/faculties/" + savedFacultyId);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculties/" + savedFacultyId, Faculty.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}