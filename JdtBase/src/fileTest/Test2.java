package fileTest;

public class Test2 {

    public String multiCondition(int a, int b, String Statis, String Unstatis) {
        int i, j;
        for (i = 1, j = 1; i < 5 || j < 4; i++, j++) {
            if (a > 0 && (a % 5 > i || b > j)) {
                return Statis;
            }
        }
        return Unstatis;
    }
}
