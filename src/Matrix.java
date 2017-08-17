import java.io.*;
import java.security.InvalidParameterException;

/*
    This class is created for Matrix operating.
    Matrix should be written in file with such format:
    a b
    c11 c12 c13....c1b
    ..................
    ca1 ca2 ca3....cab

    Constructors:
    1. Matrix (path to file);

    Next methods are ready to use:
    1. writeOnScreen()           -- to write in console window;
    2. writeToFile(path to file) -- to write in particular file;
    3. add(Matrix m)             -- to add this matrix to m;
    4. transpose()               -- transpose this matrix;
    5. multiply(Matrix m)        -- multiply this matrix on m;
    6. toHighTriangleMatrix()    -- get HighTriangleMatrix() from this matrix;
    7. getDeterminant            -- get Determinant if exists in double;

    Next methods are in process of developing:
    1. getDeterminant by recursion method;
    2. getReverseMatrix;

    13.08.17 15:15.
*/
class Matrix {
    private double[][] array;
    private int numberOfRows;
    private int numberOfColumns;
    final static public String path = "C:/Users/Denis/Desktop/Res.txt";

    Matrix(double[][] inputArray) throws UnsupportedOperationException {
        array = inputArray;
        numberOfRows = this.array.length;
        if (array.length != 0) {
            numberOfColumns = this.array[0].length;
        } else {
            array = null;
            throw new InvalidParameterException("Matrix can not have zero sizes");
        }
    }

    Matrix(String path) throws IOException {
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

    void writeOnScreen() throws IOException {                   //How to write at the same distance???
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

    void writeToFile(String way) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(way));
        String sizes = String.format("Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
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

    Matrix add(Matrix m1) {
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
            System.err.println("Incompatible sizes of Matrix during adding");
            return null;
        }
    }

    Matrix transpose() {
        double[][] trans = new double[this.numberOfColumns][this.numberOfRows];
        for (int i = 0; i < this.numberOfColumns; i++) {
            for (int j = 0; j < this.numberOfRows; j++) {
                trans[i][j] = array[j][i];
            }
        }
        this.array = trans;
        int a = this.numberOfRows;
        this.numberOfRows = this.numberOfColumns;
        this.numberOfColumns = a;
        return this;
    }

    Matrix multiply(Matrix m) {
        if (numberOfColumns == m.numberOfRows) {
            double[][] first = this.array;
            double[][] second = m.array;
            double[][] sol = new double[this.numberOfRows][m.numberOfColumns];
            for (int i = 0; i < this.numberOfRows; i++) {
                for (int j = 0; j < m.numberOfColumns; j++) {
                    for (int k = 0; k < this.numberOfColumns; k++) {
                        sol[i][j] = sol[i][j] + first[i][k] * second[k][j];
                    }
                }
            }
            this.array = sol;
            this.numberOfColumns = m.numberOfColumns;
            return this;
        } else { //work on mistake
            System.err.println("Incompatible sizes of Matrix during multiplication");
            return null;
        }
    }

    double getDeterminant(){                            //how to make a normal exit condition???
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
            System.out.println("It's not a square matrix!");
            System.out.println("Determinant doesn't exist!");
            return 0;
        }
    }

    Matrix toHighTriangleMatrix() {
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

    private Matrix swapRows(int first, int second){         //method of changing places of two rows first and second
        for (int i = 0; i < this.numberOfColumns; i++){
            double element = array[first][i];
            array[first][i] = array[second][i];
            array[second][i] = element;
        }
        return this;
    }

    private Matrix sumFirstToSecondRows(int first,int second, double multiplyFirst ){ //method of sum two rows with coefficient
        for (int j = 0; j < this.numberOfColumns; j++){     //second = first*multiplyFirst + second
            double element = array[first][j]*multiplyFirst + array[second][j];
            array[second][j] = element;
        }
        return this;
    }

    private Matrix getMinor(int iElement, int jElement){                           //method of getting main minor
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
        // todo: test this
        return new Matrix(result);
    }

    /*private Matrix rowToZero(int numberOfRow){                                      //method of getting zero one row
        for (int i = 0; i < this.numberOfColumns; i++){                               //it works but never used
            array[numberOfRow][i] = 0;
        }
        return this;
    }*/

    public void validateSquared() {
        if (numberOfColumns != numberOfRows || numberOfColumns == 0) {
            throw new InvalidParameterException("Matrix is not square or has zero dimension.");
        }
    }

    //this method doesn't work. let's try to make throw Gausse method!
    public double determinant() {  //I think create return method in loop is not a good idea?
        validateSquared();

        double result = 0;         //But how to make a recursion???
        if (this.numberOfColumns == 1) {
            return this.array[0][0];
        }


        int sign = 1;
        for (int j = 0; j < this.numberOfColumns; j++) {
            result += sign * this.array[0][j] * this.getMinor(0, j).determinant();
            sign *= -1;
        }
        // todo: test it

        return result; //just not to have any problems with return statement :D
    }

    /*Matrix getReverseMatrix(){                          //I want to use the same operations as in toHighTriangleMatrix
        if (this.getDeterminant() != 0){                //to singular matrix. But how to make this method doing the same
            return null;                                //the same operations?
        }
        else {
            System.out.println("Reverse matrix can't be built");
            return null;
        }
    }*/

     Matrix getReverseMatrix() throws IOException{                       //How work with matrix from the beginning???
        final Matrix beginMatrix = this;
        double mainDeterminant = this.getDeterminant();
        double[][] result = new double[this.numberOfRows][this.numberOfColumns];
        if (mainDeterminant != 0){
            for (int i = 0; i < this.numberOfRows; i++){
                for (int j = 0; j < this.numberOfColumns; j++){
                    Matrix beginMatrix1 = beginMatrix;
                    //mBegin.getMinor(i,j).writeOnScreen();
                    result[i][j] = Math.pow(-1,i+j) * beginMatrix1.getMinor(i,j).getDeterminant() / mainDeterminant;
                }
            }
        }
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        // todo: do not change input array, return another matrix
        this.array = result;
        return this;
     }
}
