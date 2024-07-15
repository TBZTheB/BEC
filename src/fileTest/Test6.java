package fileTest;

public class Test6 {
    public static void test(){
        try {
            int result = divideByZero(10, 0);
            System.out.println("Kết quả: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Lỗi chia cho 0: " + e.getMessage());
        } finally {
            System.out.println("Khối finally được thực hiện.");
        }
    }

    public static int divideByZero(int dividend, int divisor) {
        if (divisor == 0) {
            // Gây ra ngoại lệ kiểu ArithmeticException nếu chia cho 0
            throw new ArithmeticException("Không thể chia cho 0.");
        }
        return dividend / divisor;
    }
}
