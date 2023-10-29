package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-id", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Пуффендуй");
        faculty.setColor("Желтый");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-color", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByNameOrColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-name-or-color", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/students-by-faculty-id", String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Когтевран");
        faculty.setColor("Синий");

        ResponseEntity<Void> resp = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.DELETE, null, Void.class);
    }

    @Test
    public void testPutStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Слизерин");
        faculty.setColor("Зеленый");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }
}