public class MatrixDeterminant {

    public static void main(String[] args) {
        int[][][] m = { {{1}},
                        {{1, 3}, {2,5}},
                        {{2,5,3}, {1,-2,-1}, {1,3,4}}};
        System.out.println(determinant(m[2]));
    }
    
    public static int determinant(int[][] matrix) {
        if(matrix.length == 1)
            return matrix[0][0]; 
        if(matrix.length == 2)
            return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        int result = 0;
        for(int i = 0; i < matrix.length; i++){
            int[][] temp = new int[matrix.length-1][matrix.length-1];
            int row = 0,col = 0;
            for(int j = 0; j < matrix.length; j++){
                for(int h = 0; h < matrix.length; h++){
                    if(j != 0 && h != i){
                        temp[row][col] = matrix[j][h];
                        col++;
                        if(col == matrix.length-1){ col = 0; row++; }
                    }
                }
            }
            result += (i%2!=0 ? -1 : 1) * matrix[0][i] * determinant(temp);
        }
        return result;
    }
}