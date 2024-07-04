package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void getStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1L", Student.class))
                .isNotNull();
    }

    @Test
    void getAllStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", Collection.class))
                .isNotNull();
    }

    @Test
    void getAllStudentByAge() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/age/15L", JSONObject.class))
                .isNotNull();
    }

    @Test
    void getAllStudentByAgeBetween() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/age-between/10/15", Collection.class))
                .isNotNull();
    }

    @Test
    void addStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setAge(15);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class))
                .isNotNull();
    }

    @Test
    void editStudent() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(15);
        student.setId(1L);

        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity("/students", student, Student.class);
        Long savedStudentId = requireNonNull(studentResponseEntity.getBody()).getId();

        Student studentChanging = studentResponseEntity.getBody();
        studentChanging.setName("Harry Potter");
        studentChanging.setAge(25);

        restTemplate.put("/students", studentChanging);

        ResponseEntity<Student> response = restTemplate.getForEntity("/students/" + savedStudentId, Student.class);

        assertEquals(response.getBody(), studentChanging);
    }

    @Test
    void deleteStudent() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(15);
        student.setId(1L);

        ResponseEntity<Student> studentResponseEntity = restTemplate.postForEntity("/students", student, Student.class);
        Long savedStudentId = requireNonNull(studentResponseEntity.getBody()).getId();

        ResponseEntity<Student> findResponse = restTemplate.getForEntity("/students/" + savedStudentId, Student.class);
        Assertions.assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        restTemplate.delete("/students/" + savedStudentId);

        ResponseEntity<Student> response = restTemplate.getForEntity("/students/" + savedStudentId, Student.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}