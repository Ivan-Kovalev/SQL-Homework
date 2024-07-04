package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.hogwarts.school.test_utils.ConstantFaculty.GRYFFINDOR;
import static ru.hogwarts.school.test_utils.ConstantStudent.STUDENTS;
import static ru.hogwarts.school.test_utils.ConstantStudent.STUDENT_1;
import static ru.hogwarts.school.test_utils.ConstantsAvatar.AVATAR_1;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService service;

    @Test
    void add() {
        when(studentRepository.save(any())).thenReturn(STUDENT_1);

        Assertions.assertEquals(
                STUDENT_1,
                service.add(new Student(1L, "Harry Potter", 16, GRYFFINDOR, AVATAR_1)));
    }

    @Test
    void find() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(STUDENT_1));

        Assertions.assertEquals(STUDENT_1, service.find(1L));
    }

    @Test
    void edit() {
        when(studentRepository.save(
                new Student(1L, "Harry Potter", 16, GRYFFINDOR, AVATAR_1)))
                .thenReturn(STUDENT_1);

        Assertions.assertEquals(
                STUDENT_1,
                service.edit(new Student(1L, "Harry Potter", 16, GRYFFINDOR, AVATAR_1)));
    }

    @Test
    void delete() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(STUDENT_1));

        assertTrue(service.delete(1L));
        verify(studentRepository).deleteById(1L);
    }

    @Test
    void getAllStudents() {
        when(studentRepository.findAll()).thenReturn(STUDENTS);

        Assertions.assertEquals(STUDENTS, service.getAllStudents());
        Assertions.assertEquals(4, service.getAllStudents().size());
    }

    @Test
    void getAllStudentsByAge() {
        when(studentRepository.findStudentsByAge(any())).thenReturn(STUDENTS);

        Assertions.assertEquals(STUDENTS, service.getAllStudentsByAge(15));
    }
}
