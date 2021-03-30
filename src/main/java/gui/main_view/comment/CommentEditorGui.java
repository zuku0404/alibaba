package gui.main_view.comment;

import service.impl.CommentServiceImpl;
import gui.main_view.post.PostWithCommentsGui;
import model.dao.Comment;
import model.dao.Post;
import model.dao.User;

import javax.swing.*;
import java.awt.*;

public class CommentEditorGui {
    private User user;
    private Comment comment;
    private Post post;

    public CommentEditorGui(User user, Comment comment, Post post) {
        this.user = user;
        this.comment = comment;
        this.post = post;
    }

    public void show() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JLabel contentLabel = new JLabel("Content: ");
        JTextArea contentText = new JTextArea(8,30);
        contentText.setVisible(true);
        JScrollPane scrollContent = new JScrollPane (contentText);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JButton confirmButton = new JButton("confirm");
        confirmButton.addActionListener(actionEvent -> {
            try {
                CommentServiceImpl commentServiceImpl = new CommentServiceImpl();
                commentServiceImpl.editComment(comment, user, contentText.getText());
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
        firstPanel.add(contentLabel);
        secondPanel.add(contentText);
        thirdPanel.add(confirmButton);
        thirdPanel.add(closeButton);
        frame.setVisible(true);
        frame.pack();
    }
}