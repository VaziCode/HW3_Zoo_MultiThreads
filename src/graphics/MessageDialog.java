package graphics;

import javax.swing.*;

public class MessageDialog extends JDialog {

    public static void errorMessage(String message){
        JOptionPane.showMessageDialog(null,
                message, "Error Message",
                JOptionPane.ERROR_MESSAGE);
    }
    public static void stateMessage(String message){
        JOptionPane.showMessageDialog(null,
                message, "Error Message",
                JOptionPane.ERROR_MESSAGE);
    }

}
