package gui.user_logged_out;

import gui.Gui;
import gui.TablePostsPanel;
import gui.account.LoginGui;
import gui.account.RegisterGui;
import model.Post;
import model.domain.post.PostFinder;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGui implements Gui {

    @Override
    public void show() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JPanel firstPanel = new JPanel();
        JPanel secondPanel = new JPanel();

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
                    PostGui postGui = new PostGui(post);
                    postGui.show();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);
        firstPanel.add(scrollPane);

        JButton logInButton = new JButton("log in");
        logInButton.addActionListener(actionEvent -> {
            LoginGui loginGui = new LoginGui();
            loginGui.show();
            frame.dispose();
        });

        JButton signUpButton = new JButton("sign up");
        signUpButton.addActionListener(actionEvent -> {
            RegisterGui registerGui = new RegisterGui();
            registerGui.show();
            frame.dispose();
        });

        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        secondPanel.add(logInButton);
        secondPanel.add(signUpButton);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}