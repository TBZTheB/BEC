package fileCloned;

public class Test4 {

    public void test(int x) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$34#statement$int[] numbers={1,2,3,4,5,2,9,7,5};");
	int[] numbers={1,2,3,4,5,2,9,7,5};
	Mark.print("line-in-function$3#offset$87#statement$int count=0;");
	int count=0;
	Mark.print("line-in-function$4#offset$110#surrounding-control-block$forEach");
	Mark.print("line-in-function$4#offset$115#statement$int number : numbers");
	for(int number : numbers) {
	Mark.print("statement${#line-of-block-in-function$4");
	Mark.print("line-in-function$5#offset$151#surrounding-control-block$if");
	if ((Mark.print("line-in-function$5#offset$155#statement$number >= x") && number >= x)) {
	Mark.print("statement${#line-of-block-in-function$5");
	Mark.print("line-in-function$6#offset$186#statement$count++;");
	count++;
	Mark.print("line-in-function$7#offset$211#statement$System.out.println(number);");
	System.out.println(number);
	Mark.print("statement$}#line-of-block-in-function$5");
	}
	Mark.print("statement$}#line-of-block-in-function$4");
	Mark.print("line-in-function$4#offset$115#statement$int number : numbers");
	}
	Mark.print("line-in-function$10#offset$271#statement$System.out.println(count);");
	System.out.println(count);
	Mark.print("statement$}#line-of-block-in-function$1");
}
}
