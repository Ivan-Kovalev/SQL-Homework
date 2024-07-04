package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.hogwarts.school.test_utils.ConstantFaculty.FACULTIES;
import static ru.hogwarts.school.test_utils.ConstantFaculty.GRYFFINDOR;
import static ru.hogwarts.school.test_utils.ConstantStudent.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService service;

    @Test
    void add() {
        Mockito.when(facultyRepository.save(any())).thenReturn(GRYFFINDOR);

        Assertions.assertEquals(GRYFFINDOR, service.add(GRYFFINDOR));
    }

    @Test
    void find() {
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(GRYFFINDOR));

        Assertions.assertEquals(GRYFFINDOR, service.find(1L));
    }

    @Test
    void edit() {
        Mockito.when(facultyRepository.save(new Faculty(1L, "Gryffindor", "red",
                List.of(STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4)))).thenReturn(GRYFFINDOR);

        Assertions.assertEquals(GRYFFINDOR, service.edit(new Faculty(1L, "Gryffindor", "red",
                List.of(STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4))));
    }

    @Test
    void delete() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(GRYFFINDOR));

        assertTrue(service.delete(1L));
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    void getAllFaculty() {
        Mockito.when(facultyRepository.findAll()).thenReturn(FACULTIES);

        Assertions.assertEquals((FACULTIES), service.getAllFaculty());
        Assertions.assertEquals(2, service.getAllFaculty().size());
    }

    @Test
    void getAllFacultyByColor() {
        Mockito.when(facultyRepository.findFacultiesByColor(any())).thenReturn(FACULTIES);

        Assertions.assertEquals(FACULTIES, service.getAllFacultyByColor("red"));
    }
}
