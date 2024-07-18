package fileCloned;

public class Test5 {
    public static void main(String[] args) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$49#statement$int a=1, sum=0;");
	int a=1, sum=0;
	Mark.print("line-in-function$3#offset$77#surrounding-control-block$do");
	do {
	Mark.print("statement${#line-of-block-in-function$3");
	Mark.print("line-in-function$4#offset$94#statement$sum+=a;");
	sum+=a;
	Mark.print("line-in-function$5#offset$116#statement$a++;");
	a++;
	Mark.print("statement$}#line-of-block-in-function$3");
	} while ((Mark.print("line-in-function$6#offset$138#statement$a <= 5") && a <= 5));
	Mark.print("line-in-function$7#offset$155#statement$System.out.println(\"Sum of 1 to 5  is \" + sum);");
	System.out.println("Sum of 1 to 5  is " + sum);
	Mark.print("statement$}#line-of-block-in-function$1");
}
}
