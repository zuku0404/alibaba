package data_base.comment;

import model.dao.Comment;
import model.dao.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment saveComment (Comment comment);
    Comment updateComment(Comment comment, String content);
    List<Comment> findAllCommentByPost(Post post);
    void removeComment(Comment comm);
    Optional<Comment> findByDate(LocalDateTime created);
}
