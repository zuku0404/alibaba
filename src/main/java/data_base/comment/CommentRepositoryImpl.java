package data_base.comment;

import model.dao.Comment;
import model.dao.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment saveComment(Comment comment) {
        CommentsCreatorDb commentsCreatorDb = new CommentsCreatorDb();
        return commentsCreatorDb.saveComment(comment);
    }

    @Override
    public Comment updateComment(Comment comment, String content) {
        CommentsEditorDB commentsEditorDB = new CommentsEditorDB();
        return commentsEditorDB.updateComment(comment, content);
    }

    @Override
    public List<Comment> findAllCommentByPost(Post post) {
        CommentsFinderDb commentsFinderDb = new CommentsFinderDb();
        return commentsFinderDb.findAllCommentByPost(post);
    }

    @Override
    public void removeComment(Comment comment) {
        CommentsRemoverDb commentsRemoverDb = new CommentsRemoverDb();
        commentsRemoverDb.deleteComment(comment);
    }

    @Override
    public Optional<Comment> findByDate(LocalDateTime created) {
        CommentsFinderDb commentsFinderDb = new CommentsFinderDb();
        return commentsFinderDb.findByDate(created);
    }
}

