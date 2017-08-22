package com.denis.chainikov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

//todo: create a method matrix in power and implement it
//todo: change a format of output data
//todo: how to divide it by blocks
//todo: increase font
//todo: make headings of every matrix: matrix A, ...
//todo: replace buttons functions with lambda?
//todo: show exceptions in the window
//todo: put a label of action between windows A,B and label "=" before C
//todo: (?) how to recognize the name of matrix to write "Det of matrix " + (?) + ...
//todo: ^^ (previous) make a general method DisplayDeterminant

public class InterfaceGUI {

    private JTextArea mA, mB, mC;

    Component createComponent() {

        mA = new JTextArea();
        mB = new JTextArea();
        mC = new JTextArea();

        //Make Buttons.
        JButton readAFromFile = new JButton("Read A from file");
        JButton readBFromFile = new JButton("Read B from file");
        JButton swapAandB = new JButton("Swap A & B");
        JButton AplusB = new JButton("A + B");
        JButton AminusB = new JButton("A - B");
        JButton AmultiplyB = new JButton("A x B");
        JButton clearA = new JButton("Clear A");
        JButton clearB = new JButton("Clear B");
        JButton clearC = new JButton("Clear C");
        JButton detA = new JButton("Determinant A");
        JButton detB = new JButton("Determinant B");
        JButton transposeA = new JButton("Transpose A");
        JButton transposeB = new JButton("Transpose B");
        JButton inverseA = new JButton("Inverse A");
        JButton inverseB = new JButton("Inverse B");
        JButton toHghTrnglA = new JButton("High Trngl A");
        JButton toHghTrnglB = new JButton("High Trngl B");

        //Make Panel.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,3,2,2));
        //panel.setMinimumSize(new Dimension(400,400));
        panel.add(MatrixPanel("Matrix A", mA));
        panel.add(MatrixPanel("Matrix B", mB));
        panel.add(MatrixPanel("Matrix C", mC));
        panel.add(readAFromFile);
        panel.add(readBFromFile);
        panel.add(swapAandB);
        panel.add(clearA);
        panel.add(clearB);
        panel.add(clearC);
        panel.add(transposeA);
        panel.add(transposeB);
        panel.add(AplusB);
        panel.add(detA);
        panel.add(detB);
        panel.add(AminusB);
        panel.add(toHghTrnglA);
        panel.add(toHghTrnglB);
        panel.add(AmultiplyB);
        panel.add(inverseA);
        panel.add(inverseB);

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

        swapAandB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA);
                    Matrix m2 = new Matrix(mB);
                    DisplayMatrix(m1,mB);
                    DisplayMatrix(m2,mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        AplusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA);
                    Matrix m2 = new Matrix(mB);
                    Matrix m3 = m1.add(m2);
                    DisplayMatrix(m3,mC);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        AminusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA);
                    Matrix m2 = new Matrix(mB).getMinusMatrix();
                    DisplayMatrix(m1.add(m2),mC);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        AmultiplyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA);
                    Matrix m2 = new Matrix(mB);
                    Matrix m3 = m1.multiply(m2);
                    DisplayMatrix(m3,mC);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        clearA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClearTheWindow(mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClearTheWindow(mB);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        clearC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClearTheWindow(mC);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        detA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mC.setText("Det of A is " + new Matrix(mA).determinant());
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        detB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mC.setText("Det of B is " + new Matrix(mB).determinant());
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        transposeA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA).getTranspose();
                    DisplayMatrix(m1,mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        transposeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mB).getTranspose();
                    DisplayMatrix(m1,mB);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        toHghTrnglA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA).toHighTriangleMatrix();
                    DisplayMatrix(m1,mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        toHghTrnglB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mB).toHighTriangleMatrix();
                    DisplayMatrix(m1,mB);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        inverseA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mA).getReverseMatrix();
                    DisplayMatrix(m1,mA);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            }
        });

        inverseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Matrix m1 = new Matrix(mB).getReverseMatrix();
                    DisplayMatrix(m1,mB);
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

        jta.setText(builder.toString());//
    }

    private void ClearTheWindow(JTextArea jta) {
        jta.setText("");
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
