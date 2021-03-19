package model.domain.comment;

import data_base.comment.CommentsRemoverDb;
import model.Comment;
import model.Post;
import model.User;

public class CommentRemover {
    private CommentsRemoverDb commentsRemoverDb = new CommentsRemoverDb();
    private Comment comment;
    private User user;

    public CommentRemover(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public void deleteComment() {
        if (user.getId() == (comment.getUserId())) {
            commentsRemoverDb.deleteComment(comment);
        }
        else
            throw new IllegalArgumentException("it is not your comment");
    }
    public void deleteAllCommentsToPost(Post post){
        CommentFinder commentFinder = new CommentFinder(post);
        commentFinder.findAllCommentsByPost()
                .forEach(comment1->deleteComment());
    }

}
