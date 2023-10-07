package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    public Map<Long, Faculty> Facultys = new HashMap<>();
    private long chethik = 0;
    public Faculty add (Faculty faculty) {
        faculty.setId(chethik);
        Facultys.put(chethik, faculty);
        chethik++;
        return faculty;
    }

    public Faculty get(long id) {
        return Facultys.get(id);
    }

    public boolean remove(long id) {
        return Facultys.remove(id) != null;
    }

    public Faculty update(Faculty faculty) {
        if (Facultys.containsKey(faculty.getId())) {
            Facultys.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Collection<Faculty> filterByColor(String color) {
        return Facultys.values().stream()
                .filter(s -> s.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}
