import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Matrix {
    private int[][] array;
    public static int size;
    public int[][] getMatrix(){
        return array;
    }
    private Matrix() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Denis/Desktop/Matrix3x3.txt"));
        String sizeString = reader.readLine();

        int size = Integer.parseInt(sizeString); //size of matrix (it's written in the file)
        this.size = size;

        int[][] matrix = new int[size][size];
        int line = 0;
        while (true) {
            String string = reader.readLine();
            if (!string.isEmpty()) {
                String[] arr = string.split(" ");
                int column = 0;
                for (String element : arr) {
                    int elementInt = Integer.parseInt(element);
                    matrix[line][column] = elementInt;
                    column++;
                }
                line++;
            }
            else {
                break;
            }
        }
        this.array = matrix;
    }
    /*public void writeMatrix(){
        int[][] matrix = this.array;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }*/

    public static void main(String[] args) throws IOException{
        int[][] matrix = new Matrix().getMatrix();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
