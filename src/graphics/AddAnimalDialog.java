package graphics;

import animals.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * AddAnimalDialog Class, Adding new animal to the Zoo By initialise animal's properties
 * @version 3.0
 * @author Tal vazana
 * @see
 */
public class AddAnimalDialog extends JDialog implements ActionListener{

    private Container container;
    private JComboBox typeComboBox;
    private JComboBox colorComboBox;
    private JSlider horSlider;
    private JSlider verSlider;
    private JLabel title;
    private JTextArea tout;
    private final JLabel typeLabel;
    private final JLabel nameLabel;
    private final JLabel colorLabel;
    private final JLabel sizeLabel;
    private final JLabel hSpeedLabel;
    private final JLabel vSpeedLabel;
    private JTextField nameTextField;
    private JTextField sizeTextField;
    private final JButton saveBtn;
    private final JButton cancelBtn;
    private String placeholder;
    private String animalType;
    private String defaultName;
    private String animalColor;
    private String animalName;
    private  int animalSize;
    private int animal_hor_speed;
    private int animal_ver_speed;
    private JLabel imgLabel;
    private ImageIcon imgIcon;
    private static final String[] ANIMAL_TYPES = {"None","Lion", "Elephant", "Giraffe", "Bear", "Turtle"};
    private static final String[]  ANIMAL_COLORS = {"Natural", "Red", "Blue"};
    private static final Font FONT = new Font("georgia", Font.PLAIN, 16);
    private Animals animalsArr;
    private  ZooPanel zooPanel;
    String imgPath;

