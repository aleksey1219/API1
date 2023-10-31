package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student add(Student Student) {
        return studentRepository.save(Student);
    }

    public Student get(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student remove(long id) {
        var entity = studentRepository.findById(id).orElse(null);
        if (entity != null) {
            studentRepository.delete(entity);
        }
        return entity;
    }

    public Student update(Student Student) {
        return studentRepository.findById(Student.getId())
                .map(entity -> studentRepository.save(Student))
                .orElse(null);
    }
    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public List<Student> filerByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Integer findAllStudentsInSchool() {
        return studentRepository.findAllStudentsInSchool();
    }

    public double AvgAgeStudent() {
        return studentRepository.AvgAgeStudent();
    }

    public List<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }
}
