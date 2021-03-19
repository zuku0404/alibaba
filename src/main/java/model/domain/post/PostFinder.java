package model.domain.post;

import data_base.post.PostFinderDb;
import model.Post;

import java.util.List;
import java.util.Optional;

public class PostFinder {
    private PostFinderDb finderDb = new PostFinderDb();
    public Optional<Post> findPostByTitle(String title) {
        return findAllPost().stream()
                .filter(post -> post.getTitle().equals(title))
                .findFirst();
    }
    public List<Post> findAllPost (){
        return finderDb.findAllPost();
    }
}
