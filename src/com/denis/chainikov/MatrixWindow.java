package com.denis.chainikov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MatrixWindow extends JFrame {
    MatrixWindow() {
        super("Matrix Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,300));
        InterfaceGUI app = new InterfaceGUI();
        Component content = app.createComponent();
        getContentPane().add(content, BorderLayout.SOUTH);
        pack();

//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });


    }

}
