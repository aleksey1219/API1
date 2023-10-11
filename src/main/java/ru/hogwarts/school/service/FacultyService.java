package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyService() {

    }

    public Faculty add (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        return facultyRepository.findById(id).get();
    }

    public void remove(long id) {
        facultyRepository.getById(id);
    }

    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Collection<Faculty> filterByColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(s -> s.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
