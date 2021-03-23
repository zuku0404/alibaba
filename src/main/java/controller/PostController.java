package controller;

import data_base.post.PostRepositoryImpl;
import model.dao.Post;
import model.dao.User;
import validate.DataValidatorRegex;

import java.util.List;
import java.util.Optional;


public class PostController {
    private PostRepositoryImpl postRepository = new PostRepositoryImpl();
    private StringBuilder message = new StringBuilder();

    public Post createPost(User user, String title, String content) {
        validatePost(title);
        return postRepository.savePost(new Post(user.getId(), title, content));
    }

    public Post editPost(Post post, User user, String editedTitle, String editedContent) {
        isCurrentUserCreatePost(post.getUserId(), user.getId());
        isTitleChange(post.getTitle(), editedTitle);
        postRepository.findByTitle(editedTitle);
        return postRepository.updatePost(post, editedTitle, editedContent);
    }

    public Optional<Post> findPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findAllPost() {
        return postRepository.findAllPost();
    }

    public void deletePost(Post post, User user) {
        isCurrentUserCreatePost(post.getUserId(), user.getId());
        CommentController commentController = new CommentController();
        commentController.findAllCommentsByPost(post).forEach(comment -> commentController.deleteComment(comment,user));
        postRepository.removePost(post);
    }

    public List<Post> findByUserId(Long userId) {
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
        if (!DataValidatorRegex.checkPostTitle(title)) {
            message.append("title is incorrect\n");
        }
        if (findPostByTitle(title).isPresent()) {
            message.append("title exist\n");
        }
        return message.toString();
    }

}