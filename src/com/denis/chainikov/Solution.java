package com.denis.chainikov;

import com.denis.chainikov.Matrix;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class Solution {
    public static void main(String[] args) throws IOException, DataFormatException {
        Matrix m1 = new Matrix("C:/Users/Denis/Desktop/M1.txt");
        Matrix m2 = new Matrix("C:/Users/Denis/Desktop/M2.txt");

        //m1.toHighTriangleMatrix().printOnScreen();
        m1.getReverseMatrix().printOnScreen();
        //m1.getReverseMatrix().printOnScreen();



    }
}




