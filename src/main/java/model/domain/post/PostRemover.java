package model.domain.post;

import data_base.post.PostRemoverDb;
import model.Post;
import model.User;
import model.domain.comment.CommentFinder;
import model.domain.comment.CommentRemover;

public class PostRemover {
    private PostRemoverDb postRemoverDb = new PostRemoverDb();


    public void deletePost(Post post, User user) {
        if (user.getId() == (post.getUserId())) {
//            CommentRemover commentRemover = new CommentRemover(post);
            postRemoverDb.removePost(post);
        } else
            throw new IllegalArgumentException("it is not your post");
    }
}
