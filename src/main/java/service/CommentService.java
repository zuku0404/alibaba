package service;

import model.dao.Comment;
import model.dao.Post;
import model.dao.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(User user, Post post , String content);

    Comment editComment(Comment comment, User user, String editedContent);

    List<Comment> findAllCommentsByPost(Post post);

    Optional<Comment> findCommentByDate (LocalDateTime date);

    void deleteComment(Comment comment,User user);
}
