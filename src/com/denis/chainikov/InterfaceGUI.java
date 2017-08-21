package com.denis.chainikov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//todo: increase font

public class InterfaceGUI {

    private JTextArea mA, mB, mC;

    Component createComponent() {

        mA = new JTextArea();
        mB = new JTextArea();
        //mC = new JTextArea();

        //Make Buttons.
        JButton readAFromFile = new JButton("Read A from file");
        JButton readBFromFile = new JButton("Read B from file");

        //Make Panel.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2,2,2));
        panel.setMinimumSize(new Dimension(200,200));
        panel.add(MatrixPanel("Matrix A", mA));
        panel.add(MatrixPanel("Matrix B", mB));
        panel.add(readAFromFile);
        panel.add(readBFromFile);

          //Make a vertical box.
//        Box box = Box.createVerticalBox();
//        box.add(MatrixPanel("Matrix A", mA));
//        box.add(Box.createVerticalStrut(10));
//        box.add(readAFromFile);
//        box.add(Box.createVerticalStrut(10));
//        //panel.add(Box.createRigidArea(new Dimension(10,10)));

        //Make button's listeners
        readAFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DisplayMatrix(new Matrix("C:/Users/Denis/Desktop/M1.txt"),mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        readBFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DisplayMatrix(new Matrix("C:/Users/Denis/Desktop/M2.txt"),mB);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });
        return panel;
    }

    private void DisplayMatrix(Matrix m, JTextArea jta) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < m.getNumberOfRows(); i++) {
            for (int j = 0; j < m.getNumberOfColumns(); j++) {
                builder.append(m.getElementIJ(i,j) + " ");
            }
            builder.append(System.lineSeparator());
        }

        jta.setText(builder.toString());
    }

    private JPanel MatrixPanel(String name, JTextArea jta) {
        //Create scrollPane
        int size = 200;
        JScrollPane scrollPane = new JScrollPane(jta);
        //scrollPane.setMinimumSize(new Dimension(size,size));
        //scrollPane.setMaximumSize(new Dimension(size,size));
        //What is the label? How does it work?
        //JLabel label = new JLabel();
        //label.setLabelFor(scrollPane);

        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        //panel.add(label);
        panel.add(scrollPane);

        return panel;
    }
}
