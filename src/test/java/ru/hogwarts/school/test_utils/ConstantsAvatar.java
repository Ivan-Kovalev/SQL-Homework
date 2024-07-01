package ru.hogwarts.school.test_utils;

import ru.hogwarts.school.model.Avatar;

import static ru.hogwarts.school.test_utils.ConstantStudent.*;

public class ConstantsAvatar {
    private ConstantsAvatar() {
    }

    public static Avatar AVATAR_1 = new Avatar(1L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_1);
    public static Avatar AVATAR_2 = new Avatar(2L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_2);
    public static Avatar AVATAR_3 = new Avatar(3L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_3);
    public static Avatar AVATAR_4 = new Avatar(4L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_4);

    public static Avatar AVATAR_5 = new Avatar(5L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_5);
    public static Avatar AVATAR_6 = new Avatar(6L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_6);
    public static Avatar AVATAR_7 = new Avatar(7L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_7);
    public static Avatar AVATAR_8 = new Avatar(8L, "default", 2L, "default", new byte[2], new byte[2], STUDENT_8);
}
