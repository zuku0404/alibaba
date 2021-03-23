package gui.user_logged;

import controller.PostController;
import model.dao.Post;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablePostsPanel {
    private PostController postController = new PostController();
    private final String[] header = new String[]{"title", "content", "created"};
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);

    public JTable getTable() {
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        showPostData(postController.findAllPost());
        ListSelectionModel listSelectionModel = table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        return table;
    }

    private void showPostData(List<Post> posts) {
        for (Post post : posts) {
            dtm.setColumnIdentifiers(header);
            dtm.addRow(new Object[]{
                    post.getTitle(), post.getContent(), post.getCreated()
            });
        }
    }
}
