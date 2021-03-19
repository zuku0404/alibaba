package gui;

import model.Comment;
import model.Post;
import model.domain.comment.CommentFinder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablePostWithComments {
    private final String[] header = new String[]{"content", "created"};
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);

    public JTable getTable(Post post) {
        table.setDefaultEditor(Object.class, null);
        CommentFinder commentFinder = new CommentFinder(post);
        showPostData(commentFinder.findAllCommentsByPost());
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        return table;
    }

    private void showPostData(List<Comment> comments) {
        for (Comment comment : comments) {
            dtm.setColumnIdentifiers(header);
            dtm.addRow(new Object[]{
                    comment.getContent(), comment.getCreated()
            });
        }
    }
}
