package service;

import model.dao.Post;
import model.dao.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Long userId, String title, String content);

    Post editPost(Post post, User user, String editedTitle, String editedContent);

    Optional<Post> getPostByTitle(String title);

    List<Post> getAllPost();

    void deletePost(Post post, User user);

    List<Post> getPostByUserId(Long userId);

}
