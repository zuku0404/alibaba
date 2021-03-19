package model.domain.comment;

import data_base.comment.CommentsCreatorDb;
import model.Comment;
import model.Post;
import model.User;


public class CommentCreator {
    private CommentsCreatorDb commentsCreatorDb = new CommentsCreatorDb();
    private String content;
    private Post post;
    private User user;

    public CommentCreator(Post post, User user, String content) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public Comment createComment() {
        return commentsCreatorDb.saveComment(new Comment(user.getId(), post.getId(), content));
    }
}

