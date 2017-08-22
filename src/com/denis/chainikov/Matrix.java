package com.denis.chainikov;

import com.sun.media.sound.InvalidDataException;
import sun.plugin.dom.exception.InvalidStateException;

import javax.swing.*;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * Global todo
 *
 * -> todo: how to build matrix by reading properly?
 * -> todo: getAdjunctMatrix().
 * -> todo: problems of input. if you read empty matrix you get the description about number of col
 */

/*
    This class is created for com.denis.chainikov.Matrix operating.
    com.denis.chainikov.Matrix should be written in file with such format:
    a b
    c11 c12 c13....c1b
    ..................
    ca1 ca2 ca3....cab

    Constructors:
    1. Matrix (path to file);
    2. Matrix (double array[][]);

    Next methods are ready to use:
    1. printOnScreen()                               -- to write in console window;
    2. printToFile(path to file)                     -- to write in particular file;
    3. add(Matrix m)                                 -- to add this matrix to m;
    4. getTranspose()                                -- getTranspose this matrix;
    5. multiply(Matrix m)                            -- multiply this matrix on m;
    6. toHighTriangleMatrix()                        -- get HighTriangleMatrix() from this matrix;
    7. getDeterminant                                -- get Determinant if exists in double
                                                        using toHighTriangleMethod
                                                        now is deprecated because of below method
    8. determinant                                   -- get determinant by recursive method;
    9. getReverseMatrix                              -- get reverse matrix;

    17.08.17 20:30.
*/

public class Matrix {
    private double[][] array;

    private int numberOfRows;

    private int numberOfColumns;

