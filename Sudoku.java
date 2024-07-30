public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        if (solve(board)) {
            display(board);
        } else {
            System.out.println("No solution exists");
        }
    }

    static boolean solve(int[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            if (!emptyLeft) {
                break;
            }
        }
        if (emptyLeft) {
            return true;
        }
        for (int number = 1; number <= 9; number++) {
            if (isSafe(board, row, col, number)) {
                board[row][col] = number;
                if (solve(board)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    static void display(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int[][] board, int r, int c, int n) {
        // Check the row
        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == n) {
                return false;
            }
        }
        // Check the column
        for (int[] nums : board) {
            if (nums[c] == n) {
                return false;
            }
        }
        // Check the sub-grid
        int sqrt = (int) Math.sqrt(board.length);
        int startRow = r - (r % sqrt);
        int startCol = c - (c % sqrt);

        for (int row = startRow; row < startRow + sqrt; row++) {
            for (int col = startCol; col < startCol + sqrt; col++) {
                if (board[row][col] == n) {
                    return false;
                }
            }
        }
        return true;
    }
}
