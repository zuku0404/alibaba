package gui.user_logged_out;

import gui.TablePostWithComments;
import model.Post;

import javax.swing.*;
import java.awt.*;

public class PostGui {
    private JTable table;
    private Post post;

    public PostGui(Post post) {
        this.post = post;
    }

    public void show() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 15));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        JPanel thirdPanel = new JPanel();
        JPanel fourthPanel = new JPanel();

        JLabel titleLabel = new JLabel("title");
        JTextArea titlePost = new JTextArea("Title",2,65);
        titlePost.setText(post.getTitle());
        titlePost.setEditable(false);
        titlePost.setLineWrap(true);
        titlePost.setVisible(true);
        JScrollPane scrollTitle = new JScrollPane (titlePost);
        scrollTitle.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollTitle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JLabel contentLabel = new JLabel("content");
        JTextArea contentPost = new JTextArea("Content",8,65);
        contentPost.setText(post.getContent());
        contentPost.setEditable(false);
        contentPost.setVisible(true);
        JScrollPane scrollContent = new JScrollPane (contentPost);
        scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        TablePostWithComments tablePostWithComments = new TablePostWithComments();
        table = tablePostWithComments.getTable(post);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);
        thirdPanel.add(scrollPane);

        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        mainPanel.add(fourthPanel);
        firstPanel.add(titleLabel);
        firstPanel.add(scrollTitle);
        secondPanel.add(contentLabel);
        secondPanel.add(scrollContent);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
