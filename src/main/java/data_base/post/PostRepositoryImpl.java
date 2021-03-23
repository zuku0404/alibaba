package data_base.post;

import model.dao.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public Post savePost(Post post) {
        PostCreatorDb postCreatorDb = new PostCreatorDb();
        return postCreatorDb.savePost(post);
    }

    @Override
    public Post updatePost(Post post, String editedTitle, String editedContent) {
        PostEditorDb postEditorDb = new PostEditorDb();
        return postEditorDb.updatePost(post, editedTitle, editedContent);
    }

    @Override
    public List<Post> findAllPost() {
        PostFinderDb postFinderDb = new PostFinderDb();
        return postFinderDb.findAllPost();
    }

    @Override
    public void removePost(Post post) {
        PostRemoverDb postRemoverDb = new PostRemoverDb();
        postRemoverDb.removePost(post);
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        PostFinderDb postFinderDb = new PostFinderDb();
        return postFinderDb.findByTitle(title);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        PostFinderDb postFinderDb = new PostFinderDb();
        return postFinderDb.findByUserId(userId);
    }
}
