import java.io.*;


class Matrix {
    private int[][] array;

    private static int size;

    public int[][] getMatrix(){
        return array;
    }

    public Matrix() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Denis/Desktop/Matrix3x3.txt"));
        String sizeString = reader.readLine();

        int size = Integer.parseInt(sizeString); //size of matrix (it's written in the file)
        this.size = size;

        int[][] matrix = new int[size][size];
        int line = 0;
        for (int i=0; i < size; i++){
            String string = reader.readLine();
            String[] strArr = string.split(" ");
            for (int j=0; j < size; j++){
                matrix[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        reader.close();
        this.array = matrix;
    }

    public static void writeOnScreen(int[][] matrix){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void writeOnFile(int[][] array) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Denis/Desktop/MatrixResult.txt"));
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                String element = String.valueOf(array[i][j]);
                writer.write(element);
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.close();
    }

}
