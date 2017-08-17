package com.denis.chainikov;

import sun.plugin.dom.exception.InvalidStateException;

import java.io.*;
import java.security.InvalidParameterException;

/**
 * Global todo
 *
 * -> todo: getElement() which will return (i,j) element of a matrix.
 * -> todo: add, multiply - return new matrix instead of this one.
 * -> todo: remove redundant matrix sizes (numberOfColumns/rows)
 * -> todo: describe methods @param section.
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
    final static public String path = "C:/Users/Denis/Desktop/Res.txt";

    /**
     * Constructor of Matrix
     * Read input array and return Matrix Object.
     *
     * @param inputArray
     * @throws InvalidParameterException
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
     * Constructor of the Matrix
     * Read a matrix from the file of particular view:
     * Number of Rows _ Number of Columns
     * Matrix
     *
     * todo: when size of matrix is bigger than 9, constructor reads only one symbol
     *
     * @param path
     * @throws IOException
     */
    public Matrix(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String sizeString = reader.readLine();
        char[] sizes = sizeString.toCharArray();
        int lines = sizes[0] - 48; //size of matrix (it's written in the file)
        int columns = sizes[2] - 48;
        this.numberOfRows = lines;
        this.numberOfColumns = columns;

        double[][] matrix = new double[lines][columns];
        for (int i = 0; i < lines; i++) {
            String string = reader.readLine();
            String[] strArr = string.split(" ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Double.parseDouble(strArr[j]);
            }
        }
        reader.close();
        this.array = matrix;
    }

    /**
     * Print this particular matrix on the screen
     * Number of Rows _ Number of Columns
     * Array
     *
     * @throws IOException
     */
    public void printOnScreen() throws IOException {
        String sizes = String.format("com.denis.chainikov.Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
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
     * Number of Rows _ Number of Columns
     * Array
     *
     * @param way
     * @throws IOException
     */
    public void printToFile(String way) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(way));
        String sizes = String.format("com.denis.chainikov.Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
        writer.write(sizes);
        writer.newLine();
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
     * todo: exception of incompatible sizes
     *
     * @param m1
     * @return this Matrix object - the sum of matrices
     */
    public Matrix add(Matrix m1) {
        if ((m1.numberOfRows == this.numberOfRows) & (m1.numberOfColumns == this.numberOfColumns)) {
            double[][] resMatrix = new double[m1.numberOfRows][m1.numberOfColumns];
            double[][] firstMatrix = m1.array;
            double[][] secondMatrix = array;
            for (int i = 0; i < m1.numberOfRows; i++) {
                for (int j = 0; j < m1.numberOfColumns; j++) {
                    resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
            }
            this.array = resMatrix;
            return this;

        } else { //work on the mistake
            System.err.println("Incompatible sizes of com.denis.chainikov.Matrix during adding");
            return null;
        }
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
     * todo: exception of incompatible sizes
     *
     * @param secondMatrix
     * @return this Matrix - multiplication of two Matrix objects
     */
    public Matrix multiply(Matrix secondMatrix) {
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
            this.array = sol;
            this.numberOfColumns = secondMatrix.numberOfColumns;
            return this;
        } else { //work on mistake
            System.err.println("Incompatible sizes of com.denis.chainikov.Matrix during multiplication");
            return null;
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
     * todo: normal exception
     *
     * @return new Matrix - reverse Matrix
     * @throws
     */
    // todo: inverse matrix
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
        return new Matrix(result);
     }

    /**
     * This method swaps two rows first and second
     *
     * @param first
     * @param second
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
     * @param first
     * @param second
     * @param multiplier
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
     * todo: normal exception condition
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
