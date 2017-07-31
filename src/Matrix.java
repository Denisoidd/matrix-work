import java.io.*;


class Matrix /*extends MatrixOperations*/{
    private int[][] array;
    private int numberOfRows;
    private int numberOfColumns;
    final static public String path = "C:/Users/Denis/Desktop/Res.txt";

    public Matrix(String way) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(way));
        String sizeString = reader.readLine();
        char[] sizes = sizeString.toCharArray();
        int lines =  sizes[0] - 48; //size of matrix (it's written in the file)
        int columns =  sizes[2] - 48;
        this.numberOfRows = lines;
        this.numberOfColumns = columns;

        int[][] matrix = new int[lines][columns];
        for (int i=0; i < lines; i++){
            String string = reader.readLine();
            String[] strArr = string.split(" ");
            for (int j=0; j < columns; j++){
                matrix[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        reader.close();
        this.array = matrix;
    }

    public void writeOnScreen() throws IOException{
        String sizes = String.format("Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
        System.out.println(sizes);
        for (int i = 0; i < numberOfRows; i++){
            for (int j = 0; j < numberOfColumns; j++){
                System.out.print(this.array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void writeToFile(String way) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(way));
        String sizes = String.format("Matrix size: %1$d x %2$d ", numberOfRows, numberOfColumns);
        writer.write(sizes);
        writer.newLine();
        for (int i = 0; i < numberOfRows; i++){
            for (int j = 0; j < numberOfColumns; j++){
                String element = String.valueOf(array[i][j]);
                writer.write(element);
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.close();
    }

    public Matrix add(Matrix m1){
        if ((m1.numberOfRows == this.numberOfRows) & (m1.numberOfColumns == this.numberOfColumns)){
            int[][] resMatrix = new int[m1.numberOfRows][m1.numberOfColumns];
            int[][] firstMatrix = m1.array;
            int[][] secondMatrix = array;
            for (int i = 0; i < m1.numberOfRows; i++){
                for (int j = 0; j < m1.numberOfColumns; j++){
                    resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
            }
            this.array = resMatrix;
            return this;
        }
        else{ //work on the mistake
            System.err.println("Incompatible sizes of Matrix during adding");
            return null;
        }
    }

    public Matrix transpose(){
        int[][] trans = new int[this.numberOfColumns][this.numberOfRows];
        for (int i = 0; i < this.numberOfColumns; i++){
            for (int j = 0; j < this.numberOfRows; j++ ){
                trans[i][j] = array[j][i];
            }
        }
        this.array = trans;
        int a = this.numberOfRows ;
        this.numberOfRows = this.numberOfColumns;
        this.numberOfColumns = a;
        return this;
    }

    public Matrix multiply(Matrix m){
        if  (numberOfColumns == m.numberOfRows){
            int[][] first = this.array;
            int[][] second = m.array;
            int[][] sol = new int[this.numberOfRows][m.numberOfColumns];
            for (int i = 0; i < this.numberOfRows; i++){
                for (int j = 0; j < m.numberOfColumns; j++){
                    for (int k = 0; k < this.numberOfColumns; k++){
                        sol[i][j] = sol[i][j] + first[i][k]*second[k][j];
                    }
                }
            }
            this.array = sol;
            this.numberOfColumns = m.numberOfColumns;
            return this;
        }
        else{ //work on mistake
            System.err.println("Incompatible sizes of Matrix during multiplication");
            return null;
        }
    }
}
