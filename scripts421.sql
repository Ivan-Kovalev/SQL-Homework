CREATE TABLE student_checked (
                                 id INTEGER PRIMARY KEY,
                                 name TEXT PRIMARY KEY,
                                 age INTEGER CHECK (age > 16) DEFAULT 20,
                                 faculty_id INTEGER,
                                 avatar_id INTEGER
);

ALTER TABLE student ADD CONSTRAINT name_unique_require UNIQUE (name);
ALTER TABLE faculty ADD CONSTRAINT name_unique_require UNIQUE (name, color);