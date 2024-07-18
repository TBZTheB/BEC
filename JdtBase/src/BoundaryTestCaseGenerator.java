import org.eclipse.jdt.core.dom.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BoundaryTestCaseGenerator {

    public static void main(String[] args) {
        String source =
                "public class Dulieutestbien {\n" +
                        "\n" +
                        "    // Tính giai thừa của x\n" +
                        "    /*\n" +
                        "     * Source getting from website: https://www.geeksforgeeks.org/\n" +
                        "     */\n" +
                        "    // https://www.geeksforgeeks.org/gcd-factorials-two-numbers/ //tinh x!\n" +
                        "    // Error: x=1\n" +
                        "    /*\n" +
                        "     * intput : x\n" +
                        "     * output: 1*2*3*4*...*x\n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of error: 1\n" +
                        "     * if x=1 function will return unexpected result (1!=2)\n" +
                        "     */\n" +
                        "    public static int factorial(int x) {\n" +
                        "        if (x <= 1) {// x<=1, error\n" +
                        "            return 1;\n" +
                        "        }\n" +
                        "        int res = 1;\n" +
                        "        for (int i = 2; i <= x; i++) {\n" +
                        "            res *= i;\n" +
                        "        }\n" +
                        "        return res;\n" +
                        "    }\n" +
                        "    // Chuyển đổi điểm trung bình thành ký tự\n" +
                        "    // ****************************************************************************\n" +
                        "    /*\n" +
                        "     * intput : averageGrade\n" +
                        "     * output: return charecter correspond to given averageGrade\n" +
                        "     * in 90-100, return A\n" +
                        "     * in 80-89, return B\n" +
                        "     * in 70-79, return C\n" +
                        "     * in 60-69, return D\n" +
                        "     * in 0-59, return F\n" +
                        "     * else, return I\n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of error: 6\n" +
                        "     * if averageGrade is in {100,90,80,70,60,0} the function will return unexpected\n" +
                        "     * result\n" +
                        "     */\n" +
                        "\n" +
                        "    public static char grade(int averageGrade) {\n" +
                        "        if (averageGrade > 90 && averageGrade <= 100)\n" +
                        "            return 'A';\n" +
                        "        else if (averageGrade > 80 && averageGrade <= 90)\n" +
                        "            return 'B';\n" +
                        "        else if (averageGrade > 70 && averageGrade <= 80)\n" +
                        "            return 'C';\n" +
                        "        else if (averageGrade > 60 && averageGrade <= 70)\n" +
                        "            return 'D';\n" +
                        "        else if (averageGrade >= 0 && averageGrade <= 60)\n" +
                        "            return 'F';\n" +
                        "        return 'I'; // 'I' for invalid input\n" +
                        "    }\n" +
                        "\n" +
                        "    // Tính giá vé dựa trên tuổi và khoảng cách\n" +
                        "    // ****************************************************************************\n" +
                        "    /*\n" +
                        "     * intput : age, distance\n" +
                        "     * output: return integer number correspond to given age, distance\n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of error: 4\n" +
                        "     * if age in {4, 14, 15}, distance=10 the function will return unexpected result\n" +
                        "     * note: replace\n" +
                        "     * //if(age >= 4 && age <= 14) by if(age > 4 && age < 14)\n" +
                        "     * //if(age >= 15) by if(age > 15)\n" +
                        "     * //if(distance > 10) by if(distance >= 10)\n" +
                        "     */\n" +
                        "    public static int getFare(int age, int distance) {\n" +
                        "        int fare = 0;\n" +
                        "        // if(age >= 4 && age <= 14)\n" +
                        "        if (age > 4 && age < 14) {\n" +
                        "            // if(distance > 10)\n" +
                        "            if (distance >= 10) {\n" +
                        "                fare = 130;\n" +
                        "            } else {\n" +
                        "                fare = 100;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        // if(age >= 15)\n" +
                        "        if (age > 15) {\n" +
                        "            if (distance < 10 && age >= 60) {\n" +
                        "                fare = 160;\n" +
                        "            } else if (distance > 10 && age < 60) {\n" +
                        "                fare = 250;\n" +
                        "            } else {\n" +
                        "                fare = 200;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return fare;\n" +
                        "    }\n" +
                        "\n" +
                        "    // Tính i mũ j\n" +
                        "    // D:\\OneDrive - Cao Dang Su Pham Trung\n" +
                        "    // Uong\\NCS\\ava\\data-test\\tsdv\\Sample_for_R1_4\\r85.cpp\n" +
                        "    /*\n" +
                        "     * Error:\n" +
                        "     * Case 1\n" +
                        "     * Goc: i=0, j=0 thì return -1\n" +
                        "     * Sua: i>=0, j=0 thì return -1\n" +
                        "     * Ví dụ: 2^0=-1\n" +
                        "     * \n" +
                        "     * Case 2\n" +
                        "     * Goc: i bat ky, j=1 thi return i\n" +
                        "     * Sua:\n" +
                        "     * i bat ky, j>=1 thi return i\n" +
                        "     * vi du: 2^3=2\n" +
                        "     * Co 2 loi voi cac bo test\n" +
                        "     * (i >=0; j=0)\n" +
                        "     * (i bat ky, j>=1)\n" +
                        "     * \n" +
                        "     */\n" +
                        "    public static int i4_power(int i, int j) {\n" +
                        "        // ****************************************************************************80\n" +
                        "        //\n" +
                        "        // Purpose:\n" +
                        "        //\n" +
                        "        // I4_POWER returns the value of I^J.\n" +
                        "        //\n" +
                        "        // Licensing:\n" +
                        "        //\n" +
                        "        // This code is distributed under the GNU LGPL license.\n" +
                        "        //\n" +
                        "        // Modified:\n" +
                        "        //\n" +
                        "        // 01 April 2004\n" +
                        "        //\n" +
                        "        // Author:\n" +
                        "        //\n" +
                        "        // John Burkardt\n" +
                        "        //\n" +
                        "        // Parameters:\n" +
                        "        //\n" +
                        "        // Input, int I, J, the base and the power. J should be nonnegative.\n" +
                        "        //\n" +
                        "        // Output, int I4_POWER, the value of I^J.\n" +
                        "        //\n" +
                        "\n" +
                        "        int value;\n" +
                        "\n" +
                        "        if (j < 0) {\n" +
                        "            if (i == 1) {\n" +
                        "                value = 1;\n" +
                        "            } else if (i == 0) {\n" +
                        "                // cerr << \"\\n\";\n" +
                        "                // cerr << \"I4_POWER - Fatal error!\\n\";\n" +
                        "                // cerr << \" I^J requested, with I = 0 and J negative.\\n\";\n" +
                        "                // exit ( 1 );\n" +
                        "                return -1;\n" +
                        "            } else {\n" +
                        "                value = 0;\n" +
                        "            }\n" +
                        "        } else if (j == 0) { // j==0\n" +
                        "            if (i == 0) {// i==0; add Error\n" +
                        "                // cerr << \"\\n\";\n" +
                        "                // cerr << \"I4_POWER - Fatal error!\\n\";\n" +
                        "                // cerr << \" I^J requested, with I = 0 and J = 0.\\n\";\n" +
                        "                // exit ( 1 );\n" +
                        "                return -1;\n" +
                        "            } else {\n" +
                        "                value = 1;\n" +
                        "            }\n" +
                        "        } else if (j >= 1) {//j==1, add Error\n" +
                        "            value = i;\n" +
                        "        } else {\n" +
                        "            value = 1;\n" +
                        "            for (int k = 1; k <= j; k++) {\n" +
                        "                value *= i;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return value;\n" +
                        "    }\n" +
                        "    // Tính cung hoàng đạo\n" +
                        "    /*\n" +
                        "     * intput : date, month\n" +
                        "     * output: this function return zodiac given date\n" +
                        "     */\n" +
                        "\n" +
                        "    public static String calculateZodiac(int date, int month) {\n" +
                        "        if ((month == 3 && date >= 21) || (month == 4 && date <= 19)) {\n" +
                        "            return \"ARIES\";\n" +
                        "        } else if ((month == 4 && date >= 20) || (month == 5 && date <= 20)) {\n" +
                        "            return \"TAURUS\";\n" +
                        "        } else if ((month == 5 && date >= 21) || (month == 6 && date <= 20)) {\n" +
                        "            return \"GEMINI\";\n" +
                        "        } else if ((month == 6 && date >= 21) || (month == 7 && date <= 22)) {\n" +
                        "            return \"CANCER\";\n" +
                        "        } else if ((month == 7 && date >= 23) || (month == 8 && date <= 22)) {\n" +
                        "            return \"LEO\";\n" +
                        "        } else if ((month == 8 && date >= 23) || (month == 9 && date <= 22)) {\n" +
                        "            return \"VIRGO\";\n" +
                        "        } else if ((month == 9 && date >= 23) || (month == 10 && date <= 22)) {\n" +
                        "            return \"LIBRA\";\n" +
                        "        } else if ((month == 10 && date >= 23) || (month == 11 && date <= 21)) {\n" +
                        "            return \"SCORPIO\";\n" +
                        "        } else if ((month == 11 && date >= 22) || (month == 12 && date <= 21)) {\n" +
                        "            return \"SAGITTARIUS\";\n" +
                        "        } else if ((month == 12 && date >= 22) || (month == 1 && date <= 19)) {\n" +
                        "            return \"CAPRICORN\";\n" +
                        "        } else if ((month == 1 && date >= 20) || (month == 2 && date <= 18)) {\n" +
                        "            return \"AQUARIUS\";\n" +
                        "        } else if ((month == 2 && date >= 19) || (month == 3 && date <= 20)) {\n" +
                        "            return \"PISCES\";\n" +
                        "        }\n" +
                        "        return \"UNKNOWN\";\n" +
                        "    }\n" +
                        "\n" +
                        "    // Tính GCD của hai số nguyên\n" +
                        "    // GCD(int, int)\n" +
                        "    /*\n" +
                        "     * intput : integer number m, n\n" +
                        "     * output: return integer number correspond to GCD(m,n)\n" +
                        "     */\n" +
                        "    public static int GCD(int m, int n) {\n" +
                        "        m = Math.abs(m);\n" +
                        "        n = Math.abs(n);\n" +
                        "        while (n != 0) {\n" +
                        "            int t = n;\n" +
                        "            n = m % n;\n" +
                        "            m = t;\n" +
                        "        }\n" +
                        "        return m;\n" +
                        "    }\n" +
                        "\n" +
                        "    // Xác định loại tam giác\n" +
                        "    /*\n" +
                        "     * http://pathcrawler-online.com:8080/#\n" +
                        "     * Example 1\n" +
                        "     * \n" +
                        "     * /* Should return the type of the triangle\n" +
                        "     * input: 3 integer values correspond to 3 edges of a Triangle.\n" +
                        "     * output: Should return the type of the triangle\n" +
                        "     * which has sides of these lengths\n" +
                        "     * 3 = not a triangle\n" +
                        "     * 2 = equilateral triangle\n" +
                        "     * 1 = isoceles triangle\n" +
                        "     * 0 = other triangle\n" +
                        "     * \n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of errors: 3\n" +
                        "     * There are 2 mistakes in line 5. if i,j,k are all equal or greater than 0 and\n" +
                        "     * (j+k=i or k+i = j) ,the function will return unexpected result\n" +
                        "     * At line 7, if got condition at line 7, but i==j, the function may also return\n" +
                        "     * unexpected result\n" +
                        "     * \n" +
                        "     */\n" +
                        "    public static int Tritype(double i, double j, double k) {\n" +
                        "         //Bi loi trong truong hop j + k = i || k + i = j, ket qua la tam giac Can\n" +
                        "        if (i <= 0 || j <= 0 || k <= 0) {\n" +
                        "            return 3;\n" +
                        "        }\n" +
                        "        if (i + j <= k || j + k < i || k + i < j) { //  if (i + j <=k || j + k <= i || k + i <=j)\n" +
                        "            return 3;\n" +
                        "        }\n" +
                        "        int trityp = 0;\n" +
                        "        if (i > j) // line 7  if (i == j) trityp = trityp + 1;\n" +
                        "            trityp++;\n" +
                        "        if (i == k)\n" +
                        "            trityp++;\n" +
                        "        if (j == k)\n" +
                        "            trityp++;\n" +
                        "        if (trityp >=2)\n" +
                        "            return 2; \n" +
                        "        return trityp;\n" +
                        "    }\n" +
                        "\n" +
                        "    // Phân loại dựa theo Math và English\n" +
                        "    /*\n" +
                        "     * Type A: Math + English >=180 and Math >= 50 && English>=60\n" +
                        "     * Type B: Math >= 80 || English >=90 and Math >=50 && English>=60\n" +
                        "     * Type C: Math>=50 and English>=60 and not in Type A, Type B\n" +
                        "     * Type D: Other\n" +
                        "     * Code:\n" +
                        "     * Add error at boundary,\n" +
                        "     * Math > 50 && English >60 thay vi Math >= 50 && English >=60\n" +
                        "     * Math > 80 || English >90 thay vi Math >= 80 || English >=90\n" +
                        "     * Dead code: if (Math + English >=180) return 'A';\n" +
                        "     * Khong bao gio tra ve type 'A'\n" +
                        "     * \n" +
                        "     */\n" +
                        "    public static char MathEnglishGrade(int Math, int English) {\n" +
                        "    //Loi ta cac truong hop Math=50, math=80, English=60, English=90\n" +
                        "\t//if(Math>=50 && English>=60)\n" +
                        "        if (Math > 50 && English > 60) {\n" +
                        "            //if(Math>=80 || English>=90)\n" +
                        "            if (Math > 80 || English > 90) {\n" +
                        "                return 'B';\n" +
                        "            } else if (Math + English >= 180) {\n" +
                        "                return 'A';\n" +
                        "            } else {\n" +
                        "                return 'C';\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return 'D';\n" +
                        "    }\n" +
                        "\n" +
                        "    // Kiểm tra xem ba số có tạo thành tam giác không\n" +
                        "    /*\n" +
                        "     * input: a,b,c\n" +
                        "     * Should return 1, if a,b,c represent a Triangle, otherwise return 0;\n" +
                        "     * Detail: a<b+c && b<a+c && c<a+b, EO: 1\n" +
                        "     * otherwise, EO: 0\n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of errors: 1\n" +
                        "     * if a=b+c or b= a+c, the function will return unexpected result\n" +
                        "     * Bo test a=b=1, c=0 se tim ra loi\n" +
                        "     */\n" +
                        "    public static int isTriangle(int a, int b, int c) {\n" +
                        "        if ((a<=b+c) && (b<=a+c) && (c < a+b)) {\n" +
                        "            return 1;\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    // Kiểm tra năm nhuận\n" +
                        "    /*\n" +
                        "     * https://www.geeksforgeeks.org/program-check-given-year-leap-year/\n" +
                        "     * input: year\n" +
                        "     * The function should return 1 if year is leap year, otherwise returns 0\n" +
                        "     * \n" +
                        "     * Error in function\n" +
                        "     * Number of errors: 1\n" +
                        "     * There is a mistake at line 2, if not ( year%4=0 and year%100!=0) and\n" +
                        "     * year%400==0, the function will return unexpected result\n" +
                        "     */\n" +
                        "    // Đieu kien đung year % 4 ==0 && year % 100!=0 && year % 4!=0\n" +
                        "    // Da thay dau && thanh dau ||\n" +
                        "    public static int leapYear(int year) {\n" +
                        "        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 != 0)) {\n" +
                        "            return 1;\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "    // ****************************************************************************\n" +
                        "    // Tính xác suất PDF\n" +
                        "    /*\n" +
                        "     * input: x,minn,maxx\n" +
                        "     * Should return probability density function of the continuous uniform\n" +
                        "     * distribution\n" +
                        "     * if maxx<minn or maxx=minn or x = maxx or x = minn the function may will\n" +
                        "     * return unexpected result\n" +
                        "     */\n" +
                        "\n" +
                        "    public static float PDF(int x, int minn, int maxx) {\n" +
                        "        if (x <= minn || x >= maxx) {\n" +
                        "            return 0;\n" +
                        "        }\n" +
                        "        return 1.0f / (maxx - minn);\n" +
                        "    }\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: interger number a,b,c,d\n" +
                        "     * output: float number\n" +
                        "     * Error:\n" +
                        "     * Function may will return unexpected result if a>0\n" +
                        "     */\n" +
                        "    static float foo(int a, int b, int c, int d) {\n" +
                        "        float e;\n" +
                        "        if (a > 0)\n" +
                        "            return 0;  //Cay loi a==0 thanh a>=0. If a>0 Then UnExpected output\n" +
                        "        int x = 0;\n" +
                        "        if ((a == b) || (c == d)) {\n" +
                        "            x = 1;\n" +
                        "        }\n" +
                        "        e = 1 / x;\n" +
                        "        return e;\n" +
                        "    }\n" +
                        "    // ****************************************************************************\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: day, month, year\n" +
                        "     * output:\n" +
                        "     * Tinh so ngay la khoang thoi gian tu ngay 01/01/1900 den ngay day/month/year\n" +
                        "     * \n" +
                        "     * Error:\n" +
                        "     * Cay loi tai dieu kien if (year==1990) thanh if (year>1900)\n" +
                        "     * Khi Year>1900 thi cho ket qua khong mong muon, cau lenh if(year>1900 khong\n" +
                        "     * thuc hien\n" +
                        "     * hàm này lỗi *\n" +
                        "     */\n" +
                        "    public static long CDateToNumber(int day, int month, int year) {\n" +
                        "        int[] ngay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};\n" +
                        "        long s = 0;\n" +
                        "        int i;\n" +
                        "\n" +
                        "        if (year > 1900) {\n" +
                        "            s = s + day;\n" +
                        "            for (i = 0; i < month; i++) \n" +
                        "                s = s + ngay[i];\n" +
                        "        } else if (year > 1900) { // Note: This condition is logically unreachable.\n" +
                        "            s = s + day;\n" +
                        "            s = s + (year - 1900) * 365;\n" +
                        "            for (i = 1900; i <= year; i++) {\n" +
                        "                if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0))\n" +
                        "                 s = s + 1;\n" +
                        "            }\n" +
                        "            for (i = 0; i < month; i++) {\n" +
                        "                s = s + ngay[i];\n" +
                        "                if (((i % 4 == 0) && (i % 100 != 0) || (i % 400 == 0)) && (i == 2)) s = s + 1;\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        return s;\n" +
                        "    }\n" +
                        "\n" +
                        "    // ****************************************************************************\n" +
                        "    /*\n" +
                        "     * input: hour, minute, second\n" +
                        "     * output: Total seconds from 0:0:0 to hour:minute:second\n" +
                        "     * Error:\n" +
                        "     * Cay loi tai dieu kien if (year==1990) thanh if (year>1900)\n" +
                        "     * Khi Year>1900 thi cho ket qua khong mong muon, cau lenh if(year>1900 khong\n" +
                        "     * thuc hien\n" +
                        "     * Hàm CountSecond có một lỗi logic: nó chỉ chấp nhận giờ, phút và giây phải lớn\n" +
                        "     * hơn 0. Điều này có nghĩa là thời gian 00:00:00 sẽ bị loại bỏ, trong khi đây\n" +
                        "     * là một thời gian hợp lệ. Đồng thời, nó cũng loại bỏ các trường hợp mà giờ,\n" +
                        "     * phút hoặc giây bằng 0, như 12:00:00 hay 00:30:45.*\n" +
                        "     */\n" +
                        "    public static long CountSecond(int hour, int minute, int second) {\n" +
                        "        long s ;\n" +
                        "        //Tinh tong so giay tinh tu luc 0:0:0 den hour:minute:second\n" +
                        "        //if (hour>=0 && minute>=0&&second>=0&&hour<=24&&minute<=60&&second<=60) da bo het dau bang = trong cac dieu kien don\n" +
                        "        s = -1;\n" +
                        "        if (hour > 0 && minute > 0 && second > 0 && hour < 24 && minute < 60 && second < 60)\n" +
                        "            s = hour * 3600 + minute * 60 + second;\n" +
                        "        return s;\n" +
                        "    }\n" +
                        "\n" +
                        "    /*\n" +
                        "     * Kiem tra 3 so nhap vao co tao thanh ngay thang hop le khong\n" +
                        "     * input: day, month, year\n" +
                        "     * output: return 1 if valid date; return 0 if invalid date\n" +
                        "     * Error: replace if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 &&\n" +
                        "     * year<=2020) --> 6 loi\n" +
                        "     * by if (day>1 && month>1 && year>1 && day<31 && month<12 && year<2020)\n" +
                        "     */\n" +
                        "    public static int CheckValidDate(int day, int month, int year) {// Tuong doi cham\n" +
                        "        // if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 && year<=2020) -->\n" +
                        "        // 6 loi\n" +
                        "        if (day > 1 && month > 1 && year > 1 && day < 31 && month < 12 && year < 2020) {\n" +
                        "            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)\n" +
                        "                    && (day <= 31))\n" +
                        "                return 1;\n" +
                        "            if ((month == 4 || month == 6 || month == 9 || month == 11) && (day <= 30))\n" +
                        "                return 1;\n" +
                        "            if ((month == 2) && (day < 28))\n" +
                        "                return 1; // day<=28\n" +
                        "            if ((month == 2) && (day == 29) && (year % 4 == 0) && (year % 400 != 0))\n" +
                        "                return 1;\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    // ****************************************************************************\n" +
                        "    /*\n" +
                        "     * Kiem tra 3 so nhap vao co tao thanh thoi gia hop le khong\n" +
                        "     * input: hour, minute, second\n" +
                        "     * output: return 1 if valid time; return 0 if invalid time\n" +
                        "     * Error: replace if ((hour>=0 && hour<=23) && (minute>=0 && minute<=59) &&\n" +
                        "     * (second>=0 && second<=59)) bo tat ca dau bang \"=\" trong dieu kien don\n" +
                        "     * by if (hour>0 && hour<23 && minute>0 && minute<59 && second>0 && second<59)\n" +
                        "     */\n" +
                        "    public static int CheckValidTime(int hour, int minute, int second) {\n" +
                        "        if (hour > 0 && hour < 23 && minute > 0 && minute < 59 && second > 0 && second < 59)\n" +
                        "            return 1;\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "    // ****************************************************************************\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: a,b\n" +
                        "     * output: if a+b < -10, the function should return 1/(a+b)\n" +
                        "     * if a+b = 0 or a+b+1 = 0, the function should return 0;\n" +
                        "     * other wise, the function should return 1/(a+b+1)\n" +
                        "     * Error in function: 2\n" +
                        "     * Number of mistakes: if a+b = 0 or a+b+10 = 0, the function may return\n" +
                        "     * unexpected result;\n" +
                        "     */\n" +
                        "\n" +
                        "    public static int divisionTest(int a, int b) {\n" +
                        "        int x = a + b;\n" +
                        "        int y = x + 10;\n" +
                        "        if (x < 10) {\n" +
                        "            return 1 / x;\n" +
                        "        } else {\n" +
                        "            return 1 / y;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: month, day, year\n" +
                        "     * output: the next date\n" +
                        "     * \n" +
                        "     */\n" +
                        "    public static long NextDate(int year, int month, int day) {\n" +
                        "        int[] ngay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };\n" +
                        "        day = day + 1;\n" +
                        "        while (day >= 365) {\n" +
                        "            year = year + 1;\n" +
                        "            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))\n" +
                        "                day = day - 366;\n" +
                        "            else\n" +
                        "                day = day - 365;\n" +
                        "        }\n" +
                        "\n" +
                        "        while (day > ngay[month]) {\n" +
                        "            day = day - ngay[month];\n" +
                        "            month = month + 1;  \n" +
                        "\n" +
                        "            while (month >= 12) {\n" +
                        "                year = year + 1;\n" +
                        "                month = month % 12;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    // ****************************************************************************\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: x, y\n" +
                        "     * This function is created for loop testing.\n" +
                        "     */\n" +
                        "    public static int simpleWhileTest(int x, int y) {\n" +
                        "        while (x < y) {\n" +
                        "            x += 1;\n" +
                        "        }\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "\n" +
                        "    // ****************************************************************************\n" +
                        "\n" +
                        "    /*\n" +
                        "     * input: m, n\n" +
                        "     * This function is created for loop testing.\n" +
                        "     */\n" +
                        "    public static int Forloop(int m, int n) {\n" +
                        "        int s = 0;\n" +
                        "        if (m > 0 && n > 0) {\n" +
                        "            for (int i = 1; i <= m; i++) {\n" +
                        "                for (int j = 1; j <= n; j++) {\n" +
                        "                    s = s + i + j;\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return s;\n" +
                        "    }\n" +
                        "\n" +
                        "    // ****************************************************************************\n" +
                        "    public static void selectionSort(int[] arr) {\n" +
                        "        int n = arr.length;\n" +
                        "        int i, j, min_idx, temp;\n" +
                        "        // One by one move boundary of unsorted subarray\n" +
                        "        for (i = 0; i < n - 1; i++) {\n" +
                        "            min_idx = i;\n" +
                        "            // Find the minimum element in unsorted array\n" +
                        "            for (j = i + 1; j < n; j++) {\n" +
                        "                if (arr[j] < arr[min_idx]) {\n" +
                        "                    min_idx = j;\n" +
                        "                }\n" +
                        "            }\n" +
                        "            // Swap the found minimum element with the first element\n" +
                        "            temp = arr[min_idx];\n" +
                        "            arr[min_idx] = arr[i];\n" +
                        "            arr[i] = temp;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    // http://pathcrawler-online.com:8080/#\n" +
                        "\n" +
                        "    /*\n" +
                        "     * A classic example from test generation literature\n" +
                        "     * which contains no loops or arrays\n" +
                        "     * but is interesting for its simple example of an oracle.\n" +
                        "     * This implementation does not handle negative inputs correctly\n" +
                        "     */\n" +
                        "\n" +
                        "    /* Determines the type of a triangle given its three edges i,j,k */\n" +
                        "    /* 4 = Not a triangle */\n" +
                        "    /* 3 = Equilateral triangle */\n" +
                        "    /* 2 = Isosceles triangle */\n" +
                        "    /* 1 = Any other triangle */\n" +
                        "    public static int tritype0(int i, int j, int k) {\n" +
                        "        int type_code;\n" +
                        "        if ((i == 0) || (j == 0) || (k == 0)) {\n" +
                        "            type_code = 4;\n" +
                        "        } else {\n" +
                        "            type_code = 0;\n" +
                        "            if (i == j)\n" +
                        "                type_code = type_code + 1;\n" +
                        "            if (i == k)\n" +
                        "                type_code = type_code + 2;\n" +
                        "            if (j == k)\n" +
                        "                type_code = type_code + 3;\n" +
                        "            if (type_code == 0) {\n" +
                        "                if ((i + j <= k) || (j + k <= i) || (i + k <= j)) {\n" +
                        "                    type_code = 4;\n" +
                        "                } else {\n" +
                        "                    type_code = 1;\n" +
                        "                }\n" +
                        "            } else if (type_code > 3) {\n" +
                        "                type_code = 3;\n" +
                        "            } else if ((type_code == 1) && (i + j > k)) {\n" +
                        "                type_code = 2;\n" +
                        "            } else if ((type_code == 2) && (i + k > j)) {\n" +
                        "                type_code = 2;\n" +
                        "            } else if ((type_code == 3) && (j + k > i)) {\n" +
                        "                type_code = 2;\n" +
                        "            } else {\n" +
                        "                type_code = 4;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return type_code;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int leapYear1(int year) {\n" +
                        "        if (year % 4 == 0) {\n" +
                        "            if (year % 100 == 0) {\n" +
                        "                if (year % 400 == 0) {\n" +
                        "                    return 1; // leap year\n" +
                        "                } else {\n" +
                        "                    return 0; // not leap year\n" +
                        "                }\n" +
                        "            } else {\n" +
                        "                return 1;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            return 0;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    // http://pathcrawler-online.com:8080/#\n" +
                        "    // ExampleUnit\n" +
                        "    public static int uninit_var(int[] a, int[] b) {\n" +
                        "        int k = 0;\n" +
                        "        for (int i = 0; i < 2; i++) {\n" +
                        "            if (a[i] == 0)\n" +
                        "                return 0;\n" +
                        "            if (a[i] != a[i + 1])\n" +
                        "                k = 0;\n" +
                        "            else if (k == 2)\n" +
                        "                return 0;\n" +
                        "            while (b[k] != a[i]) {\n" +
                        "                if (k == 2)\n" +
                        "                    return 0;\n" +
                        "                else\n" +
                        "                    k++;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "    // Sach thay Hung\n" +
                        "\n" +
                        "    public static double Average(double[] value, double min, double max, int[] tcnt_vcnt) {\n" +
                        "        double sum = 0;\n" +
                        "        int i = 0;\n" +
                        "        int tcnt = 0, vcnt = 0;\n" +
                        "        while (value[i] != -999 && tcnt < 100) {\n" +
                        "            tcnt++;\n" +
                        "            if (min <= value[i] && value[i] <= max) {\n" +
                        "                sum += value[i];\n" +
                        "                vcnt++;\n" +
                        "            }\n" +
                        "            i++;\n" +
                        "        }\n" +
                        "        tcnt_vcnt[0] = tcnt;\n" +
                        "        tcnt_vcnt[1] = vcnt;\n" +
                        "        if (vcnt > 0)\n" +
                        "            return sum / vcnt;\n" +
                        "        return -999;\n" +
                        "    }\n" +
                        "\n" +
                        "    /****************************************************************************************/\n" +
                        "    public static int multiConditionTest(int x) {\n" +
                        "        if (x < 0) {\n" +
                        "            if (x > -10) {\n" +
                        "                if (x > -5) {\n" +
                        "                    return 1;\n" +
                        "                } else if (x <= -5 && x >= -8) {\n" +
                        "                    return 2;\n" +
                        "                } else {\n" +
                        "                    return 3;\n" +
                        "                }\n" +
                        "            } else {\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "        } else if (x > 0 && x < 100) {\n" +
                        "            if (x < 50) {\n" +
                        "                if (x < 20) {\n" +
                        "                    return 4;\n" +
                        "                } else {\n" +
                        "                    return 5;\n" +
                        "                }\n" +
                        "            } else if (x >= 50 && x < 80) {\n" +
                        "                if (x == 60) {\n" +
                        "                    return 6;\n" +
                        "                } else {\n" +
                        "                    return 7;\n" +
                        "                }\n" +
                        "            } else {\n" +
                        "                return 8;\n" +
                        "            }\n" +
                        "        } else if (x == 101) {\n" +
                        "            return 9;\n" +
                        "        } else {\n" +
                        "            return x;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /****************************************************************************************/\n" +
                        "\n" +
                        "    public static float distanceTest(float x) {\n" +
                        "        if (x > 1) {\n" +
                        "            if (x < 1.001) {\n" +
                        "                if (x < 1.00001) {\n" +
                        "                    return 1;\n" +
                        "                } else {\n" +
                        "                    return 2;\n" +
                        "                }\n" +
                        "            } else {\n" +
                        "                return 3;\n" +
                        "            }\n" +
                        "        } else if (x <= 1 && x >= 0.999) {\n" +
                        "            return 4;\n" +
                        "        }\n" +
                        "        return 5;\n" +
                        "    }\n" +
                        "\n" +
                        "    /****************************************************************************************/\n" +
                        "    public static double smallIntervalTest(double x) {\n" +
                        "        if (x > 1.0) {\n" +
                        "            if (x > 2.00001 && x < 2.000015) {\n" +
                        "                return 1;\n" +
                        "            } else if (x <= 2.00001 && x >= 1.9999) {\n" +
                        "                return 2;\n" +
                        "            } else {\n" +
                        "                return 3;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return 4;\n" +
                        "    }\n" +
                        "\n" +
                        "    /****************************************************************************************/\n" +
                        "\n" +
                        "    public static int mmin(int a, int b) {\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int gcd3(int a, int b) {\n" +
                        "        int temp;\n" +
                        "\n" +
                        "        while (b > 0) {\n" +
                        "            temp = a % b;\n" +
                        "            a = b;\n" +
                        "            b = temp;\n" +
                        "        }\n" +
                        "\n" +
                        "        return a;\n" +
                        "    }\n" +
                        "    // D:\\OneDrive - Cao Dang Su Pham Trung\n" +
                        "    // Uong\\NCS\\ava\\data-test\\tsdv\\Sample_for_R1_4\n" +
                        "\n" +
                        "    // Da them 4 loi\n" +
                        "    // Loi tai age=4,14,15, distance=10\n" +
                        "\n" +
                        "    public static int getFare1(int age, int distance) {\n" +
                        "        // Da them 4 loi\n" +
                        "        // Loi tai age=4,14,15, distance=10\n" +
                        "        int fare = 0;\n" +
                        "        if (age > 4 && age < 14) {// if(age >= 4 && age <= 14), 2 loi\n" +
                        "            if (distance >= 10) {// if(distance > 10), 1 loi\n" +
                        "                fare = 130;\n" +
                        "            } else {\n" +
                        "                fare = 100;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if (age > 15) {// (age >= 15) , 1 loi\n" +
                        "            if (distance < 10 && age >= 60) {\n" +
                        "                fare = 160;\n" +
                        "            } else if (distance > 10 && age < 60) {\n" +
                        "                fare = 250;\n" +
                        "            } else {\n" +
                        "                fare = 200;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return fare;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static short SimpleCondThanComplexCond(short a, short b, short c) {\n" +
                        "        if ((a > 0) && (c > 3) && (b > -10)) {\n" +
                        "            if (b > 0)\n" +
                        "                if ((a > b) && (c > 5))\n" +
                        "                    if (a > c)\n" +
                        "                        if ((a > 10) && (b > 10) && (c > 10))\n" +
                        "                            if ((a <= 17) && (c <= 15) && (b < 20))\n" +
                        "                                return 0;\n" +
                        "        }\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static long MoreComplexCond(long a, long b, long c, long d, long e) {\n" +
                        "        if ((a > 0) && (c > 3) && (a + b > -10) && (a + b + c > 0) & (a + b - e < 0))\n" +
                        "            if ((b > 2) && (b > c))\n" +
                        "                if (a + b > d)\n" +
                        "                    if (c + d - e < 0)\n" +
                        "                        if (a > b)\n" +
                        "                            if (a > c)\n" +
                        "                                if ((a - b > 2) && (4 * b - 5 * c == 0) && (a - c == 8) && (2 * c > b))\n" +
                        "                                    if (a + d == e)\n" +
                        "                                        if ((2 * b > a) && (b + c > a))\n" +
                        "                                            return 0;\n" +
                        "        return 1;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static float multilParaTypeTest(short a, int b, float c) {\n" +
                        "        float x = 0;\n" +
                        "        if ((a < 10) && (b < 15) && (c < 5))\n" +
                        "            x = 1;\n" +
                        "        else if ((a < 20) && (b < 25) && (c < 10))\n" +
                        "            x = 2;\n" +
                        "        else if ((a < 30) && (b < 35) && (c < 20))\n" +
                        "            x = 3;\n" +
                        "        else if ((a < 40) && (b < 45) && (c < 30))\n" +
                        "            x = 4;\n" +
                        "        else if ((a < 50) && (b < 55) && (c < 40))\n" +
                        "            x = 5;\n" +
                        "        else\n" +
                        "            x = 6;\n" +
                        "        return x;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static double average(double[] value, double min, double max, int[] tcnt_vcnt) {\n" +
                        "        double sum = 0;\n" +
                        "        int i = 0;\n" +
                        "        int tcnt = 0, vcnt = 0;\n" +
                        "        while (value[i] != -999 && tcnt < 100) {\n" +
                        "            tcnt++;\n" +
                        "            if (min <= value[i] && value[i] <= max) {\n" +
                        "                sum += value[i];\n" +
                        "                vcnt++;\n" +
                        "            }\n" +
                        "            i++;\n" +
                        "        }\n" +
                        "        tcnt_vcnt[0] = tcnt;\n" +
                        "        tcnt_vcnt[1] = vcnt;\n" +
                        "        if (vcnt > 0)\n" +
                        "            return sum / vcnt;\n" +
                        "        return -999;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int twoWhileloop(int m, int n) {\n" +
                        "        int s = 0;\n" +
                        "        int i, j;\n" +
                        "        if (m > 0 && n > 0) {\n" +
                        "            i = 1;\n" +
                        "            j = 1;\n" +
                        "            while (i <= m) {\n" +
                        "                while (j <= n) {\n" +
                        "                    s = s + i + j;\n" +
                        "                    j = j + 1;\n" +
                        "                }\n" +
                        "                i = i + 1;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return s;\n" +
                        "    }\n" +
                        "}";

        generateBoundaryTests(source);
    }

    public static void generateBoundaryTests(String source) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration node) {
                generateTestCases(node);
                return super.visit(node);
            }
        });
    }

    private static void generateTestCases(MethodDeclaration method) {
        List<?> parameters = method.parameters();
        Set<String> uniqueTestCases = new HashSet<>();

        for (Object parameter : parameters) {
            SingleVariableDeclaration svd = (SingleVariableDeclaration) parameter;
            String type = svd.getType().toString();

            // Phân tích các điều kiện trong thân phương thức để lấy các giá trị biên
            List<Object> boundaryValues = extractBoundaryValues(method.getBody(), svd.getName().getIdentifier(), type);

            switch (type) {
                case "int":
                    generateIntBoundaryTestCases(method, svd, boundaryValues, uniqueTestCases);
                    break;
                case "double":
                    generateDoubleBoundaryTestCases(method, svd, boundaryValues, uniqueTestCases);
                    break;
                case "short":
                    generateShortBoundaryTestCases(method, svd, boundaryValues, uniqueTestCases);
                    break;
                case "long":
                    generateLongBoundaryTestCases(method, svd, boundaryValues, uniqueTestCases);
                    break;
                case "float":
                    generateFloatBoundaryTestCases(method, svd, boundaryValues, uniqueTestCases);
                    break;
                case "String":
                    generateStringBoundaryTestCases(method, svd, uniqueTestCases);
                    break;
                // Add other types as needed
                default:
                    System.out.println("Unsupported type: " + type);
            }
        }
        System.out.println("Unique test cases for method " + method.getName() + ": " + uniqueTestCases.size());
        // Write unique test cases to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BoundaryTestCases_" + method.getName() + ".txt"))) {
            for (String testCase : uniqueTestCases) {
                writer.write(testCase);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Object> extractBoundaryValues(Block body, String variableName, String type) {
        List<Object> boundaryValues = new ArrayList<>();
        body.accept(new ASTVisitor() {
            @Override
            public boolean visit(InfixExpression node) {
                if (node.getLeftOperand() instanceof SimpleName &&
                        ((SimpleName) node.getLeftOperand()).getIdentifier().equals(variableName)) {
                    if (node.getRightOperand() instanceof NumberLiteral) {
                        String operator = node.getOperator().toString();
                        addBoundaryValue(boundaryValues, operator, ((NumberLiteral) node.getRightOperand()).getToken(), type);
                    }
                } else if (node.getRightOperand() instanceof SimpleName &&
                        ((SimpleName) node.getRightOperand()).getIdentifier().equals(variableName)) {
                    if (node.getLeftOperand() instanceof NumberLiteral) {
                        String operator = node.getOperator().toString();
                        addBoundaryValue(boundaryValues, operator, ((NumberLiteral) node.getLeftOperand()).getToken(), type);
                    }
                }
                return super.visit(node);
            }
        });
        return boundaryValues;
    }

    private static void addBoundaryValue(List<Object> boundaryValues, String operator, String token, String type) {
        switch (type) {
            case "int":
                int intValue = Integer.parseInt(token);
                switch (operator) {
                    case "<":
                        boundaryValues.add(intValue - 1);
                        boundaryValues.add(intValue);
                        break;
                    case "<=":
                        boundaryValues.add(intValue);
                        boundaryValues.add(intValue + 1);
                        break;
                    case ">":
                        boundaryValues.add(intValue + 1);
                        boundaryValues.add(intValue);
                        break;
                    case ">=":
                        boundaryValues.add(intValue);
                        boundaryValues.add(intValue - 1);
                        break;
                }
                break;
            case "double":
                double doubleValue = Double.parseDouble(token);
                switch (operator) {
                    case "<":
                        boundaryValues.add(doubleValue - 0.01);
                        boundaryValues.add(doubleValue);
                        break;
                    case "<=":
                        boundaryValues.add(doubleValue);
                        boundaryValues.add(doubleValue + 0.01);
                        break;
                    case ">":
                        boundaryValues.add(doubleValue + 0.01);
                        boundaryValues.add(doubleValue);
                        break;
                    case ">=":
                        boundaryValues.add(doubleValue);
                        boundaryValues.add(doubleValue - 0.01);
                        break;
                }
                break;
            case "short":
                short shortValue = Short.parseShort(token);
                switch (operator) {
                    case "<":
                        boundaryValues.add((short) (shortValue - 1));
                        boundaryValues.add(shortValue);
                        break;
                    case "<=":
                        boundaryValues.add(shortValue);
                        boundaryValues.add((short) (shortValue + 1));
                        break;
                    case ">":
                        boundaryValues.add((short) (shortValue + 1));
                        boundaryValues.add(shortValue);
                        break;
                    case ">=":
                        boundaryValues.add(shortValue);
                        boundaryValues.add((short) (shortValue - 1));
                        break;
                }
                break;
            case "long":
                long longValue = Long.parseLong(token);
                switch (operator) {
                    case "<":
                        boundaryValues.add(longValue - 1);
                        boundaryValues.add(longValue);
                        break;
                    case "<=":
                        boundaryValues.add(longValue);
                        boundaryValues.add(longValue + 1);
                        break;
                    case ">":
                        boundaryValues.add(longValue + 1);
                        boundaryValues.add(longValue);
                        break;
                    case ">=":
                        boundaryValues.add(longValue);
                        boundaryValues.add(longValue - 1);
                        break;
                }
                break;
            case "float":
                float floatValue = Float.parseFloat(token);
                switch (operator) {
                    case "<":
                        boundaryValues.add(floatValue - 0.01f);
                        boundaryValues.add(floatValue);
                        break;
                    case "<=":
                        boundaryValues.add(floatValue);
                        boundaryValues.add(floatValue + 0.01f);
                        break;
                    case ">":
                        boundaryValues.add(floatValue + 0.01f);
                        boundaryValues.add(floatValue);
                        break;
                    case ">=":
                        boundaryValues.add(floatValue);
                        boundaryValues.add(floatValue - 0.01f);
                        break;
                }
                break;
        }
    }

    private static void generateIntBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> boundaryValues, Set<String> uniqueTestCases) {
        for (Object val : boundaryValues) {
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), val.toString());
            uniqueTestCases.add(testCase);
        }
    }

    private static void generateDoubleBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> boundaryValues, Set<String> uniqueTestCases) {
        for (Object val : boundaryValues) {
            String valueStr = val.toString().replace("E", "e"); // handle scientific notation
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), valueStr);
            uniqueTestCases.add(testCase);
        }
    }

    private static void generateShortBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> boundaryValues, Set<String> uniqueTestCases) {
        for (Object val : boundaryValues) {
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), val.toString());
            uniqueTestCases.add(testCase);
        }
    }

    private static void generateLongBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> boundaryValues, Set<String> uniqueTestCases) {
        for (Object val : boundaryValues) {
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), val.toString());
            uniqueTestCases.add(testCase);
        }
    }

    private static void generateFloatBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> boundaryValues, Set<String> uniqueTestCases) {
        for (Object val : boundaryValues) {
            String valueStr = val.toString().replace("E", "e"); // handle scientific notation
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), valueStr);
            uniqueTestCases.add(testCase);
        }
    }

    private static void generateStringBoundaryTestCases(MethodDeclaration method, SingleVariableDeclaration svd, Set<String> uniqueTestCases) {
        String[] values = {"", "a", "boundaryTestString"};
        for (String value : values) {
            String testCase = createTestCaseString(method.getName().toString(), svd.getName().getIdentifier(), "\"" + value + "\"");
            uniqueTestCases.add(testCase);
        }
    }

    private static String createTestCaseString(String methodName, String paramName, String value) {
        return methodName + "(" + paramName + " = " + value + ")";
    }
}
