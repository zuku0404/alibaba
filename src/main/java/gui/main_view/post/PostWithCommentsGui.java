package gui.main_view.post;

import service.impl.CommentServiceImpl;
import service.impl.PostServiceImpl;
import gui.main_view.table.TablePostWithComments;
import gui.main_view.comment.CommentCreatorGui;
import gui.main_view.comment.CommentEditorGui;
import model.dao.Comment;
import model.dao.Post;
import model.dao.User;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.LocalDateTime;

public class PostWithCommentsGui {
    private JTable table;
    private User user;
    private Post post;

    public PostWithCommentsGui(Post post, User user) {
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
        JPanel thirdPanel = new JPanel();
        JPanel fourthPanel = new JPanel();
        JPanel fifthPanel = new JPanel();

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
        scrollPane.setPreferredSize(new Dimension(800, 350));
        table.setFillsViewportHeight(true);
        thirdPanel.add(scrollPane);

        //BUTTONS TO POST
        JButton editPostButton = new JButton("edit post");
        editPostButton.addActionListener(actionEvent -> {
            PostEditorGui postEditorGui = new PostEditorGui(post, user);
            postEditorGui.show();
            frame.dispose();
        });

        JButton deletePostButton = new JButton("delete post");
        deletePostButton.addActionListener(actionEvent -> {
            try {
                int response = JOptionPane.showConfirmDialog(null, "are you sure you want delete post?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    PostServiceImpl postServiceImpl = new PostServiceImpl();
                    postServiceImpl.deletePost(post,user);
                    frame.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        // BUTTON TO COMMENTS

        JButton editCommentButton = new JButton("edit comment");
        editCommentButton.addActionListener(actionEvent -> {
            Comment actualComment = getActualComment();
            CommentEditorGui commentEditorGui = new CommentEditorGui(user,actualComment,post);
            commentEditorGui.show();
            frame.dispose();
        });

        JButton createCommentButton = new JButton("create comment");
        createCommentButton.addActionListener(actionEvent -> {
            CommentCreatorGui commentCreatorGui = new CommentCreatorGui(user,post);
            commentCreatorGui.show();
            frame.dispose();
        });

        JButton deleteCommentButton = new JButton("delete comment");
        deleteCommentButton.addActionListener(actionEvent -> {
            try {
                int response = JOptionPane.showConfirmDialog(null, "are you sure you want delete post?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    Comment actualComment = getActualComment();
                    CommentServiceImpl commentServiceImpl = new CommentServiceImpl();
                    commentServiceImpl.deleteComment(actualComment,user);
                    frame.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });


        firstPanel.add(titleLabel);
        firstPanel.add(scrollTitle);
        secondPanel.add(contentLabel);
        secondPanel.add(scrollContent);

        if(user!=null) {
            mainPanel.add(firstPanel);
            mainPanel.add(secondPanel);
            mainPanel.add(fourthPanel);
            mainPanel.add(thirdPanel);
            mainPanel.add(fifthPanel);


            fourthPanel.add(editPostButton);
            fourthPanel.add(deletePostButton);
            fifthPanel.add(createCommentButton);
            fifthPanel.add(editCommentButton);
            fifthPanel.add(deleteCommentButton);
        }
        else {
            mainPanel.add(firstPanel);
            mainPanel.add(secondPanel);
            mainPanel.add(thirdPanel);
            mainPanel.add(fourthPanel);
        }
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    private Comment getActualComment(){
        int indexSelectedRow = table.getSelectedRow();
        TableModel model = table.getModel();
        LocalDateTime commentDataCreated = LocalDateTime.parse(model.getValueAt(indexSelectedRow, 1).toString());
        CommentServiceImpl commentServiceImpl = new CommentServiceImpl();
        return commentServiceImpl.findCommentByDate(commentDataCreated).orElseThrow();
    }
}
