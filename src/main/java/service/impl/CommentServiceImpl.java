package service.impl;

import service.CommentService;
import data_base.comment.CommentRepositoryImpl;
import model.dao.Comment;
import model.dao.Post;
import model.dao.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryImpl commentRepository = new CommentRepositoryImpl();

    public Comment createComment(User user, Post post , String content) {
        return commentRepository.saveComment(new Comment(user.getId(), post.getId(), content));
    }

    public Comment editComment(Comment comment, User user, String editedContent) {
        isCurrentUserCreateComment(comment.getUserId(),user.getId());
            return commentRepository.updateComment(comment, editedContent);
        }

    public List<Comment> findAllCommentsByPost(Post post) {
        return commentRepository.findAllCommentByPost(post);
    }

    public Optional<Comment> findCommentByDate (LocalDateTime date){
        return commentRepository.findByDate(date);
    }

    public void deleteComment(Comment comment,User user) {
        isCurrentUserCreateComment(comment.getUserId(), user.getId());
        commentRepository.removeComment(comment);
    }

    private void isCurrentUserCreateComment(long idUserComment, long idCurrentUser) {
        if (idUserComment != idCurrentUser){
            throw new IllegalArgumentException("It is not your comment! ");
        }
    }
}