    public double getElementIJ(int row,int column){
        return array[row][column];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Constructor of Matrix
     * Read input array and return Matrix Object.
     *
     * @param inputArray - array
     * @throws InvalidParameterException - appears if input array is wrong
     */
    public Matrix(double[][] inputArray) throws InvalidParameterException {
        array = inputArray;
        numberOfRows = this.array.length;
        if (array.length != 0) {
            numberOfColumns = this.array[0].length;
        } else {
            array = null;
            throw new InvalidParameterException("Matrix can not have zero sizes");
        }
    }

    /**
     * Construct Matrix object reading array from file
     *
     * @param path - a matrix's path
     * @throws IOException - if path is incorrect
     * @throws InvalidDataException appears when number of columns varies on row
     */
    public Matrix(String path) throws IOException {
        matrixBuilder(new Scanner(new FileInputStream(path)));
//        int numberOfRows = 0;
//        int numberOfColumns = 0;
//        int checker;
//        ArrayList<Double> doubleList = new ArrayList<>();
//
//        //Read matrix, put it into ArrayList, check and find sizes of it
//        Scanner scanner = new Scanner(new FileInputStream(path));
//        while (scanner.hasNextLine()) {
//            Scanner scannerString = new Scanner(scanner.nextLine());
//            numberOfRows++;
//            checker = 0;
//            while (scannerString.hasNextDouble()){
//                doubleList.add(scannerString.nextDouble());
//                if (numberOfRows == 1) {
//                    numberOfColumns++;
//                }
//                checker++;
//            }
//            if (checker != numberOfColumns) {
//                throw new InvalidDataException("Check size of your Matrix. Number of columns is " +
//                        "different");
//            }
//            scannerString.close();
//        }
//        scanner.close();
//
//        //From ArrayList into array
//        double[][] array = new double[numberOfRows][numberOfColumns];
//        int numbOfListElem = 0;
//        for (int i = 0; i < numberOfRows; i++) {
//            for (int j = 0; j < numberOfColumns; j++) {
//                array[i][j] = doubleList.get(numbOfListElem);
//                numbOfListElem++;
//            }
//        }
//        this.array = array;
//        this.numberOfColumns = numberOfColumns;
//        this.numberOfRows = numberOfRows;
    }

    public Matrix(JTextArea jta) {
        try {
            matrixBuilder(new Scanner(jta.getText()));
        } catch (InvalidDataException e) {
            System.err.println("Error " + e);
        }
    }

    void matrixBuilder(Scanner scanner) throws InvalidDataException{
        int numberOfRows = 0;
        int numberOfColumns = 0;
        int checker;
        ArrayList<Double> doubleList = new ArrayList<>();while (scanner.hasNextLine()) {
            Scanner scannerString = new Scanner(scanner.nextLine());
            numberOfRows++;
            checker = 0;
            while (scannerString.hasNextDouble()){
                doubleList.add(scannerString.nextDouble());
                if (numberOfRows == 1) {
                    numberOfColumns++;
                }
                checker++;
            }
            if (checker != numberOfColumns) {
                throw new InvalidDataException("Check size of your Matrix. Number of columns is " +
                        "different");
            }
            scannerString.close();
        }
        scanner.close();

        //From ArrayList into array
        double[][] array = new double[numberOfRows][numberOfColumns];
        int numbOfListElem = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                array[i][j] = doubleList.get(numbOfListElem);
                numbOfListElem++;
            }
        }
        this.array = array;
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
    }

    /**
     * Print this particular matrix on the screen
     *
     * Array
     *
     */
    public void printOnScreen() {
        String sizes = String.format("Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
        System.out.println(sizes);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(this.array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Print this particular matrix to file of following path
     *
     * Array
     *
     * @param path - a path to matrix
     * @throws IOException if file doesn't exist
     */
    public void printToFile(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                String element = String.valueOf(array[i][j]);
                writer.write(element);
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.close();
    }

    /**
     * This method sums this and m1 matrices.
     *
     * @param m1 - second matrix of sum
     * @return new Matrix object - the sum of matrices
     */
    public Matrix add(Matrix m1) throws DataFormatException{
        if ((m1.numberOfRows == this.numberOfRows) & (m1.numberOfColumns == this.numberOfColumns)) {
            double[][] resMatrix = new double[m1.numberOfRows][m1.numberOfColumns];
            double[][] firstMatrix = m1.array;
            double[][] secondMatrix = array;
            for (int i = 0; i < m1.numberOfRows; i++) {
                for (int j = 0; j < m1.numberOfColumns; j++) {
                    resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
            }
            return new Matrix(resMatrix);

        } else {
            throw new DataFormatException("Sizes of matrices are not the same.");
        }
    }

    /**
     * This method returns minus matrix
     * @return new matrix with opposite sign
     */
    public Matrix getMinusMatrix() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                array[i][j] *= -1;
            }
        }
        return new Matrix(array);
    }
    /**
     * This method transposes matrix.
     *
     * @return a new Matrix object - transpose Matrix
     */
    public Matrix getTranspose() {
        double[][] trans = new double[this.numberOfColumns][this.numberOfRows];
        for (int i = 0; i < this.numberOfColumns; i++) {
            for (int j = 0; j < this.numberOfRows; j++) {
                trans[i][j] = array[j][i];
            }
        }
        return new Matrix(trans);
    }

    /**
     * This method multiplicities two Matrix objects.
     *
     *
     * @param secondMatrix - second matrix of multiplication
     * @return this Matrix - multiplication of two Matrix objects
     */
    public Matrix multiply(Matrix secondMatrix) throws DataFormatException{
        if (numberOfColumns == secondMatrix.numberOfRows) {
            double[][] first = this.array;
            double[][] second = secondMatrix.array;
            double[][] sol = new double[this.numberOfRows][secondMatrix.numberOfColumns];
            for (int i = 0; i < this.numberOfRows; i++) {
                for (int j = 0; j < secondMatrix.numberOfColumns; j++) {
                    for (int k = 0; k < this.numberOfColumns; k++) {
                        sol[i][j] = sol[i][j] + first[i][k] * second[k][j];
                    }
                }
            }
            return new Matrix(sol);
        } else { //work on mistake
            throw new DataFormatException("Incompatible sizes of Matrix during multiplication");
        }
    }

    /**
     * This method transforms matrix to high triangular matrix
     *
     * @return this matrix in a high triangular form
     */
    public Matrix toHighTriangleMatrix() {
        int workWithNumberOfRows;                           //look for number of rows which i will work on
        if (this.numberOfRows > this.numberOfColumns){
            workWithNumberOfRows = this.numberOfColumns;
        }
        else if (this.numberOfColumns == this.numberOfRows){
            workWithNumberOfRows = this.numberOfColumns;
        }
        else {
            workWithNumberOfRows = this.numberOfRows;
        }
        for (int i = 0; i < workWithNumberOfRows  ; i++){   //go throw all rows
            for (int j = i+1; j < this.numberOfRows; ){     //go from particular row and below
                if (array[i][i] != 0) {                     //check if divide by zero
                    if (array[j][i] != 0) {
                        this.sumFirstToSecondRows(i, j, -array[j][i] / array[i][i]);
                    }
                    j++;
                }
                else {                                      //if zero case look for not zero element and swap rows
                    for (int k = i + 1; k < this.numberOfRows; k++){
                        if (array[k][i] != 0 ){
                            this.swapRows(i,k);
                            break;
                        }
                        j++;
                    }
                }
            }
        }
        return this;
    }

    /**
     * Method that gives any (n-1, m-1) dimensioned minor.
     *
     * @param iElement ith row to exclude
     * @param jElement jth column to exclude
     *
     * @return returns new Matrix - needed minor
     */
    public Matrix getMinor(int iElement, int jElement) {
        double[][] result = new double[numberOfRows - 1][numberOfColumns - 1];        //this method works. it was created to be applied in recursion method of determinant
        for (int i = 0; i < numberOfRows - 1; i++){
            for (int j = 0; j < numberOfColumns - 1; j++){
                if ((i < iElement) && (j < jElement)){
                    result[i][j] = array[i][j];
                }
                else if ((i >= iElement) && (j < jElement)){
                    result[i][j] = array[i+1][j];
                }
                else if ((i < iElement) && (j >= jElement)){
                    result[i][j] = array[i][j+1];
                }
                else if ((i >= iElement) && (j >= jElement)){
                    result[i][j] = array[i+1][j+1];
                }
            }
        }
        return new Matrix(result);
    }

    /**
     * This method calculate determinant by recursive method
     *
     * @return determinant of Matrix object if exists in double format
     */
    public double determinant() {
        validateSquared();
        double result = 0;
        if (this.numberOfColumns == 1) {
            return this.array[0][0];
        }
        int sign = 1;
        for (int j = 0; j < this.numberOfColumns; j++) {
            result += sign * this.array[0][j] * this.getMinor(0, j).determinant();
            sign *= -1;
        }
        return result;
    }

    /**
     * This method returns reverse Matrix object
     * In process of developing
     *
     * @return new Matrix - reverse Matrix
     * @throws ArithmeticException - if determinant equals zero
     */
     public Matrix getReverseMatrix() {
        double mainDeterminant = this.determinant();
        double[][] result = new double[this.numberOfRows][this.numberOfColumns];
        int sign = 1;
        if (mainDeterminant != 0){
            for (int i = 0; i < this.numberOfRows; i++){
                for (int j = 0; j < this.numberOfColumns; j++){
                    result[i][j] = sign * this.getMinor(i,j).determinant() /
                            mainDeterminant;
                    sign *= -1;
                }
            }
        }
        else {
            throw new ArithmeticException("Determinant is not zero.");
        }
        return new Matrix(result);
     }

    /**
     * This method swaps two rows first and second
     *
     * @param first row
     * @param second row
     * @return this Matrix object with swapped rows
     */
    private Matrix swapRows(int first, int second){
        for (int i = 0; i < this.numberOfColumns; i++){
            double element = array[first][i];
            array[first][i] = array[second][i];
            array[second][i] = element;
        }
        return this;
    }

    /**
     * This method sum first row to the second row multiply by multiplier
     *
     * @param first row
     * @param second row
     * @param multiplier - first row will be multiplied by multiplier
     * @return this matrix with sum of rows
     */
    private Matrix sumFirstToSecondRows(int first,int second, double multiplier){
        for (int j = 0; j < this.numberOfColumns; j++){
            double element = array[first][j]*multiplier + array[second][j];
            array[second][j] = element;
        }
        return this;
    }

    /**
     * This method use toHighTriangleMatrix at first
     * then multiply diagonal elements
     *
     * @deprecated because of recursive method
     * @return determinant of this matrix in double format
     */
    public double getDeterminant() {
        double result;
        this.toHighTriangleMatrix();
        if (this.numberOfColumns == this.numberOfRows) {
            result = 1;
            for (int i = 0; i < this.numberOfColumns; i++) {
                result = result * array[i][i];
            }
            return result;
        }
        else {
            throw new InvalidStateException("Matrix isn't square. Determinant doesn't exist!");
        }
    }

    /**
     * Throws an exception if matrix is not a square matrix or zero matrix
     */
    private void validateSquared() {
        if (numberOfColumns != numberOfRows || numberOfColumns == 0) {
            throw new InvalidParameterException("Matrix is not square or has zero dimension.");
        }
    }

    /**
     * This method returns the same Matrix with zero row.
     * Never used.
     *
     * @param numberOfRow the row which needs to be zero row
     * @return the same matrix - with zero row
     */
     private Matrix rowToZero(int numberOfRow) {
        for (int i = 0; i < this.numberOfColumns; i++) {
            array[numberOfRow][i] = 0;
        }
        return this;
     }
}
