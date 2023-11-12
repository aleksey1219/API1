package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty remove(long id) {
        var entity = facultyRepository.findById(id).orElse(null);
        if (entity != null) {
            facultyRepository.delete(entity);
        }else logger.error("entity null");
        return entity;
    }

    public Faculty update(Faculty faculty) {
        return facultyRepository.findById(faculty.getId())
                .map(entity -> facultyRepository.save(faculty))
                .orElse(null);
    }

    public List<Faculty> filterByColor(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public List<Faculty> filterByNameOrCalor(String name, String color) {
        return facultyRepository.findAllByNameOrColorIgnoreCase(name, color);
    }
}