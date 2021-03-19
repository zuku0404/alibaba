package model.domain.comment;

import data_base.comment.CommentsEditorDB;
import model.Comment;
import model.User;


public class CommentEditor {
    private CommentsEditorDB editorDb = new CommentsEditorDB();

    private String editedContent;
    private Comment comment;
    private User user;

    public CommentEditor(String editedContent, Comment comment, User user) {
        this.editedContent = editedContent;
        this.comment = comment;
        this.user = user;
    }

    public Comment editComment() {
        if (isCurrentUserCreateComment()) {
            return editorDb.updateComment(comment, editedContent);
        }
        throw new IllegalArgumentException("It is not your comment! ");
    }

    private boolean isCurrentUserCreateComment() {
        return comment.getUserId() == user.getId();
    }
}
