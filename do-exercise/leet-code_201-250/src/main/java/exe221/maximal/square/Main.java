package exe221.maximal.square;

public class Main {
    public static void main(String[] args) {
        /*
         * Example 1:
         * Input: matrix = [<p>
         * |                 ["1","0","1","0","0"],
         * |                 ["1","0","1","1","1"],
         * |                 ["1","1","1","1","1"],
         * |                 ["1","0","0","1","0"]
         * |                ]
         * Output: 4
         * Example 2:
         * <p>
         * <p>
         * Input: matrix = [
         * ["0","1"],
         * ["1","0"]
         * ]
         * <p>
         * Output: 1
         * Example 3:
         * <p>
         * Input: matrix = [["0"]]
         * Output: 0
         */
        Solution solution = new Solution();
        System.out.println(solution.maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));

        System.out.println(solution.maximalSquare(new char[][]{
                {'0', '1'},
                {'1', '0'}
        }));
        System.out.println(solution.maximalSquare(new char[][]{
                {'0'}
        }));
    }
}
