package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

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
    }

    @Test
    void deleteStudent() {
    }
}