package model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.dao.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Post {
    private long id;
    private long userId;
    private String title;
    private String content;
    private LocalDateTime created;
    private List<Comment> comments;

    public Post (Long userId,String title,String content){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.created = LocalDateTime.now();
    }
}
