package model.domain.post;

import data_base.post.PostCreatorDb;
import model.Post;
import model.User;
import validate.PostValidator;


public class PostCreator {
    private String title;
    private String content;
    private User user;
    private PostCreatorDb postCreatorDb = new PostCreatorDb();

    public PostCreator(User user, String title, String content) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post createPost() {
        PostValidator postValidator = new PostValidator(title);
        postValidator.validatePost();
        return postCreatorDb.savePost(new Post(user.getId(), title, content));
    }
}

