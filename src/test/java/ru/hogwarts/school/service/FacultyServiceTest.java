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

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    private final Faculty faculty = new Faculty(1L, "Gryffindor", "red");
    private final List<Faculty> faculties = List.of(
            new Faculty(1L, "Gryffindor", "red"),
            new Faculty(2L, "Slytherin", "green"),
            new Faculty(3L, "GryffindorCopy", "red"),
            new Faculty(4L, "SlytherinCopy", "green"),
            new Faculty(5L, "SlytherinCopy2", null));

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService service;

    @Test
    void add() {
        Mockito.when(facultyRepository.save(any())).thenReturn(faculty);

        Assertions.assertEquals(faculty, service.add(new Faculty(1L, "Gryffindor", "red")));
    }

    @Test
    void find() {
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        Assertions.assertEquals(faculty, service.find(1L));
    }

    @Test
    void edit() {
        Mockito.when(facultyRepository.save(new Faculty(1L, "Gryffindor", "red"))).thenReturn(faculty);

        Assertions.assertEquals(faculty, service.edit(new Faculty(1L, "Gryffindor", "red")));
    }

    @Test
    void delete() {
        faculty.setId(1L);
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        assertTrue(service.delete(1L));
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    void getAllFaculty() {
        Mockito.when(facultyRepository.findAll()).thenReturn(faculties);

        Assertions.assertEquals(faculties, service.getAllFaculty());
        Assertions.assertEquals(5, service.getAllFaculty().size());
    }

    @Test
    void getAllFacultyByColor() {
        Mockito.when(facultyRepository.findFacultyByColor(any())).thenReturn(faculties);

        Assertions.assertEquals(faculties, service.getAllFacultyByColor("red"));
    }
}
