package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long chethik = 0;

    public Student add(Student student) {
        student.setId(chethik);
        students.put(chethik, student);
        chethik++;
        return student;
    }

    public Student get(long id) {
        return students.get(id);
    }

    public boolean remove(long id) {
        return students.remove(id) != null;
    }

    public Student update(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Collection<Student> filterByAge(int age) {
        return students.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }
}
