package gui.user_logged;

import controller.PostController;
import model.dao.Post;
import model.dao.User;

import javax.swing.*;
import java.awt.*;

public class PostEditorGui {
    private User user;
    private Post post;

    public PostEditorGui(Post post, User user) {
        this.user = user;
        this.post = post;
    }

    public void show() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel titleLabel = new JLabel("title");
        JTextArea newTitle = new JTextArea(4,30);
        newTitle.setLineWrap(true);
        newTitle.setVisible(true);
        JScrollPane scrollTitle = new JScrollPane (newTitle);
        scrollTitle.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollTitle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JLabel contentLabel = new JLabel("content");
        JTextArea content = new JTextArea(8,30);
        content.setVisible(true);
        JScrollPane scrollContent = new JScrollPane (content);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JButton confirmButton = new JButton("confirm");
        confirmButton.addActionListener(actionEvent -> {
            try {
                PostController postController = new PostController();
                postController.editPost( post, user,newTitle.getText(), content.getText());
                frame.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        JButton closeButton = new JButton("close");
        closeButton.addActionListener(actionEvent -> {
            PostWithCommentsGui postWithCommentsGui = new PostWithCommentsGui(post,user);
            postWithCommentsGui.show();
            frame.dispose();
        });

        frame.getContentPane().add(mainPanel);
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        firstPanel.add(titleLabel);
        firstPanel.add(newTitle);
        secondPanel.add(contentLabel);
        secondPanel.add(content);
        thirdPanel.add(confirmButton);
        thirdPanel.add(closeButton);
        frame.setVisible(true);
        frame.pack();
    }
}
