CREATE TABLE car (
                     id int PRIMARY KEY,
                     mark text,
                     model text,
                     price int
);
CREATE TABLE person (
                        id int PRIMARY KEY,
                        name text,
                        age int,
                        have_driveLicense bool,
                        car_id int REFERENCES car(id)

);

select s.name, s.age, f.name
from student s
         join faculty f on f.id = s.faculty_id;

select s.name, s.id
from avatar a
         join student s on s.id = a.student_id;
