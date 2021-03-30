package gui.main_view.table;

import service.impl.CommentServiceImpl;
import model.dao.Comment;
import model.dao.Post;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablePostWithComments {
    private final String[] header = new String[]{"content", "created"};
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);

    public JTable getTable(Post post) {
        table.setDefaultEditor(Object.class, null);
        CommentServiceImpl commentServiceImpl = new CommentServiceImpl();
        showPostData(commentServiceImpl.findAllCommentsByPost(post));
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
