package gui.account;

import service.impl.UserServiceImpl;
import gui.main_view.BoardGuiUser;
import gui.Gui;

import javax.swing.*;
import java.awt.*;

public class RegisterGui implements Gui {

    public void show() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JPanel firsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 5));
        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 23, 5));
        JPanel fourthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel loginLabel = new JLabel("login: ");
        JTextField loginText = new JTextField(20);
        JLabel passwordLabel = new JLabel("password: ");
        JTextField passwordText = new JTextField(20);
        JLabel emailLabel = new JLabel("email: ");
        JTextField emailText = new JTextField(20);
        JButton confirmDataButton = new JButton("confirm");
        confirmDataButton.addActionListener(actionEvent -> {
            try {
                UserServiceImpl userServiceImpl = new UserServiceImpl();
                userServiceImpl.createUser(loginText.getText(), passwordText.getText(), emailText.getText());
                JOptionPane.showMessageDialog(null, "Account has been created");
                BoardGuiUser boardGui = new BoardGuiUser(null);
                boardGui.show();
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(actionEvent -> {
            BoardGuiUser boardGui = new BoardGuiUser(null);
            boardGui.show();
            frame.dispose();
        });

        firsPanel.add(loginLabel);
        firsPanel.add(loginText);
        secondPanel.add(passwordLabel);
        secondPanel.add(passwordText);
        thirdPanel.add(emailLabel);
        thirdPanel.add(emailText);
        fourthPanel.add(confirmDataButton);
        fourthPanel.add(backButton);

        panel.add(firsPanel);
        panel.add(secondPanel);
        panel.add(thirdPanel);
        panel.add(fourthPanel);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(350, 250);
    }
}
