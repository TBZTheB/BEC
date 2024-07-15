public class TestMinMaxValue {
    public static void main(String[] args) {
        int a = 5;
        System.out.println(getMinValue(a));
        System.out.println(getMaxValue(a));
    }

    public static Number getMinValue(int a) {
        switch (a) {
            case 1:
                return Byte.MIN_VALUE;
            case 2:
                return Short.MIN_VALUE;
            case 3:
                return Integer.MIN_VALUE;
            case 4:
                return Long.MIN_VALUE;
            case 5:
                return Float.MIN_VALUE;
            case 6:
                return Double.MIN_VALUE;
            default:
                return null;
        }
    }

    public static Number getMaxValue(int a) {
        switch (a) {
            case 1:
                return Byte.MAX_VALUE;
            case 2:
                return Short.MAX_VALUE;
            case 3:
                return Integer.MAX_VALUE;
            case 4:
                return Long.MAX_VALUE;
            case 5:
                return Float.MAX_VALUE;
            case 6:
                return Double.MAX_VALUE;
            default:
                return null;
        }
    }
}
