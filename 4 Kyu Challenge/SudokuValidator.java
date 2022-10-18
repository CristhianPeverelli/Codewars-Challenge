public class SudokuValidator {

    public static void main(String[] args) {
        int[][] sudoku = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        System.out.println(check(sudoku));
        checkSubsquare(sudoku);
    }

    public static boolean check(int[][] sudoku) {
        return checkRowsCols(sudoku) && checkSubsquare(sudoku);
    }

    private static boolean checkRowsCols(int[][] sudoku){
        for(int i = 0; i < sudoku.length; i++){
            int[] valuesR = {1,2,3,4,5,6,7,8,9};
            int[] valuesC = {1,2,3,4,5,6,7,8,9};
            for(int j = 0; j < sudoku[0].length; j++){
                if(sudoku[i][j] <= 0){ return false; }
                if(valuesR[sudoku[i][j]-1] == 0){ return false; }
                if(valuesC[sudoku[j][i]-1] == 0){ return false; }
                valuesR[sudoku[i][j]-1] = 0;
                valuesC[sudoku[j][i]-1] = 0;
            }
        }
        return true;
    }

    private static boolean checkSubsquare(int[][] sudoku){
        for(int columns = 0; columns < 9; columns+=3){
            for(int rows = 0; rows < 9; rows+=3){
                int[] values = {1,2,3,4,5,6,7,8,9};
                for(int i = rows; i < rows + 3; i++){
                    for(int j = columns; j < columns + 3; j++){
                        if(values[sudoku[i][j]-1] == 0){ return false; }
                        values[sudoku[i][j]-1] = 0;
                    }
                }
            }
        }
        return true;
    }
}
