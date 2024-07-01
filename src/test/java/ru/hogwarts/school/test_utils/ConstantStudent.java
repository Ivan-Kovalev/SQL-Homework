package ru.hogwarts.school.test_utils;

import ru.hogwarts.school.model.Student;

import java.util.List;

import static ru.hogwarts.school.test_utils.ConstantFaculty.GRYFFINDOR;
import static ru.hogwarts.school.test_utils.ConstantFaculty.SLYTHERIN;
import static ru.hogwarts.school.test_utils.ConstantsAvatar.*;

public class ConstantStudent {
    private ConstantStudent() {
    }

    public static Student STUDENT_1 = new Student(1L, "Harry Potter", 16, GRYFFINDOR, AVATAR_1);
    public static Student STUDENT_2 = new Student(2L, "Remus Lupin", 35, GRYFFINDOR, AVATAR_2);
    public static Student STUDENT_3 = new Student(3L, "Minerva McGonagall", 59, GRYFFINDOR, AVATAR_3);
    public static Student STUDENT_4 = new Student(4L, "Sirius Black", 36, GRYFFINDOR, AVATAR_4);

    public static Student STUDENT_5 = new Student(5L, "Draco Malfoy", 16, SLYTHERIN, AVATAR_5);
    public static Student STUDENT_6 = new Student(6L, "Severus Snape", 36, SLYTHERIN, AVATAR_6);
    public static Student STUDENT_7 = new Student(7L, "Bellatrix Lestrange", 33, SLYTHERIN, AVATAR_7);
    public static Student STUDENT_8 = new Student(8L, "Tom Riddle", 82, SLYTHERIN, AVATAR_8);

    public static List<Student> STUDENTS = List.of(STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4);
}
