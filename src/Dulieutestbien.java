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
    // Tính cung hoàng đạo
    /*
     * intput : date, month
     * output: this function return zodiac given date
     */

    public static String calculateZodiac(int date, int month) {
        if ((month == 3 && date >= 21) || (month == 4 && date <= 19)) {
            return "ARIES";
        } else if ((month == 4 && date >= 20) || (month == 5 && date <= 20)) {
            return "TAURUS";
        } else if ((month == 5 && date >= 21) || (month == 6 && date <= 20)) {
            return "GEMINI";
        } else if ((month == 6 && date >= 21) || (month == 7 && date <= 22)) {
            return "CANCER";
        } else if ((month == 7 && date >= 23) || (month == 8 && date <= 22)) {
            return "LEO";
        } else if ((month == 8 && date >= 23) || (month == 9 && date <= 22)) {
            return "VIRGO";
        } else if ((month == 9 && date >= 23) || (month == 10 && date <= 22)) {
            return "LIBRA";
        } else if ((month == 10 && date >= 23) || (month == 11 && date <= 21)) {
            return "SCORPIO";
        } else if ((month == 11 && date >= 22) || (month == 12 && date <= 21)) {
            return "SAGITTARIUS";
        } else if ((month == 12 && date >= 22) || (month == 1 && date <= 19)) {
            return "CAPRICORN";
        } else if ((month == 1 && date >= 20) || (month == 2 && date <= 18)) {
            return "AQUARIUS";
        } else if ((month == 2 && date >= 19) || (month == 3 && date <= 20)) {
            return "PISCES";
        }
        return "UNKNOWN";
    }

    // Tính GCD của hai số nguyên
    // GCD(int, int)
    /*
     * intput : integer number m, n
     * output: return integer number correspond to GCD(m,n)
     */
