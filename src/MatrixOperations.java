import java.io.IOException;

/*abstract class MatrixOperations {
    //this constructor made by IDEA and i don't understand this principal of work
    //should i extend Matrix or make as interface?
    public Matrix summ(Matrix m1, Matrix m2){
         if ((m1.getLines() == m2.getLines()) & (m1.getColumns() == m2.getColumns())){
             int lines = m1.getLines();
             int columns = m1.getColumns();
             int[][] resMatrix = new int[lines][columns];
             int[][] firstMatrix = m1.getArray();
             int[][] secondMatrix = m2.getArray();
             for (int i = 0; i < lines; i++){
                for (int j = 0; j < columns; j++){
                   resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
             }
             Matrix res = new Matrix(resMatrix,lines,columns);
             return res;
         }
         else{
             System.err.println("Incompatible sizes of Matrix");
             return null;
         }
    }
    public Matrix transpose(){
        return null;
    }
}*/
