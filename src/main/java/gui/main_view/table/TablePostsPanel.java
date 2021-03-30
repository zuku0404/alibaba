package gui.main_view.table;

import service.impl.PostServiceImpl;
import model.dao.Post;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablePostsPanel {
    private PostServiceImpl postServiceImpl = new PostServiceImpl();
    private final String[] header = new String[]{"title", "content", "created"};
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);

    public JTable getTable() {
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        showPostData(postServiceImpl.getAllPost());
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
