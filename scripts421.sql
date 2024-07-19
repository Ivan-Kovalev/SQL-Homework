CREATE TABLE student_checked (
                                 id INTEGER,
                                 name TEXT PRIMARY KEY,
                                 age INTEGER CHECK (age > 16) DEFAULT 20,
                                 faculty_id INTEGER,
                                 avatar_id INTEGER
)