package ru.hogwarts.school.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class AvatarNotFoundException extends RuntimeException {
}
