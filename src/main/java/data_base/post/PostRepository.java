package data_base.post;

import model.dao.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post savePost(Post post); //TODO

    Post updatePost(Post post, String editedTitle, String editedContent);

    List<Post> findAllPost();

    void removePost(Post post);

    Optional<Post> findByTitle(String title);

    List<Post> findByUserId(Long userId);
}
