package ru.hogwarts.school.test_utils;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

import static ru.hogwarts.school.test_utils.ConstantStudent.*;

public class ConstantFaculty {
    private ConstantFaculty() {
    }

    public static Faculty GRYFFINDOR = new Faculty(1L, "Gryffindor", "red", List.of(
            STUDENT_1,
            STUDENT_2,
            STUDENT_3,
            STUDENT_4));

    public static Faculty SLYTHERIN  = new Faculty(2L, "slytherin", "green", List.of(
            STUDENT_5,
            STUDENT_6,
            STUDENT_7,
            STUDENT_8));

    public static List<Faculty> FACULTIES = List.of(GRYFFINDOR, SLYTHERIN);
}
