package gui.user_logged;

import controller.CommentController;
import model.dao.Post;
import model.dao.User;

import javax.swing.*;
import java.awt.*;

public class CommentCreatorGui {
    private User user;
    private Post post;

    public CommentCreatorGui(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public void show(){
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentText = new JTextArea(8,30);
        contentText.setVisible(true);
        JScrollPane scrollContent = new JScrollPane (contentText);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JButton createButton = new JButton("create comment");
        createButton.addActionListener(actionEvent -> {
            CommentController commentController = new CommentController();
            commentController.createComment(user,post, contentText.getText());
            frame.dispose();
        });
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(actionEvent -> {
            PostWithCommentsGui postWithCommentsGui = new PostWithCommentsGui(post,user);
            postWithCommentsGui.show();
            frame.dispose();
        });

        frame.getContentPane().add(mainPanel);
        mainPanel.add(thirdPanel);
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);

        thirdPanel.add(contentLabel);
        firstPanel.add(contentText);
        secondPanel.add(createButton);
        secondPanel.add(closeButton);

        frame.pack();
        frame.setVisible(true);
    }
}
