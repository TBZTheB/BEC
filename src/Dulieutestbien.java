import java.util.Scanner;

public class Dulieutestbien {

    // Tính giai thừa của x
    /*
     * Source getting from website: https://www.geeksforgeeks.org/
     */
    // https://www.geeksforgeeks.org/gcd-factorials-two-numbers/ //tinh x!
    // Error: x=1
    /*
     * intput : x
     * output: 1*2*3*4*...*x
     *
     * Error in function
     * Number of error: 1
     * if x=1 function will return unexpected result (1!=2)
     */
     // STT 1
    public static int factorial(int x) {
        if (x <= 1) {// x<=1, error
            return 1;
        }
        int res = 1;
        for (int i = 2; i <= x; i++) {
            res *= i;
        }
        return res;
    }
    // Chuyển đổi điểm trung bình thành ký tự
    // ****************************************************************************
    /*
     * intput : averageGrade
     * output: return charecter correspond to given averageGrade
     * in 90-100, return A
     * in 80-89, return B
     * in 70-79, return C
     * in 60-69, return D
     * in 0-59, return F
     * else, return I
     *
     * Error in function
     * Number of error: 6
     * if averageGrade is in {100,90,80,70,60,0} the function will return unexpected
     * result
     * 50 60 70
     * 70 80 90
     */

    //STT 2
    public static char grade(int averageGrade) {
        if (averageGrade > 90 && averageGrade < 100)
            return 'A';
        else if (averageGrade > 80 && averageGrade < 90)
            return 'B';
        else if (averageGrade > 70 && averageGrade < 80)
            return 'C';
        else if (averageGrade > 60 && averageGrade < 70)
            return 'D';
        else if (averageGrade > 0 && averageGrade < 60)
            return 'F';
        return 'I'; // 'I' for invalid input
    }

    // Tính giá vé dựa trên tuổi và khoảng cách
    // ****************************************************************************
    /*
     * intput : age, distance
     * output: return integer number correspond to given age, distance
     *
     * Error in function
     * Number of error: 4
     * if age in {4, 14, 15}, distance=10 the function will return unexpected result
     * note: replace
     * //if(age >= 4 && age <= 14) by if(age > 4 && age < 14)
     * //if(age >= 15) by if(age > 15)
     * //if(distance > 10) by if(distance >= 10)
     */
    //STT 3
    public static int getFare(int age, int distance) {
        int fare = 0;
        // if(age >= 4 && age <= 14)
        if (age > 4 && age < 14) {
            // if(distance > 10)
            if (distance >= 10) {
                fare = 130;
            } else {
                fare = 100;
            }
        }
        // if(age >= 15)
        if (age > 15) {
            if (distance < 10 && age >= 60) {
                fare = 160;
            } else if (distance > 10 && age < 60) {
                fare = 250;
            } else {
                fare = 200;
            }
        }
        return fare;
    }

    // Tính i mũ j
    // D:\OneDrive - Cao Dang Su Pham Trung
    // Uong\NCS\ava\data-test\tsdv\Sample_for_R1_4\r85.cpp
    /*
     * Error:
     * Case 1
     * Goc: i=0, j=0 thì return -1
     * Sua: i>=0, j=0 thì return -1
     * Ví dụ: 2^0=-1
     *
     * Case 2
     * Goc: i bat ky, j=1 thi return i
     * Sua:
     * i bat ky, j>=1 thi return i
     * vi du: 2^3=2
     * Co 2 loi voi cac bo test
     * (i >=0; j=0)
     * (i bat ky, j>=1)
     *
     */
    //STT 4
    public static int i4_power(int i, int j) {

        int value;

        if (j < 0) {
            if (i == 1) {
                value = 1;
            } else if (i == 0) {
                // cerr << "\n";
                // cerr << "I4_POWER - Fatal error!\n";
                // cerr << " I^J requested, with I = 0 and J negative.\n";
                // exit ( 1 );
                return -1;
            } else {
                value = 0;
            }
        } else if (j == 0) { // j==0
            if (i == 0) {// i==0; add Error
                // cerr << "\n";
                // cerr << "I4_POWER - Fatal error!\n";
                // cerr << " I^J requested, with I = 0 and J = 0.\n";
                // exit ( 1 );
                return -1;
            } else {
                value = 1;
            }
        } else if (j >= 1) {//j==1, add Error
            value = i;
        } else {
            value = 1;
            for (int k = 1; k <= j; k++) {
                value *= i;
            }
        }
        return value;
    }
    // Phân loại dựa theo Math và English
    /*
     * Type A: Math + English >=180 and Math >= 50 && English>=60
     * Type B: Math >= 80 || English >=90 and Math >=50 && English>=60
     * Type C: Math>=50 and English>=60 and not in Type A, Type B
     * Type D: Other
     * Code:
     * Add error at boundary,
     * Math > 50 && English >60 thay vi Math >= 50 && English >=60
     * Math > 80 || English >90 thay vi Math >= 80 || English >=90
     * Dead code: if (Math + English >=180) return 'A';
     * Khong bao gio tra ve type 'A'
     *
     */
    //STT 5
    public static char MathEnglishGrade(int Math, int English) {
        //Loi ta cac truong hop Math=50, math=80, English=60, English=90
        //if(Math>=50 && English>=60)
        if (Math > 50 && English > 60) {
            //if(Math>=80 || English>=90)
            if (Math > 80 || English > 90) {
                return 'B';
            } else if (Math + English >= 180) {
                return 'A';
            } else {
                return 'C';
            }
        }
        return 'D';
    }

