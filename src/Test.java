import java.awt.*;
import java.util.ArrayList;

public class Test {
    public int compare_numbers(int a, int b, int c) {
        int max = a;
        if (b >  max) {
            max = b;
        } else if (c > max) {
            max = c;
        }
        return max;
    }


}