package gui.user_logged;

import controller.PostController;
import model.dao.User;

import javax.swing.*;
import java.awt.*;

public class PostCreatorGui {
    public void show(User user){
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel titleLabel = new JLabel("title");
        JTextArea titleText = new JTextArea("Title",4,30);
        titleText.setLineWrap(true);
        titleText.setVisible(true);
        JScrollPane scrollTitle = new JScrollPane (titleText);
        scrollTitle.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollTitle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JLabel contentLabel = new JLabel("content");
        JTextArea contentText = new JTextArea("Content",8,30);
        contentText.setVisible(true);
        JScrollPane scrollContent = new JScrollPane (contentText);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JButton createButton = new JButton("create post");
        createButton.addActionListener(actionEvent -> {
            try {
                PostController postController = new PostController();
                postController.createPost(user, titleText.getText(), contentText.getText());
                frame.dispose();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(actionEvent -> frame.dispose());

        frame.getContentPane().add(mainPanel);
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);

        firstPanel.add(titleLabel);
        firstPanel.add(titleText);
        secondPanel.add(contentLabel);
        secondPanel.add(contentText);
        thirdPanel.add(createButton);
        thirdPanel.add(closeButton);

        frame.pack();
        frame.setSize(450,300);
        frame.setVisible(true);
    }

}