    // Kiểm tra xem ba số có tạo thành tam giác không
    /*
     * input: a,b,c
     * Should return 1, if a,b,c represent a Triangle, otherwise return 0;
     * Detail: a<b+c && b<a+c && c<a+b, EO: 1
     * otherwise, EO: 0
     *
     * Error in function
     * Number of errors: 1
     * if a=b+c or b= a+c, the function will return unexpected result
     * Bo test a=b=1, c=0 se tim ra loi
     */
    //STT 6
    public static int isTriangle(int a, int b, int c) {
        if ((a<=b+c) && (b<=a+c) && (c < a+b)) {
            return 1;
        }
        return 0;
    }
    // ****************************************************************************
    // Tính xác suất PDF
    /*
     * input: x,minn,maxx
     * Should return probability density function of the continuous uniform
     * distribution
     * if maxx<minn or maxx=minn or x = maxx or x = minn the function may will
     * return unexpected result
     */
    //STT 7
    public static float PDF(int x, int minn, int maxx) {
        if (x <= minn || x >= maxx) {
            return 0;
        }
        return 1.0f / (maxx - minn);
    }

    /*
     * input: interger number a,b,c,d
     * output: float number
     * Error:
     * Function may will return unexpected result if a>0
     */
    //STT 8
    static float foo(int a, int b, int c, int d) {
        float e;
        if (a > 0)
            return 0;  //Cay loi a==0 thanh a>=0. If a>0 Then UnExpected output
        int x = 0;
        if ((a == b) || (c == d)) {
            x = 1;
        }
        e = 1 / x;
        return e;
    }
    //STT 9
    public static int CheckValidDate(int day, int month, int year) {// Tuong doi cham
        // if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 && year<=2020) -->
        // 6 loi
        if (day > 1 && month > 1 && year > 1 && day < 31 && month < 12 && year < 2020) {
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    && (day <= 31))
                return 1;
            if ((month == 4 || month == 6 || month == 9 || month == 11) && (day <= 30))
                return 1;
            if ((month == 2) && (day < 28))
                return 1; // day<=28
            if ((month == 2) && (day == 29) && (year % 4 == 0) && (year % 400 != 0))
                return 1;
        }
        return 0;
    }

    // ****************************************************************************
    /*
     * Kiem tra 3 so nhap vao co tao thanh thoi gia hop le khong
     * input: hour, minute, second
     * output: return 1 if valid time; return 0 if invalid time
     * Error: replace if ((hour>=0 && hour<=23) && (minute>=0 && minute<=59) &&
     * (second>=0 && second<=59)) bo tat ca dau bang "=" trong dieu kien don
     * by if (hour>0 && hour<23 && minute>0 && minute<59 && second>0 && second<59)
     */
    //STT 10
    public static int CheckValidTime(int hour, int minute, int second) {
        if (hour > 0 && hour < 23 && minute > 0 && minute < 59 && second > 0 && second < 59)
            return 1;
        return 0;
    }
    // ****************************************************************************

    /*
     * input: a,b
     * output: if a+b < -10, the function should return 1/(a+b)
     * if a+b = 0 or a+b+1 = 0, the function should return 0;
     * other wise, the function should return 1/(a+b+1)
     * Error in function: 2
     * Number of mistakes: if a+b = 0 or a+b+10 = 0, the function may return
     * unexpected result;
     */
    //STT 11
    public static int divisionTest(int a, int b) {
        int x = a + b;
        int y = x + 10;
        if (x < 10) {
            return 1 / x;
        } else {
            return 1 / y;
        }
    }
    // ****************************************************************************

    /*
     * input: m, n
     * This function is created for loop testing.
     */
    //STT 12
    public static int Forloop(int m, int n) {
        int s = 0;
        if (m > 0 && n > 0) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    s = s + i + j;
                }
            }
        }
        return s;
    }
    //STT 13
    public static double smallIntervalTest(double x) {
        if (x > 1.0) {
            if (x > 2.00001 && x < 2.000015) {
                return 1;
            } else if (x <= 2.00001 && x >= 1.9999) {
                return 2;
            } else {
                return 3;
            }
        }
        return 4;
    }

    // D:\OneDrive - Cao Dang Su Pham Trung
    // Uong\NCS\ava\data-test\tsdv\Sample_for_R1_4

    // Da them 4 loi
    // Loi tai age=4,14,15, distance=10
    //STT 3
    public static int getFare1(int age, int distance) {
        // Da them 4 loi
        // Loi tai age=4,14,15, distance=10
        int fare = 0;
        if (age > 4 && age < 14) {// if(age >= 4 && age <= 14), 2 loi
            if (distance >= 10) {// if(distance > 10), 1 loi
                fare = 130;
            } else {
                fare = 100;
            }
        }
        if (age > 15) {// (age >= 15) , 1 loi
            if (distance < 10 && age >= 60) {
                fare = 160;
            } else if (distance > 10 && age < 60) {
                fare = 250;
            } else {
                fare = 200;
            }
        }
        return fare;
    }
}
