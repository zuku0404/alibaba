package gui.user_logged;

import gui.user_logged_out.BoardGui;
import gui.TablePostsPanel;
import model.Post;
import model.User;
import model.domain.post.PostFinder;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGuiUser {
    private User user;

    public BoardGuiUser(User user) {
        this.user = user;
    }

    public void show() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel firstPanel = new JPanel();
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        JPanel thirdPanel = new JPanel();

        TablePostsPanel tablePostsPanel = new TablePostsPanel();
        JTable table = tablePostsPanel.getTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int indexRow = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    TableModel model = table.getModel();
                    String title = model.getValueAt(indexRow, 0).toString();

                    PostFinder postFinder = new PostFinder();
                    Post post = postFinder.findPostByTitle(title).orElseThrow();
                    PostWithCommentsGui postWithCommentsGui = new PostWithCommentsGui(post,user);
                    postWithCommentsGui.show();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);
        firstPanel.add(scrollPane);

        JLabel userLabel = new JLabel("User: " + user.getLogin());

        JButton createPost = new JButton("create post");
        createPost.addActionListener(actionEvent -> {
            try {
                PostCreatorGui postCreatorGui = new PostCreatorGui();
                postCreatorGui.show(user);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        JButton logOutButton = new JButton("log out");
        logOutButton.addActionListener(actionEvent -> {
            int response = JOptionPane.showConfirmDialog(null, "are you sure you want log out?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                user = null;
                frame.dispose();
                BoardGui boardGui = new BoardGui();
                boardGui.show();
            }
        });

        JButton refreshButton = new JButton("refresh");
        refreshButton.addActionListener(actionEvent -> {
            frame.dispose();
            BoardGuiUser boardGuiUser = new BoardGuiUser(user);
            boardGuiUser.show();
        });

        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        mainPanel.add(firstPanel);
        secondPanel.add(userLabel);
        thirdPanel.add(createPost);
        thirdPanel.add(logOutButton);
        thirdPanel.add(refreshButton);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

