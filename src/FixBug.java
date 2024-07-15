import java.text.DecimalFormat;

public class FixBug {
    public static String test(int a, int b){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format((float) a / b);
    }
    public static void main(String[] args) {
        for(int i = 1; i <=3; i++){
            System.out.println(test(7, 8));
        }
    }
}
