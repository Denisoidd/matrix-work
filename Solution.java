import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        Matrix m1 = new Matrix("C:/Users/Denis/Desktop/M1.txt");
        Matrix m2 = new Matrix("C:/Users/Denis/Desktop/M2.txt");
        //Matrix m3 = new Matrix();
        Matrix m3,m4;

        m1.writeOnScreen(); //check writing on the Screen
        m2.writeOnScreen();
        //m3 = m2.add(m1); //check add method
        //m2.writeOnScreen();
        m2.writeToFile(Matrix.path);
        m3 = m1.transpose(); //check transpose method
        m3.writeOnScreen();
        //m4 = m3.multiply(m2); //check multiplication method
        //m4.writeOnScreen();

    }
}
