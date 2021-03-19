package model.domain.comment;

import data_base.comment.CommentsFinderDb;
import model.Comment;
import model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentFinder {
    private CommentsFinderDb commentsFinderDb = new CommentsFinderDb();
    private Post post;

    public CommentFinder(Post post) {
        this.post = post;
    }

    public List<Comment> findAllCommentsByPost() {
        return commentsFinderDb.findAllCommentByPost(post);
    }

    public Optional<Comment> findCommentByDate (LocalDateTime date){
        return findAllCommentsByPost().stream()
                .filter(comment -> comment.getCreated().equals(date))
                .findFirst();
    }

}
