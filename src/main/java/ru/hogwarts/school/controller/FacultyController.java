package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @GetMapping("/byColor")
    public List<Faculty> byColor(@RequestParam String color) {
        return service.filterByColor(color);
    }

    @GetMapping("/byColorAndName")
    public List<Faculty> byColorAndName(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String color) {
        return service.filterByNameOrCalor(name, color);
    }
    @GetMapping("/{facultyId}/students")
    public Collection<Student> findByFaculty(@PathVariable long facultyId) {
        return service.get(facultyId).getStudents();
    }
    @GetMapping("/longName")
    public String longName() {
        return service.longName();
    }
}
