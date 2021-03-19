package model.domain.post;

import data_base.post.PostEditorDb;
import model.Post;
import model.User;
import validate.PostValidator;


public class PostEditor {
    private PostEditorDb editorDb = new PostEditorDb();

    private String editedTitle;
    private String editedContent;
    private Post post;
    private User user;

    public PostEditor(String editedTitle, String editedContent, Post post, User user) {
        this.editedTitle = editedTitle;
        this.editedContent = editedContent;
        this.post = post;
        this.user = user;
    }

    public Post editPost() {
        if (isCurrentUserCreatePost()) {
            isTitleChange();
            return editorDb.updatePost(post, editedTitle, editedContent);
        }
        throw new IllegalArgumentException("It is not your post! ");
    }

    private boolean isTitleChange() {
        if (post.getTitle().equals(editedTitle)) {
            return false;
        } else {
            PostValidator postValidator = new PostValidator(editedTitle);
            return postValidator.validatePost();
        }
    }

    private boolean isCurrentUserCreatePost() {
        return post.getUserId() == user.getId();
    }
}
