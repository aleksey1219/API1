package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findStudentByAge(int age);

    List<Student> findAllByAgeBetween(int min, int max);
}
