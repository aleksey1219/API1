package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findStudentByAge(int age);

    List<Student> findAllByAgeBetween(int min, int max);
    @Query(value = "SELECT COUNT(*) FROM student",nativeQuery = true)
    Integer findAllStudentsInSchool();

    @Query(value = "SELECT  AVG(age) as age FROM student", nativeQuery = true)
    double AvgAgeStudent();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudents();
}
