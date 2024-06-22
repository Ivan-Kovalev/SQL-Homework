package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private final Student student = new Student(1L, "Harry Potter", 16);
    private final List<Student> students = List.of(
            new Student(1L, "Harry Potter", 16),
            new Student(2L, "Ron Weasley", 16),
            new Student(3L, "Hermione Granger", 15),
            new Student(4L, "Hermione Granger Error", null));

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService service;

    @Test
    void add() {
        when(studentRepository.save(any())).thenReturn(student);

        Assertions.assertEquals(student, service.add(new Student(1L, "Harry Potter", 16)));
    }

    @Test
    void find() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Assertions.assertEquals(student, service.find(1L));
    }

    @Test
    void edit() {
        when(studentRepository.save(new Student(1L, "Harry Potter", 16))).thenReturn(student);

        Assertions.assertEquals(student, service.edit(new Student(1L, "Harry Potter", 16)));
    }

    @Test
    void delete() {
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertTrue(service.delete(1L));
        verify(studentRepository).deleteById(1L);
    }

    @Test
    void getAllStudents() {
        when(studentRepository.findAll()).thenReturn(students);

        Assertions.assertEquals(students, service.getAllStudents());
        Assertions.assertEquals(4, service.getAllStudents().size());
    }

    @Test
    void getAllStudentsByAge() {
        when(studentRepository.findStudentByAge(any())).thenReturn(students);

        Assertions.assertEquals(students, service.getAllStudentsByAge(15));
    }
}
