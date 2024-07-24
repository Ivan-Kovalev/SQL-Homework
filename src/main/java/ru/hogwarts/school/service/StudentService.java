package ru.hogwarts.school.service;

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

    public Student add(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student find(Long id) {
        return studentRepository.findById(id)
                .orElse(null);
    }

    public Student edit(Student student) {
        return studentRepository.save(student);
    }

    public boolean delete(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByAge(Integer age) {
        return studentRepository.findStudentsByAge(age);
    }

    public List<Student> getAllStudentsByAgeBetween(Integer min, Integer max) {
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Integer getCountAllStudent() {
        return studentRepository.getCountAllStudent();
    }

    public Integer getAverageAgeStudents() {
        return studentRepository.getAverageAgeStudents();
    }

    public List<Student> getLastAddedFiveStudent() {
        return studentRepository.getLastAddedFiveStudents();
    }
}
