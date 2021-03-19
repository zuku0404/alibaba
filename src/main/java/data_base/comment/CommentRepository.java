package data_base.comment;

import model.Comment;
import model.Post;

import java.util.List;

public interface CommentRepository {
    Comment saveComment (Comment comment);
    Comment updateComment(Comment comment, String content);
    List<Comment> findAllCommentByPost(Post post);
    void removeComment(Comment comm);
}
