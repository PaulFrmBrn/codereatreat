/**
 * @author paul
 */
public class GameOfLife {

    public static final int MAX_BORDER = 10;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello, world!");

        boolean[][] currentMatrix = initialize();
        print(currentMatrix);
        boolean[][] nextMatrix = new boolean[MAX_BORDER][MAX_BORDER];

        //
        int generation = 0;
        while (true) {
            for (int i = 0; i < MAX_BORDER; i++)
                for (int j = 0; j < MAX_BORDER; j++)
                    nextMatrix[i][j] = calculateValue(currentMatrix, i, j);
            System.out.println("generation = " + generation++);
            print(nextMatrix);
            if (isDone(nextMatrix,currentMatrix))
                break;
            Thread.sleep(700L);
            copyMatrix(nextMatrix,currentMatrix);
            fillEmpty(nextMatrix);
        }
        System.out.println("done");

    }

    // TODO check 3rd condition
    private static boolean isDone(boolean[][] nextMatrix, boolean[][] currentMatrix) {

        boolean hasAlive = false;
        for (int i = 0; i < MAX_BORDER; i++)
            for (int j = 0; j < MAX_BORDER; j++)
                if (nextMatrix[i][j]) {
                    hasAlive = true;
                    break;
                }

        boolean areTheSame = true;
        for (int i = 0; i < MAX_BORDER; i++)
            for (int j = 0; j < MAX_BORDER; j++)
                if (nextMatrix[i][j] != currentMatrix[i][j]) {
                    areTheSame = false;
                    break;
                }

        return !hasAlive || areTheSame;
    }

    private static void copyMatrix(boolean[][] fromMatrix, boolean[][] toMatrix) {
        for (int i = 0; i < fromMatrix.length; i++) {
            System.arraycopy(fromMatrix[i], 0, toMatrix[i], 0, MAX_BORDER);
        }
    }

    private static boolean calculateValue(boolean[][] currentMatrix, int i, int j) {
        int neighborhoodsCount = 0;

        for (int i2 = i - 1; i2 <= i + 1; i2++)
            for (int j2 = j - 1; j2 <= j + 1; j2++)
                if ((i2 >= 0 && i2 < MAX_BORDER) &&
                    (j2 >= 0 && j2 < MAX_BORDER) &&
                    !(i2 == i && j2 == j) &&
                    currentMatrix[i2][j2]
                   )
                    neighborhoodsCount++;
        return (!currentMatrix[i][j] &&  neighborhoodsCount == 3) ||
               ( currentMatrix[i][j] && (neighborhoodsCount == 2 || neighborhoodsCount == 3));
    }


    private static boolean[][] initialize() {

        return new boolean[][] {
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,true, false,false,false,false,false,false},
                {false,true, false,true, false,false,false,false,false,false},
                {false,false,true, true, false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false}
        };

    }

    private static void fillEmpty(boolean[][] matrix) {
        for (int i = 0; i < MAX_BORDER; i++)
            for (int j = 0; j < MAX_BORDER; j++)
                    matrix[i][j] = false;
    }

    private static void print(boolean[][] matrix) {
        for (int i = 0; i < MAX_BORDER; i++) {
            for (int j = 0; j < MAX_BORDER; j++) {
                System.out.print((matrix[i][j] ? ((char)27 + "[31m0" + (char)27 + "[0m") : 0) + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

}
