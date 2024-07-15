package fileCloned;

public class Test6 {
    public static void test() {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        try {
            Mark.print("statement${#line-of-block-in-function$2");
            Mark.print("line-in-function$3#offset$53#statement$int result=divideByZero(10,0);");
            int result = divideByZero(10, 0);
            Mark.print("line-in-function$4#offset$99#statement$System.out.println(\"Kết quả: \" + result);");
            System.out.println("Kết quả: " + result);
            Mark.print("statement$}#line-of-block-in-function$2");
        } catch (ArithmeticException e) {
            Mark.print("line-in-function$5#offset$158#statement$ArithmeticException e");
            Mark.print("statement${#line-of-block-in-function$5");
            Mark.print("line-in-function$6#offset$195#statement$System.out.println(\"Lỗi chia cho 0: \" + e.getMessage());");
            System.out.println("Lỗi chia cho 0: " + e.getMessage());
            Mark.print("statement$}#line-of-block-in-function$5");
        } finally {
            Mark.print("statement$finally");
            Mark.print("statement${#line-of-block-in-function$7");
            Mark.print("line-in-function$8#offset$284#statement$System.out.println(\"Khối finally được thực hiện.\");");
            System.out.println("Khối finally được thực hiện.");
            Mark.print("statement$}#line-of-block-in-function$7");
        }
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    public static int divideByZero(int dividend, int divisor) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$68#surrounding-control-block$if");
        if ((Mark.print("line-in-function$2#offset$72#statement$divisor == 0") && divisor == 0)) {
            Mark.print("statement${#line-of-block-in-function$2");
            Mark.print("line-in-function$4#offset$171#statement$throw new ArithmeticException(\"Không thể chia cho 0.\");");
            Mark.print("statement$}#line-of-block-in-function$2");
            throw new ArithmeticException("Không thể chia cho 0.");
        }
        Mark.print("line-in-function$6#offset$245#statement$return dividend / divisor;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return dividend / divisor;
    }
}
