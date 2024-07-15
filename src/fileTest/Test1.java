package fileTest;

public class Test1 {
    public String hamKiemTraTuGiac(int a, int b, int c, int d) {
        if (a + b + c + d != 360 || a == 180 || b == 180 || c == 180 || d == 180) {
            return "Khong phai la tu giac";
        }
        if (a == 90 && b == 90 && c == 90 && d == 90) {
            return "Khong phai la tu giac";
        }
        if (a == c && b == d) {
            return "Hinh binh hanh";
        }
        if (a < 180 && b < 180 && c < 180 && d < 180) {
            return "Khong phai la tu giac";
        }
        return "Tu giac lom";
    }
}