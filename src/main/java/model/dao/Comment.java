package model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Comment {
    private long id;
    private long userId;
    private long postId;
    private String content;
    private LocalDateTime created;

    public Comment(Long userId, Long postId, String content){
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.created = LocalDateTime.now();

    }
}
