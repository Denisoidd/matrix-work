import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        Matrix m1 = new Matrix("C:/Users/Denis/Desktop/M1.txt");
        Matrix m2 = new Matrix("C:/Users/Denis/Desktop/M2.txt");

        Matrix m3 = new Matrix(new double[5][5]);
        m3.toHighTriangleMatrix();
        m3.writeOnScreen();
    }
}




