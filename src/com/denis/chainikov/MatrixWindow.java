package com.denis.chainikov;

import javax.swing.*;
import java.awt.*;

public class MatrixWindow extends JFrame {
    MatrixWindow() {
        super("Matrix Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400,700));
        //setPreferredSize(new Dimension(400,400));
        InterfaceGUI app = new InterfaceGUI();
        Component content = app.createComponent();
        getContentPane().add(content);
        pack();
    }

}
