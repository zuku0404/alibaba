package validate;

import model.domain.post.PostFinder;

public class PostValidator {
    private StringBuilder message = new StringBuilder();
    private PostFinder postFinder = new PostFinder();
    private String title;

    public PostValidator(String title) {
        this.title = title;
    }

    public boolean validatePost() {
        String exceptionMessage = checkConditions();
        if (exceptionMessage.isEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
    private String checkConditions() {
        if (!DataValidatorRegex.checkPostTitle(title)) {
            message.append("title is incorrect\n");
        }
        if (!postFinder.findPostByTitle(title).isEmpty()) {
            message.append("title exist\n");
        }
        return message.toString();
    }
}
