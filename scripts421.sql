ALTER TABLE student
    ADD CONSTRAINT age CHECK (age > 16);
ALTER  TABLE student
    ALTER COLUMN name SET NOT NULL;
ALTER TABLE student
    add constraint name_unique unique (name);
ALTER TABLE faculty
    add constraint faculty_unique unique (name, color);
ALTER TABLE student
    alter column age set default 20;