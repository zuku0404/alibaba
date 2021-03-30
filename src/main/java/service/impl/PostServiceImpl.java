package service.impl;

import service.PostService;
import data_base.post.PostRepository;
import data_base.post.PostRepositoryImpl;
import model.dao.Post;
import model.dao.User;
import validate.DataValidatorRegex;

import java.util.List;
import java.util.Optional;


public class PostServiceImpl implements PostService {

    private final PostRepository postRepository = new PostRepositoryImpl();

    public Post createPost(Long userId, String title, String content) {
        validatePost(title);
        Post post = new Post(userId, title, content);
        return postRepository.savePost(post);
    }

    public Post editPost(Post post, User user, String editedTitle, String editedContent) {
        isCurrentUserCreatePost(post.getUserId(), user.getId());
        isTitleChange(post.getTitle(), editedTitle);
        postRepository.findByTitle(editedTitle);
        return postRepository.updatePost(post, editedTitle, editedContent);
    }

    public Optional<Post> getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> getAllPost() {
        return postRepository.findAllPost();
    }

    public void deletePost(Post post, User user) {
        isCurrentUserCreatePost(post.getUserId(), user.getId());
        CommentServiceImpl commentServiceImpl = new CommentServiceImpl();
        commentServiceImpl.findAllCommentsByPost(post).forEach(comment -> commentServiceImpl.deleteComment(comment,user));
        postRepository.removePost(post);
    }

    public List<Post> getPostByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    private void validatePost(String title) {
        String exceptionMessage = checkConditions(title);
        if (!exceptionMessage.isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void isTitleChange(String postTitle,String editedTitle) {
        if (!postTitle.equals(editedTitle)) {
            validatePost(editedTitle);
        }
    }

    private void isCurrentUserCreatePost(Long postId, Long userId) {
        if(!postId.equals(userId)){
            throw new IllegalArgumentException("it is not your post");
        }
    }

    private String checkConditions(String title) {
        StringBuilder message = new StringBuilder();
        if (!DataValidatorRegex.checkPostTitle(title)) {
            message.append("title is incorrect\n");
        }
        if (getPostByTitle(title).isPresent()) {
            message.append("title exist\n");
        }
        return message.toString();
    }

}