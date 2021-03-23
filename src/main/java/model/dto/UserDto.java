package model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private int age;
    private LocalDateTime created;
}
