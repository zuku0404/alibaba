package gui.account;

import service.impl.AccountService;
import gui.main_view.BoardGuiUser;
import gui.Gui;
import model.dao.User;

import javax.swing.*;
import java.awt.*;

public class LoginGui implements Gui {
    @Override
    public void show(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 23, 15));
        JPanel bottomPanel = new JPanel();
        JLabel loginLabel = new JLabel("login: ");
        JTextField loginText = new JTextField(20);
        JLabel passwordLabel = new JLabel("password: ");
        JTextField passwordText = new JTextField(20);

        JButton logInButton = new JButton("log in");
        logInButton.addActionListener(actionEvent -> {
            try {
                AccountService accountService = new AccountService();
                User user = accountService.logIntoAccount(loginText.getText(), passwordText.getText());
                BoardGuiUser boardGuiUser = new BoardGuiUser(user);
                boardGuiUser.show();
                frame.dispose();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(actionEvent -> {
            BoardGuiUser boardGui = new BoardGuiUser(null);
            boardGui.show();
            frame.dispose();
        });

        topPanel.add(loginLabel);
        topPanel.add(loginText);
        topPanel.add(passwordLabel);
        topPanel.add(passwordText);
        bottomPanel.add(logInButton);
        bottomPanel.add(backButton);
        panel.add(topPanel);
        panel.add(bottomPanel);
        frame.getContentPane().add(panel);

        frame.setSize(700,150);
        frame.setVisible(true);
    }
}
