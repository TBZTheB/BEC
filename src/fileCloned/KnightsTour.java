package fileCloned;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
    * Problem Statement: -

    Given a N*N board with the Knight placed on the first block of an empty board. Moving according
   to the rules of chess knight must visit each square exactly once. Print the order of each cell in
   which they are visited.

    Example: -

    Input : N = 8

    Output:
        0  59  38  33  30  17   8  63
        37  34  31  60   9  62  29  16
        58   1  36  39  32  27  18   7
        35  48  41  26  61  10  15  28
        42  57   2  49  40  23   6  19
        47  50  45  54  25  20  11  14
        56  43  52   3  22  13  24   5
        51  46  55  44  53   4  21  12

 */
public class KnightsTour {

    private static final int base = 12;
    private static final int[][] moves = {
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2},
    }; // Possible moves by knight on chess
    private static int[][] grid; // chess grid
    private static int total; // total squares in chess

    public static void main(String[] args) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$49#statement$grid=new int[base][base];");
        grid = new int[base][base];
        Mark.print("line-in-function$3#offset$85#statement$total=(base - 4) * (base - 4);");
        total = (base - 4) * (base - 4);
        Mark.print("line-in-function$5#offset$127#surrounding-control-block$for");
        Mark.print("line-in-function$5#offset$132#statement$int r=0");
        for (int r = 0; (Mark.print("line-in-function$5#offset$143#statement$r < base") && r < base); r++) {
            Mark.print("statement${#line-of-block-in-function$5");
            Mark.print("line-in-function$6#offset$172#surrounding-control-block$for");
            Mark.print("line-in-function$6#offset$177#statement$int c=0");
            for (int c = 0; (Mark.print("line-in-function$6#offset$188#statement$c < base") && c < base); c++) {
                Mark.print("statement${#line-of-block-in-function$6");
                Mark.print("line-in-function$7#offset$221#surrounding-control-block$if");
                if ((Mark.print("line-in-function$7#offset$225#statement$r < 2") && r < 2) || (Mark.print("line-in-function$7#offset$234#statement$r > base - 3") && r > base - 3) || (Mark.print("line-in-function$7#offset$250#statement$c < 2") && c < 2) || (Mark.print("line-in-function$7#offset$259#statement$c > base - 3") && c > base - 3)) {
                    Mark.print("statement${#line-of-block-in-function$7");
                    Mark.print("line-in-function$8#offset$295#statement$grid[r][c]=-1;");
                    grid[r][c] = -1;
                    Mark.print("statement$}#line-of-block-in-function$7");
                }
                Mark.print("statement$}#line-of-block-in-function$6");
                Mark.print("line-in-function$6#offset$198#statement$c++");
            }
            Mark.print("statement$}#line-of-block-in-function$5");
            Mark.print("line-in-function$5#offset$153#statement$r++");
        }
        Mark.print("line-in-function$13#offset$363#statement$int row=2 + (int)(Math.random() * (base - 4));");
        int row = 2 + (int) (Math.random() * (base - 4));
        Mark.print("line-in-function$14#offset$421#statement$int col=2 + (int)(Math.random() * (base - 4));");
        int col = 2 + (int) (Math.random() * (base - 4));
        Mark.print("line-in-function$16#offset$480#statement$grid[row][col]=1;");
        grid[row][col] = 1;
        Mark.print("line-in-function$18#offset$509#surrounding-control-block$if");
        if ((Mark.print("line-in-function$18#offset$513#statement$solve(row,col,2)") && solve(row, col, 2))) {
            Mark.print("statement${#line-of-block-in-function$18");
            Mark.print("line-in-function$19#offset$547#statement$printResult();");
            printResult();
            Mark.print("statement$}#line-of-block-in-function$18");
        } else {
            Mark.print("statement${#line-of-block-in-function$20");
            Mark.print("line-in-function$21#offset$591#statement$System.out.println(\"no result\");");
            System.out.println("no result");
            Mark.print("statement$}#line-of-block-in-function$20");
        }
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    // Return True when solvable
    private static boolean solve(int row, int column, int count) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$71#surrounding-control-block$if");
        if ((Mark.print("line-in-function$2#offset$75#statement$count > total") && count > total)) {
            Mark.print("statement${#line-of-block-in-function$2");
            Mark.print("line-in-function$3#offset$104#statement$return true;");
            Mark.print("statement$}#line-of-block-in-function$2");
            Mark.print("statement$}#line-of-block-in-function$1");
            return true;
        }
        Mark.print("line-in-function$6#offset$136#statement$List<int[]> neighbor=neighbors(row,column);");
        List<int[]> neighbor = neighbors(row, column);
        Mark.print("line-in-function$8#offset$192#surrounding-control-block$if");
        if ((Mark.print("line-in-function$8#offset$196#statement$neighbor.isEmpty()") && neighbor.isEmpty()) && (Mark.print("line-in-function$8#offset$218#statement$count != total") && count != total)) {
            Mark.print("statement${#line-of-block-in-function$8");
            Mark.print("line-in-function$9#offset$248#statement$return false;");
            Mark.print("statement$}#line-of-block-in-function$8");
            Mark.print("statement$}#line-of-block-in-function$1");
            return false;
        }
        Mark.print("line-in-function$12#offset$281#statement$neighbor.sort(Comparator.comparingInt(a -> a[2]));");
        neighbor.sort(Comparator.comparingInt(a -> a[2]));
        Mark.print("line-in-function$14#offset$341#surrounding-control-block$forEach");
        Mark.print("line-in-function$14#offset$346#statement$int[] nb : neighbor");
        for (int[] nb : neighbor) {
            Mark.print("statement${#line-of-block-in-function$14");
            Mark.print("line-in-function$15#offset$381#statement$row=nb[0];");
            row = nb[0];
            Mark.print("line-in-function$16#offset$406#statement$column=nb[1];");
            column = nb[1];
            Mark.print("line-in-function$17#offset$434#statement$grid[row][column]=count;");
            grid[row][column] = count;
            Mark.print("line-in-function$18#offset$473#surrounding-control-block$if");
            if ((Mark.print("line-in-function$18#offset$477#statement$!orphanDetected(count,row,column)") && !orphanDetected(count, row, column)) && (Mark.print("line-in-function$18#offset$516#statement$solve(row,column,count + 1)") && solve(row, column, count + 1))) {
                Mark.print("statement${#line-of-block-in-function$18");
                Mark.print("line-in-function$19#offset$565#statement$return true;");
                Mark.print("statement$}#line-of-block-in-function$18");
                Mark.print("statement$}#line-of-block-in-function$14");
                Mark.print("statement$}#line-of-block-in-function$1");
                return true;
            }
            Mark.print("line-in-function$21#offset$604#statement$grid[row][column]=0;");
            grid[row][column] = 0;
            Mark.print("statement$}#line-of-block-in-function$14");
            Mark.print("line-in-function$14#offset$346#statement$int[] nb : neighbor");
        }
        Mark.print("line-in-function$24#offset$646#statement$return false;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return false;
    }

    // Returns List of neighbours
    private static List<int[]> neighbors(int row, int column) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$68#statement$List<int[]> neighbour=new ArrayList<>();");
        List<int[]> neighbour = new ArrayList<>();
        Mark.print("line-in-function$4#offset$120#surrounding-control-block$forEach");
        Mark.print("line-in-function$4#offset$125#statement$int[] m : moves");
        for (int[] m : moves) {
            Mark.print("statement${#line-of-block-in-function$4");
            Mark.print("line-in-function$5#offset$156#statement$int x=m[0];");
            int x = m[0];
            Mark.print("line-in-function$6#offset$182#statement$int y=m[1];");
            int y = m[1];
            Mark.print("line-in-function$7#offset$208#surrounding-control-block$if");
            if ((Mark.print("line-in-function$7#offset$212#statement$grid[row + y][column + x] == 0") && grid[row + y][column + x] == 0)) {
                Mark.print("statement${#line-of-block-in-function$7");
                Mark.print("line-in-function$8#offset$262#statement$int num=countNeighbors(row + y,column + x);");
                int num = countNeighbors(row + y, column + x);
                Mark.print("line-in-function$9#offset$325#statement$neighbour.add(new int[]{row + y,column + x,num});");
                neighbour.add(new int[]{row + y, column + x, num});
                Mark.print("statement$}#line-of-block-in-function$7");
            }
            Mark.print("statement$}#line-of-block-in-function$4");
            Mark.print("line-in-function$4#offset$125#statement$int[] m : moves");
        }
        Mark.print("line-in-function$12#offset$410#statement$return neighbour;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return neighbour;
    }

    // Returns the total count of neighbors
    private static int countNeighbors(int row, int column) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$65#statement$int num=0;");
        int num = 0;
        Mark.print("line-in-function$3#offset$86#surrounding-control-block$forEach");
        Mark.print("line-in-function$3#offset$91#statement$int[] m : moves");
        for (int[] m : moves) {
            Mark.print("statement${#line-of-block-in-function$3");
            Mark.print("line-in-function$4#offset$122#surrounding-control-block$if");
            if ((Mark.print("line-in-function$4#offset$126#statement$grid[row + m[1]][column + m[0]] == 0") && grid[row + m[1]][column + m[0]] == 0)) {
                Mark.print("statement${#line-of-block-in-function$4");
                Mark.print("line-in-function$5#offset$182#statement$num++;");
                num++;
                Mark.print("statement$}#line-of-block-in-function$4");
            }
            Mark.print("statement$}#line-of-block-in-function$3");
            Mark.print("line-in-function$3#offset$91#statement$int[] m : moves");
        }
        Mark.print("line-in-function$8#offset$221#statement$return num;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return num;
    }

    // Returns true if it is orphan
    private static boolean orphanDetected(int count, int row, int column) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$80#surrounding-control-block$if");
        if ((Mark.print("line-in-function$2#offset$84#statement$count < total - 1") && count < total - 1)) {
            Mark.print("statement${#line-of-block-in-function$2");
            Mark.print("line-in-function$3#offset$117#statement$List<int[]> neighbor=neighbors(row,column);");
            List<int[]> neighbor = neighbors(row, column);
            Mark.print("line-in-function$4#offset$176#surrounding-control-block$forEach");
            Mark.print("line-in-function$4#offset$181#statement$int[] nb : neighbor");
            for (int[] nb : neighbor) {
                Mark.print("statement${#line-of-block-in-function$4");
                Mark.print("line-in-function$5#offset$220#surrounding-control-block$if");
                if ((Mark.print("line-in-function$5#offset$224#statement$countNeighbors(nb[0],nb[1]) == 0") && countNeighbors(nb[0], nb[1]) == 0)) {
                    Mark.print("statement${#line-of-block-in-function$5");
                    Mark.print("line-in-function$6#offset$281#statement$return true;");
                    Mark.print("statement$}#line-of-block-in-function$5");
                    Mark.print("statement$}#line-of-block-in-function$4");
                    Mark.print("statement$}#line-of-block-in-function$2");
                    Mark.print("statement$}#line-of-block-in-function$1");
                    return true;
                }
                Mark.print("statement$}#line-of-block-in-function$4");
                Mark.print("line-in-function$4#offset$181#statement$int[] nb : neighbor");
            }
            Mark.print("statement$}#line-of-block-in-function$2");
        }
        Mark.print("line-in-function$10#offset$344#statement$return false;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return false;
    }

    // Prints the result grid
    private static void printResult() {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$44#surrounding-control-block$forEach");
        Mark.print("line-in-function$2#offset$49#statement$int[] row : grid");
        for (int[] row : grid) {
            Mark.print("statement${#line-of-block-in-function$2");
            Mark.print("line-in-function$3#offset$81#surrounding-control-block$forEach");
            Mark.print("line-in-function$3#offset$86#statement$int i : row");
            for (int i : row) {
                Mark.print("statement${#line-of-block-in-function$3");
                Mark.print("line-in-function$4#offset$117#surrounding-control-block$if");
                if ((Mark.print("line-in-function$4#offset$121#statement$i == -1") && i == -1)) {
                    Mark.print("statement${#line-of-block-in-function$4");
                    Mark.print("line-in-function$5#offset$152#statement$continue;");
                    Mark.print("statement$}#line-of-block-in-function$4");
                    Mark.print("statement$}#line-of-block-in-function$3");
                    continue;
                }
                Mark.print("line-in-function$7#offset$196#statement$System.out.printf(\"%2d \",i);");
                System.out.printf("%2d ", i);
                Mark.print("statement$}#line-of-block-in-function$3");
                Mark.print("line-in-function$3#offset$86#statement$int i : row");
            }
            Mark.print("line-in-function$9#offset$252#statement$System.out.println();");
            System.out.println();
            Mark.print("statement$}#line-of-block-in-function$2");
            Mark.print("line-in-function$2#offset$49#statement$int[] row : grid");
        }
        Mark.print("statement$}#line-of-block-in-function$1");
    }
}
