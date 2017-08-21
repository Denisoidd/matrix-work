package com.denis.chainikov;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.zip.DataFormatException;
public class Solution {
    public static void main(String[] args) throws IOException, DataFormatException {
//        Matrix m1 = new Matrix("C:/Users/Denis/Desktop/M1.txt");
//        Matrix m2 = new Matrix("C:/Users/Denis/Desktop/M2.txt");
        //InterfaceGUI app = new InterfaceGUI();
        //Component component = app.createComponent();
        //frame.setSize(new Dimension(600,400));
//        frame.getContentPane().add(component, BorderLayout.CENTER);
        //frame.add(component,BorderLayout.SOUTH);
        //frame.pack();

        JFrame frame = new MatrixWindow();
        frame.setVisible(true);




    }
}




