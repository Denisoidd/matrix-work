package com.denis.chainikov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGUI {

    private JTextArea mA, mB, mC;

    Component createComponent() {

        mA = new JTextArea();
        //mB = new JTextArea();
        //mC = new JTextArea();

        //Make a panel with Buttons.
        JPanel panel = new JPanel();
        JButton sumButton = new JButton("A + B = C");
        panel.add(sumButton);
        panel.add(MatrixPanel("Matrix A", mA));

        //Make panel's listeners
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DisplayMatrix(new Matrix("C:/Users/Denis/Desktop/M1.txt"),mA);
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
        int size = 200;
        JScrollPane scrollPane = new JScrollPane(jta);

        scrollPane.setPreferredSize(new Dimension(size,size));
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
