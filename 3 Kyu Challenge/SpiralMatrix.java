public class SpiralMatrix {

    public static void main(String[] args) throws InterruptedException {
        int[][] m = spiralize(10);
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                System.out.print(m[i][j] + " ");
                Thread.sleep(10);
            }
            System.out.println();
        }
    }

    public static int[][] spiralize(int size) {
        int[][] matrix = new int[size][size];
        int totalBorders = matrix.length/2;
        if( matrix.length%2 == 0){ totalBorders--; }
        if(size == 1){ matrix[0][0] = 1; }
        for(int border = 0; border < totalBorders; border++)
            matrix = spiralizeBorder(matrix, border);
        return matrix;
    }

    private static int[][] spiralizeBorder(int[][] matrix, int borderIndex){
        for(int j = 2*borderIndex-1; j < matrix.length - (2*borderIndex) - 1; j++){
            if(j >= 0){ matrix[2*borderIndex][j] = 1; }else{ matrix[2*borderIndex][j+1] = 1; }
        }    
        for(int i = 2*borderIndex; i < matrix.length - (2*borderIndex); i++){
            matrix[i][matrix.length -1 - (2*borderIndex)] = 1;
        }

        for(int j = matrix.length -1 - (2*borderIndex); j >= (1 + 2*borderIndex); j--){
            matrix[matrix.length -1 - (2*borderIndex)][j] = 1;
        } 
        for(int i = matrix.length -1 - (2*borderIndex); i >= (2 + 2*borderIndex); i--){
            matrix[i][2*borderIndex] = 1;
        }       
        return matrix;
    }

}
