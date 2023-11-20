package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private StudentRepository studentRepository;
    private Queue<Object> printQueue = new ConcurrentLinkedQueue<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student Student) {
        logger.info("вызван метод add");
        return studentRepository.save(Student);
    }

    public Student get(long id) {
        logger.info("вызван метод get");
        return studentRepository.findById(id).orElse(null);
    }

    public Student remove(long id) {
        var entity = studentRepository.findById(id).orElse(null);
        if (entity != null) {
            studentRepository.delete(entity);
        } else logger.error("entity null");
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

    public List<String> sortedName() {
        return studentRepository.findAll().parallelStream()
                .map(s -> s.getName().toUpperCase())
                .filter(n -> n.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }
    public double AvgAge() {
        return studentRepository.findAll().parallelStream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow();
    }
    public void printNonSync() {
        var students = studentRepository.findAll();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        Thread t1 = new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        });
        t2.start();
        t1.start();
        System.out.println("-----------------");
    }

    public void printSync() {
        var students = studentRepository.findAll();

        printQueue.offer(students.get(0));
        printQueue.offer(students.get(1));
        printQueue.offer(students.get(2));
        printQueue.offer(students.get(3));
        printQueue.offer(students.get(4));
        printQueue.offer(students.get(5));

        Thread t1 = new Thread(this::printFromQueue);
        Thread t2 = new Thread(this::printFromQueue);

        t1.start();
        t2.start();
    }

    private void printFromQueue() {
        while (!printQueue.isEmpty()) {
            synchronized (this) {
                Object student = printQueue.poll();
                if (student != null) {
                    printSynchronized(student);
                }
            }
        }
    }

    private void printSynchronized(Object o) {
        System.out.println(o.toString());
    }
}
