SELECT student.name, student.age
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT student.name, student.age
FROM student
INNER JOIN avatar ON student.id = avatar.student_id
WHERE avatar.student_id IS NOT NULL;