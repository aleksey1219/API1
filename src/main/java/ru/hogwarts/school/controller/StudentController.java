package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public Student remove(@PathVariable long id) {
     return service.remove(id);
    }
    @GetMapping("/byAge")
    public List<Student> byAge(@RequestParam int age) {
        return service.findStudentByAge(age);
    }
    @GetMapping("/{studentId}/faculty")
    public Faculty facultyByStudent(@PathVariable long studentId) {
        return service.get(studentId).getFaculty();
    }
    @GetMapping("/byAgeBetween")
    public List<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return service.filerByAgeBetween(min, max);
    }

    @GetMapping("/findAllStudentsInSchool")
    public Integer findAllStudentsInSchool() {
        return service.findAllStudentsInSchool();
    }

    @GetMapping("/AvgAgeStudent")
    public Double AvgAgeStudent() {
        return service.AvgAgeStudent();
    }

    @GetMapping("/findLastFiveStudents")
    public List<Student> findLastFiveStudents() {
        return service.findLastFiveStudents();
    }

    @GetMapping("/sortedName")
    public List<String> sortedName() {
        return service.sortedName();
    }
    @GetMapping("/AvgAge")
    public double AvgAge() {
        return service.AvgAge();
    }
    @GetMapping("/sum")
    public int Sum() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
    }
