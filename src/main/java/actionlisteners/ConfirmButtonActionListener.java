package actionlisteners;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfirmButtonActionListener implements Controller {


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
//            PostServiceImpl postServiceImpl = new PostServiceImpl();
//            postServiceImpl.editPost(post, user, newTitle.getText(), content.getText());
//            frame.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
