package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * ZooFrame Class, The main Frame of the program, contains the main Panel which controls the system.
 * @version 3.0
 * @author Tal vazana
 * @see AddAnimalDialog
 */
public class ZooFrame extends JFrame {

    private JMenuBar menuBar;
    private ZooPanel zooPanel;

    public ZooFrame(){
        super("Zoo-simulation Main Window");
//        BorderLayout myBorderLayout = new BorderLayout();
//        getContentPane().setLayout(myBorderLayout);

//        setPreferredSize(new Dimension(1000, 800));

//        setBounds(1000, 700, 1000, 700);
        this.zooPanel = new ZooPanel();
//        this.zooPanel.setSize(1000,1000);
//        this.zooPanel.setBounds(50,10,1000,700);
        this.menuBar = createMenuBar();

        this.setJMenuBar(menuBar);
        this.add(zooPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
//        zooPanel.manageZoo();
    }

    private JMenuBar createMenuBar(){
        menuBar = new JMenuBar();
        Font f = new Font("Georgia", Font.PLAIN, 16);
        UIManager.put("Menu.font", f);

        // initiating JMenu fields.
        JMenu fileMenu= new JMenu("file");
        JMenu BackgroundMenu = new JMenu("Background");
        JMenu HelpMenu = new JMenu("Help");

        // file menu items.
        JMenuItem ExitItem = new JMenuItem("Exit");
        fileMenu.add(ExitItem);

        // background menu items.
        JMenuItem ImageItem = new JMenuItem("Image");
        JMenuItem GreenItem = new JMenuItem("Green");
        JMenuItem NoneItem = new JMenuItem("None");

        BackgroundMenu.add(ImageItem);
        BackgroundMenu.add(GreenItem);
        BackgroundMenu.add(NoneItem);

        // help menu items.
        JMenuItem HelpItem = new JMenuItem("Help");
        HelpMenu.add(HelpItem);

        // adding menus to the menu bar.
        menuBar.add(fileMenu);
        menuBar.add(BackgroundMenu);
        menuBar.add(HelpMenu);

        // menuBar Listener
        MenuBarListener menuListener = new MenuBarListener();
        ExitItem.addActionListener(menuListener);
        ImageItem.addActionListener(menuListener);
        GreenItem.addActionListener(menuListener);
        NoneItem.addActionListener(menuListener);
        HelpItem.addActionListener(menuListener);

        return menuBar;
    }

    /**
     * MenuBarListener Class, performed to the upper menu bar
     */
    private class MenuBarListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println(command);
            switch (command) {
                case "Exit":
                    System.exit(1);

                case "Image":
                    zooPanel.setPanelBackground(command);
                    break;

                case "None":
                    zooPanel.setPanelBackground(command);
                    break;

                case "Green":
                    zooPanel.setPanelBackground(command);
                    break;

                case "Help":
                    //TODO add help contents
                    JOptionPane.showMessageDialog(ZooFrame.this,"Home Work 2 GUI","Message",JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZooFrame();
            }
        });
    }
}
