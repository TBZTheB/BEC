package fileTest;

public class Test5 {
    public static void main(String[] args) {
        int a = 1, sum = 0;
        do {
            sum += a;
            a++;
        } while (a <= 5);
        System.out.println("Sum of 1 to 5  is " + sum);
    }
}
