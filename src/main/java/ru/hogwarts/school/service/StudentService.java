package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student add(Student student) {
        student.setId(null);
        logger.debug("Вызван метод add");
        return studentRepository.save(student);
    }

    public Student find(Long id) {
        logger.debug("Вызван метод find");
        return studentRepository.findById(id)
                .orElse(null);
    }

    public Student edit(Student student) {
        logger.debug("Вызван метод edit");
        return studentRepository.save(student);
    }

    public boolean delete(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            logger.debug("Вызван метод delete");
            return true;
        } else {
            logger.warn("Метод delete не отработал");
            return false;
        }
    }

    public List<Student> getAllStudents() {
        logger.debug("Вызван метод getAllStudents");
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByAge(Integer age) {
        logger.debug("Вызван метод getAllStudentsByAge");
        return studentRepository.findStudentsByAge(age);
    }

    public List<Student> getAllStudentsByAgeBetween(Integer min, Integer max) {
        logger.debug("Вызван метод getAllStudentsByAgeBetween");
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Integer getCountAllStudent() {
        logger.debug("Вызван метод getCountAllStudent");
        return studentRepository.getCountAllStudent();
    }

    public Integer getAverageAgeStudents() {
        logger.debug("Вызван метод getAverageAgeStudents");
        return studentRepository.getAverageAgeStudents();
    }

    public List<Student> getLastAddedFiveStudent() {
        logger.debug("Вызван метод getLastAddedFiveStudent");
        return studentRepository.getLastAddedFiveStudents();
    }
}
