package fileTest;

public class Test4 {

    public double test(int x) {
        int[] numbers = {1, 2, 3, 4, 5, 2, 9, 7, 5};
        int sum = 0;
        int i = 0;
        while ( i < 9) {
            if (numbers[i] >= x + 1) {
                sum += numbers[i];
            }
            i = i + 1;
        }
        return sum;
    }
}
