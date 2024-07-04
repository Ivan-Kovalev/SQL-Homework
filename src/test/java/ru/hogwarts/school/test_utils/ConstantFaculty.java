package ru.hogwarts.school.test_utils;

import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.List;

import static ru.hogwarts.school.test_utils.ConstantStudent.*;

public class ConstantFaculty {
    private ConstantFaculty() {
    }

    public static Faculty GRYFFINDOR;
    public static Faculty SLYTHERIN;
    public static List<Faculty> FACULTIES;

    static {
        GRYFFINDOR = new Faculty(1L, "Gryffindor", "red", new ArrayList<>());
        GRYFFINDOR.getStudents().add(ConstantStudent.STUDENT_1);
        GRYFFINDOR.getStudents().add(ConstantStudent.STUDENT_2);
        GRYFFINDOR.getStudents().add(ConstantStudent.STUDENT_3);
        GRYFFINDOR.getStudents().add(ConstantStudent.STUDENT_4);

        SLYTHERIN = new Faculty(2L, "Slytherin", "green", new ArrayList<>());
        SLYTHERIN.getStudents().add(ConstantStudent.STUDENT_5);
        SLYTHERIN.getStudents().add(ConstantStudent.STUDENT_6);
        SLYTHERIN.getStudents().add(ConstantStudent.STUDENT_7);
        SLYTHERIN.getStudents().add(ConstantStudent.STUDENT_8);

        FACULTIES = List.of(GRYFFINDOR, SLYTHERIN);
    }
}
