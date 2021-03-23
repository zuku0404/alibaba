package model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import model.dao.Comment;
import model.dao.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private int age;
    private LocalDateTime created;

    private List<Post> posts;
    private List<Comment> comments;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.created = LocalDateTime.now();
    }

}
