import java.io.*;


class Matrix /*extends MatrixOperations*/{
    private int[][] array;
    private int lines;
    private int columns;
    final public String way = "C:/Users/Denis/Desktop/Res.txt";

    public int[][] getArray() {
        return array;
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public Matrix(int[][] array, int lines, int columns) {
        this.array = array;
        this.lines = lines;
        this.columns = columns;
    }

    public Matrix() {
    }

    public Matrix(String way) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(way));

        String sizeString = reader.readLine();
        char[] sizes = sizeString.toCharArray();
        int lines =  sizes[0] - 48; //size of matrix (it's written in the file)
        int columns =  sizes[2] - 48;
        this.lines = lines;
        this.columns = columns;

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
        String sizes = String.format("Matrix size: %1$d x %2$d ", lines, columns);
        System.out.println(sizes);
        for (int i = 0; i < lines; i++){
            for (int j = 0; j < columns; j++){
                System.out.print(this.array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void writeToFile(String way) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(way));
        String sizes = String.format("Matrix size: %1$d x %2$d ", lines, columns);
        writer.write(sizes);
        writer.newLine();
        for (int i = 0; i < lines; i++){
            for (int j = 0; j < columns; j++){
                String element = String.valueOf(this.array[i][j]);
                writer.write(element);
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.close();
    }

    public Matrix summ(Matrix m1){
        if ((m1.getLines() == this.getLines()) & (m1.getColumns() == this.getColumns())){
            int lines = m1.getLines();
            int columns = m1.getColumns();
            int[][] resMatrix = new int[lines][columns];
            int[][] firstMatrix = m1.getArray();
            int[][] secondMatrix = this.getArray();
            for (int i = 0; i < lines; i++){
                for (int j = 0; j < columns; j++){
                    resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
            }
            Matrix res = new Matrix(resMatrix,lines,columns);
            return res;
        }
        else{
            System.err.println("Incompatible sizes of Matrix during adding");
            return null;
        }
    }

    public Matrix transpose(){
        int lines = this.columns;
        int columns = this.lines;
        int[][] trans = new int[lines][columns];
        for (int i = 0; i < lines; i++){
            for (int j = 0; j < columns; j++ ){
                trans[i][j] = this.getArray()[j][i];
            }
        }
        Matrix res = new Matrix(trans,lines,columns);
        return res;
    }

    public Matrix multiplyOnThis(Matrix m){

        if ((this.getLines() == m.getColumns()) & (this.getColumns() == m.getLines())){
            int size = this.getLines();
            int numbOfMult = this.getColumns();
            int[][] first = this.getArray();
            int[][] second = m.getArray();
            int[][] sol = new int[size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    sol[i][j] = 0;
                }
            }
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    for (int k = 0; k < numbOfMult; k++){
                        sol[i][j] = sol[i][j] + first[i][k]*second[k][j];
                    }
                }
            }
            Matrix res = new Matrix(sol,size,size);
            return res;
        }
        else{
            System.err.println("Incompatible sizes of Matrix during multiplication");
            return null;
        }

    }

}