    public AddAnimalDialog(ZooPanel panel) {

        this.zooPanel = panel;
        this.animalsArr = zooPanel.getAnimalsArr();
        this.setModal(true);
        setBounds(550, 220, 800, 600);
        setResizable(false);
        setTitle("Add Animal Dialog");

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Add New Animal To The Zoo");
        title.setFont(new Font("Curior", Font.PLAIN, 22));
        title.setSize(350, 30);
        title.setLocation(250, 30);
        container.add(title);

        //animal type
        typeLabel = new JLabel("Animal Type : ");
        typeLabel.setFont(FONT);
        typeLabel.setSize(150, 20);
        typeLabel.setLocation(50, 100);
        container.add(typeLabel);

        typeComboBox = new JComboBox(ANIMAL_TYPES);
        typeComboBox.setSize(100, 25);
        typeComboBox.setLocation(200, 100);
        container.add(typeComboBox);
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animalType = typeComboBox.getSelectedItem().toString();

                if(defaultName.equals(nameTextField.getText())){
                    defaultName = animalType + "["+(animalsArr.getNumOfAnimalsType(animalType)+1)+"]";
                    nameTextField.setText(defaultName);
                }
                imgPath = Utilities.findAnimalImagePath(animalType,colorComboBox.getSelectedItem().toString(),1);
                imgIcon = Utilities.resizeImage(imgPath,200,200);
                imgLabel.setIcon(imgIcon);
                }

            });

        //animal color
        colorLabel = new JLabel("Animal Color : ");
        colorLabel.setFont(FONT);
        colorLabel.setSize(150, 20);
        colorLabel.setLocation(50, 150);
        container.add(colorLabel);

        colorComboBox = new JComboBox(ANIMAL_COLORS);
        colorComboBox.setSize(100, 25);
        colorComboBox.setLocation(200, 150);
        container.add(colorComboBox);
        colorComboBox.addActionListener(this);
        colorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    animalColor = (String) e.getItem();
                    String path = Utilities.findAnimalImagePath(animalType,animalColor,1);
                    imgLabel.setIcon(Utilities.resizeImage(path, 220, 180));
                    imgLabel.repaint();
                }
            }
        });

        // animal name
        nameLabel = new JLabel("Animal Name : ");
        nameLabel.setFont(FONT);
        nameLabel.setSize(150, 20);
        nameLabel.setLocation(50, 200);
        container.add(nameLabel);

        defaultName = typeComboBox.getSelectedItem().toString()+" ["+(animalsArr.getNumOfAnimalsType(typeComboBox.getSelectedItem().toString())+1)+"]";
        nameTextField = new JTextField(defaultName);
        nameTextField.setFont(FONT);
        nameTextField.setSize(100, 25);
        nameTextField.setLocation(200, 200);
        container.add(nameTextField);

        //animal size
        sizeLabel = new JLabel("Animal Size :  ");
        sizeLabel.setFont(FONT);
        sizeLabel.setSize(150, 20);
        sizeLabel.setLocation(50, 250);
        container.add(sizeLabel);

        //size text field
        placeholder = "50 - 300";
        sizeTextField = new JTextField(placeholder, 10);
        sizeTextField.setSize(100, 25);
        sizeTextField.setLocation(200, 250);
        container.add(sizeTextField);

        // horizontal speed label
        hSpeedLabel =new JLabel("Horizontal Speed : ");
        hSpeedLabel.setFont(FONT);
        hSpeedLabel.setSize(150, 50);
        hSpeedLabel.setLocation(50, 290);
        container.add(hSpeedLabel);

        // horizontal speed slider
        horSlider =new JSlider(1,10,5);
        horSlider.setPaintTicks(true);
        horSlider.setMajorTickSpacing(1);
        horSlider.setPaintLabels(true);
        horSlider.setSize(220, 50);
        horSlider.setLocation(200, 300);
        container.add(horSlider);

        // vertical speed label
        vSpeedLabel =new JLabel("Vertical Speed : ");
        vSpeedLabel.setFont(FONT);
        vSpeedLabel.setSize(180, 50);
        vSpeedLabel.setLocation(50, 350);
        container.add(vSpeedLabel);

        // vertical speed slider
        verSlider =new JSlider(1,10,5);
        verSlider.setPaintTicks(true);
        verSlider.setMajorTickSpacing(1);
        verSlider.setPaintLabels(true);
        verSlider.setSize(220, 50);
        verSlider.setLocation(200, 360);
        container.add(verSlider);

        saveBtn = new JButton("Save");
        saveBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        saveBtn.setSize(100, 30);
        saveBtn.setLocation(150, 450);
        saveBtn.addActionListener(this);
        container.add(saveBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cancelBtn.setSize(100, 30);
        cancelBtn.setLocation(270, 450);
        cancelBtn.addActionListener(this);
        container.add(cancelBtn);

        //image Label
        imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.CENTER);
        imgLabel.setVerticalTextPosition(JLabel.CENTER);
        imgLabel.setHorizontalTextPosition(JLabel.CENTER);
        String path = Utilities.findAnimalImagePath(ANIMAL_TYPES[0],ANIMAL_COLORS[0],1);
        this.imgIcon = Utilities.resizeImage(path,200,300);
        imgLabel.setIcon(imgIcon);
        imgLabel.setSize(250, 400);
        imgLabel.setLocation(500, 90);
        imgLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Animal Display "),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        container.add(imgLabel);

        this.setVisible(true);
    }

    private boolean AnimalPropertiesValidation() {
        String errorMsg = "";
        boolean isValid = true;

        if (placeholder.equals(sizeTextField.getText())) {
            isValid = false;
            errorMsg = " You need to choose the size of the animal";
        } else {
            try {
                animalSize = Integer.parseInt(sizeTextField.getText());
                if (animalSize < 50 || animalSize > 300) {
                    isValid = false;
                    errorMsg = "Invalid size, please select an animal size between 50 to 300 pixels";
                }
            } catch (NumberFormatException ignored) {
                isValid = false;
                errorMsg = " The size must be an integer value";
            }
        }
        if(animalType=="None"||animalType==null) {
            isValid=false;
            errorMsg="You have to chose an Animal!";
        }
            if (!isValid) {
                JOptionPane.showMessageDialog(null,
                        errorMsg, "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            return isValid;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Animal animal = null;
        String command = e.getActionCommand();
        String errorMsg;
        boolean isValid = true;
////        System.out.println(e.getSource());
//        if(e.getSource()==colorComboBox.getSelectedItem()){
//            animalColor = (String)e.getSource();
//            String path = Utilities.findAnimalImagePath(animalType,animalColor,1);
//            imgLabel.setIcon(Utilities.resizeImage(path, 220, 180));
//            imgLabel.repaint();
//        }
        if(command.equals("Save")) {
                if(AnimalPropertiesValidation()) {
                    animalType = typeComboBox.getSelectedItem().toString();
                    animalColor = colorComboBox.getSelectedItem().toString();
                    animalSize = Integer.parseInt(sizeTextField.getText());
                    animalName = nameTextField.getText();
                    System.out.println("size: "+ animalSize);

                    animal_hor_speed = horSlider.getValue();
                    animal_ver_speed = verSlider.getValue();
                    System.out.println(animalSize);
                    System.out.println(animal_ver_speed);
                    if (isValid) {
                        switch (animalType) {
                            case "Lion":
                                animal = new Lion(animalName, animalSize, animalColor, animal_hor_speed, animal_ver_speed, this.zooPanel);
                                break;
                            case "Bear":
                                animal = new Bear(animalName, animalSize, animalColor, animal_hor_speed, animal_ver_speed, this.zooPanel);
                                break;
                            case "Elephant":
                                animal = new Elephant(animalName, animalSize, animalColor, animal_hor_speed, animal_ver_speed, this.zooPanel);
                                break;
                            case "Giraffe":
                                animal = new Giraffe(animalName, animalSize, animalColor, animal_hor_speed, animal_ver_speed, this.zooPanel);
                                break;
                            case "Turtle":
                                animal = new Turtle(animalName, animalSize, animalColor, animal_hor_speed, animal_ver_speed, this.zooPanel);
                                break;

                        }
                        animalsArr.addAnimal(animal);
                        this.setVisible(false);
                    }

                }
            }
        else if(command.equals("Cancel")){

            this.dispose();
        }

        }

    }


