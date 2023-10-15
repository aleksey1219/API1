package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findFacultyByColorIgnoreCase(String color);
    List<Faculty>findAllByNameOrColorIgnoreCase(String name, String color);
}