//    public static int GCD(int m, int n) {
//        m = Math.abs(m);
//        n = Math.abs(n);
//        while (n != 0) {
//            int t = n;
//            n = m % n;
//            m = t;
//        }
//        return m;
//    }

    // Xác định loại tam giác
    /*
     * http://pathcrawler-online.com:8080/#
     * Example 1
     *
     * /* Should return the type of the triangle
     * input: 3 integer values correspond to 3 edges of a Triangle.
     * output: Should return the type of the triangle
     * which has sides of these lengths
     * 3 = not a triangle
     * 2 = equilateral triangle
     * 1 = isoceles triangle
     * 0 = other triangle
     *
     *
     * Error in function
     * Number of errors: 3
     * There are 2 mistakes in line 5. if i,j,k are all equal or greater than 0 and
     * (j+k=i or k+i = j) ,the function will return unexpected result
     * At line 7, if got condition at line 7, but i==j, the function may also return
     * unexpected result
     *
     */
    public static int Tritype(double i, double j, double k) {
        //Bi loi trong truong hop j + k = i || k + i = j, ket qua la tam giac Can
        if (i <= 0 || j <= 0 || k <= 0) {
            return 3;
        }
        if (i + j <= k || j + k < i || k + i < j) { //  if (i + j <=k || j + k <= i || k + i <=j)
            return 3;
        }
        int trityp = 0;
        if (i > j) // line 7  if (i == j) trityp = trityp + 1;
            trityp++;
        if (i == k)
            trityp++;
        if (j == k)
            trityp++;
        if (trityp >=2)
            return 2;
        return trityp;
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
    public static int isTriangle(int a, int b, int c) {
        if ((a<=b+c) && (b<=a+c) && (c < a+b)) {
            return 1;
        }
        return 0;
    }

    // Kiểm tra năm nhuận
    /*
     * https://www.geeksforgeeks.org/program-check-given-year-leap-year/
     * input: year
     * The function should return 1 if year is leap year, otherwise returns 0
     *
     * Error in function
     * Number of errors: 1
     * There is a mistake at line 2, if not ( year%4=0 and year%100!=0) and
     * year%400==0, the function will return unexpected result
     */
    // Đieu kien đung year % 4 ==0 && year % 100!=0 && year % 4!=0
    // Da thay dau && thanh dau ||
    public static int leapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 != 0)) {
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
    // ****************************************************************************

    /*
     * input: day, month, year
     * output:
     * Tinh so ngay la khoang thoi gian tu ngay 01/01/1900 den ngay day/month/year
     *
     * Error:
     * Cay loi tai dieu kien if (year==1990) thanh if (year>1900)
     * Khi Year>1900 thi cho ket qua khong mong muon, cau lenh if(year>1900 khong
     * thuc hien
     * hàm này lỗi *
     */
    public static long CDateToNumber(int day, int month, int year) {
        int[] ngay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        long s = 0;
        int i;

        if (year > 1900) {
            s = s + day;
            for (i = 0; i < month; i++)
                s = s + ngay[i];
        } else if (year > 1900) { // Note: This condition is logically unreachable.
            s = s + day;
            s = s + (year - 1900) * 365;
            for (i = 1900; i <= year; i++) {
                if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0))
                    s = s + 1;
            }
            for (i = 0; i < month; i++) {
                s = s + ngay[i];
                if (((i % 4 == 0) && (i % 100 != 0) || (i % 400 == 0)) && (i == 2)) s = s + 1;
            }
        }

        return s;
    }

    // ****************************************************************************
    /*
     * input: hour, minute, second
     * output: Total seconds from 0:0:0 to hour:minute:second
     * Error:
     * Cay loi tai dieu kien if (year==1990) thanh if (year>1900)
     * Khi Year>1900 thi cho ket qua khong mong muon, cau lenh if(year>1900 khong
     * thuc hien
     * Hàm CountSecond có một lỗi logic: nó chỉ chấp nhận giờ, phút và giây phải lớn
     * hơn 0. Điều này có nghĩa là thời gian 00:00:00 sẽ bị loại bỏ, trong khi đây
     * là một thời gian hợp lệ. Đồng thời, nó cũng loại bỏ các trường hợp mà giờ,
     * phút hoặc giây bằng 0, như 12:00:00 hay 00:30:45.*
     */
    public static long CountSecond(int hour, int minute, int second) {
        long s ;
        //Tinh tong so giay tinh tu luc 0:0:0 den hour:minute:second
        //if (hour>=0 && minute>=0 && second>=0 && hour<=24 && minute<=60 && second<=60) da bo het dau bang = trong cac dieu kien don
        s = -1;
        if (hour > 0 && minute > 0 && second > 0 && hour > 24 && minute < 60 && second < 60)
            s = hour * 3600 + minute * 60 + second;
        return s;
    }

    /*
     * Kiem tra 3 so nhap vao co tao thanh ngay thang hop le khong
     * input: day, month, year
     * output: return 1 if valid date; return 0 if invalid date
     * Error: replace if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 &&
     * year<=2020) --> 6 loi
     * by if (day>1 && month>1 && year>1 && day<31 && month<12 && year<2020)
     */
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

    public static int divisionTest(int a, int b) {
        int x = a + b;
        int y = x + 10;
        if (x < 10) {
            return 1 / x;
        } else {
            return 1 / y;
        }
    }

    /*
     * input: month, day, year
     * output: the next date
     *
     */
    public static long NextDate(int year, int month, int day) {
        int[] ngay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        day = day + 1;
        while (day >= 365) {
            year = year + 1;
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                day = day - 366;
            else
                day = day - 365;
        }

        while (day > ngay[month]) {
            day = day - ngay[month];
            month = month + 1;

            while (month >= 12) {
                year = year + 1;
                month = month % 12;
            }
        }
        return 0;
    }

    // ****************************************************************************

    /*
     * input: x, y
     * This function is created for loop testing.
     */
    public static int simpleWhileTest(int x, int y) {
        while (x < y) {
            x += 1;
        }
        return 1;
    }

    // ****************************************************************************

    /*
     * input: m, n
     * This function is created for loop testing.
     */
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

    // ****************************************************************************
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int i, j, min_idx, temp;
        // One by one move boundary of unsorted subarray
        for (i = 0; i < n - 1; i++) {
            min_idx = i;
            // Find the minimum element in unsorted array
            for (j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    // http://pathcrawler-online.com:8080/#

    /*
     * A classic example from test generation literature
     * which contains no loops or arrays
     * but is interesting for its simple example of an oracle.
     * This implementation does not handle negative inputs correctly
     */

    /* Determines the type of a triangle given its three edges i,j,k */
    /* 4 = Not a triangle */
    /* 3 = Equilateral triangle */
    /* 2 = Isosceles triangle */
    /* 1 = Any other triangle */
    public static int tritype0(int i, int j, int k) {
        int type_code;
        if ((i == 0) || (j == 0) || (k == 0)) {
            type_code = 4;
        } else {
            type_code = 0;
            if (i == j)
                type_code = type_code + 1;
            if (i == k)
                type_code = type_code + 2;
            if (j == k)
                type_code = type_code + 3;
            if (type_code == 0) {
                if ((i + j <= k) || (j + k <= i) || (i + k <= j)) {
                    type_code = 4;
                } else {
                    type_code = 1;
                }
            } else if (type_code > 3) {
                type_code = 3;
            } else if ((type_code == 1) && (i + j > k)) {
                type_code = 2;
            } else if ((type_code == 2) && (i + k > j)) {
                type_code = 2;
            } else if ((type_code == 3) && (j + k > i)) {
                type_code = 2;
            } else {
                type_code = 4;
            }
        }
        return type_code;
    }

    public static int leapYear1(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return 1; // leap year
                } else {
                    return 0; // not leap year
                }
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    // http://pathcrawler-online.com:8080/#
    // ExampleUnit

    // chưa xử lý phần mảng
//    public static int uninit_var(int[] a, int[] b) {
//        int k = 0;
//        for (int i = 0; i < 2; i++) {
//            if (a[i] == 0)
//                return 0;
//            if (a[i] != a[i + 1])
//                k = 0;
//            else if (k == 2)
//                return 0;
//            while (b[k] != a[i]) {
//                if (k == 2)
//                    return 0;
//                else
//                    k++;
//            }
//        }
//        return 1;
//    }
//    // Sach thay Hung
//
//    public static double Average(double[] value, double min, double max, int[] tcnt_vcnt) {
//        double sum = 0;
//        int i = 0;
//        int tcnt = 0, vcnt = 0;
//        while (value[i] != -999 && tcnt < 100) {
//            tcnt++;
//            if (min <= value[i] && value[i] <= max) {
//                sum += value[i];
//                vcnt++;
//            }
//            i++;
//        }
//        tcnt_vcnt[0] = tcnt;
//        tcnt_vcnt[1] = vcnt;
//        if (vcnt > 0)
//            return sum / vcnt;
//        return -999;
//    }

    /****************************************************************************************/
    public static int multiConditionTest(int x) {
        if (x < 0) {
            if (x > -10) {
                if (x > -5) {
                    return 1;
                } else if (x <= -5 && x >= -8) {
                    return 2;
                } else {
                    return 3;
                }
            } else {
                return -1;
            }
        } else if (x > 0 && x < 100) {
            if (x < 50) {
                if (x < 20) {
                    return 4;
                } else {
                    return 5;
                }
            } else if (x >= 50 && x < 80) {
                if (x == 60) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                return 8;
            }
        } else if (x == 101) {
            return 9;
        } else {
            return x;
        }
    }

    /****************************************************************************************/
    // cài lỗi x >= 1
    // có 1 lỗi xảy ra khi x = 1

    public static float distanceTest(float x) {
        if (x > 1.0) {
            if (x < 1.001) {
                if (x < 1.00001) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 3;
            }
        } else if (x <= 1.0 && x >= 0.999) {
            return 4;
        }
        return 5;
    }

    /****************************************************************************************/

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

    /****************************************************************************************/

    public static int mmin(int a, int b) {
        return 1;
    }


    //
    public static int gcd3(int a, int b) {
        int temp;

        while (b > 0) {
            temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
    // D:\OneDrive - Cao Dang Su Pham Trung
    // Uong\NCS\ava\data-test\tsdv\Sample_for_R1_4

    // Da them 4 loi
    // Loi tai age=4,14,15, distance=10

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

    public static short SimpleCondThanComplexCond(short a, short b, short c) {
        if ((a > 0) && (c > 3) && (b > -10)) {
            if (b > 0)
                if ((a > b) && (c > 5))
                    if (a > c)
                        if ((a > 10) && (b > 10) && (c > 10))
                            if ((a <= 17) && (c <= 15) && (b < 20))
                                return 0;
        }
        return 1;
    }

    public static long MoreComplexCond(long a, long b, long c, long d, long e) {
        if ((a > 0) && (c > 3) && (a + b > -10) && (a + b + c > 0) & (a + b - e < 0))
            if ((b > 2) && (b > c))
                if (a + b > d)
                    if (c + d - e < 0)
                        if (a > b)
                            if (a > c)
                                if ((a - b > 2) && (4 * b - 5 * c == 0) && (a - c == 8) && (2 * c > b))
                                    if (a + d == e)
                                        if ((2 * b > a) && (b + c > a))
                                            return 0;
        return 1;
    }


    // cài 15 lỗi thêm dấu = vào các điều kiện
    // lỗi xảy ra khi a = { 10, 20, 30, 40 ,50}, b = { 15, 25, 35, 45, 55}, c = {5, 10, 20, 30, 40}
    public static float multilParaTypeTest(short a, int b, float c) {
        float x = 0;
        if ((a <= 10) && (b <= 15) && (c <= 5))
            x = 1;
        else if ((a <= 20) && (b <= 25) && (c <= 10))
            x = 2;
        else if ((a <= 30) && (b <= 35) && (c <= 20))
            x = 3;
        else if ((a <= 40) && (b <= 45) && (c <= 30))
            x = 4;
        else if ((a <= 50) && (b <= 55) && (c <= 40))
            x = 5;
        else
            x = 6;
        return x;
    }

//    public static double average(double[] value, double min, double max, int[] tcnt_vcnt) {
//        double sum = 0;
//        int i = 0;
//        int tcnt = 0, vcnt = 0;
//        while (value[i] != -999 && tcnt < 100) {
//            tcnt++;
//            if (min <= value[i] && value[i] <= max) {
//                sum += value[i];
//                vcnt++;
//            }
//            i++;
//        }
//        tcnt_vcnt[0] = tcnt;
//        tcnt_vcnt[1] = vcnt;
//        if (vcnt > 0)
//            return sum / vcnt;
//        return -999;
//    }

    public static int twoWhileloop(int m, int n) {
        int s = 0;
        int i, j;
        if (m > 0 && n > 0) {
            i = 1;
            j = 1;
            while (i <= m) {
                while (j <= n) {
                    s = s + i + j;
                    j = j + 1;
                }
                i = i + 1;
            }
        }
        return s;
    }
}
