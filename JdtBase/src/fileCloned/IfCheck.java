package fileCloned;

public class IfCheck {
    public static String kiemTraLoaiTamGiac(int a, int b, int c) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$64#surrounding-control-block$if");
	if ((Mark.print("line-in-function$2#offset$68#statement$a <= 0 || b <= 0 || c <= 0#is_condition$false") && a <= 0 || b <= 0 || c <= 0 && Mark.print("line-in-function$2#offset$68#statement$a <= 0 || b <= 0 || c <= 0#is_condition$true"))) {
	Mark.print("statement${#line-of-block-in-function$2");
	Mark.print("line-in-function$3#offset$110#statement$return \"Không phải tam giác\";");
	Mark.print("statement$}#line-of-block-in-function$2");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Không phải tam giác";
	}
	Mark.print("line-in-function$5#offset$158#surrounding-control-block$if");
	if ((Mark.print("line-in-function$5#offset$162#statement$a + b <= c || a + c <= b || b + c <= a#is_condition$false") && a + b <= c || a + c <= b || b + c <= a && Mark.print("line-in-function$5#offset$162#statement$a + b <= c || a + c <= b || b + c <= a#is_condition$true"))) {
	Mark.print("statement${#line-of-block-in-function$5");
	Mark.print("line-in-function$6#offset$216#statement$return \"Không phải tam giác\";");
	Mark.print("statement$}#line-of-block-in-function$5");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Không phải tam giác";
	}
	Mark.print("line-in-function$8#offset$264#surrounding-control-block$if");
	if ((Mark.print("line-in-function$8#offset$268#statement$a == b && b == c#is_condition$false") && a == b && b == c && Mark.print("line-in-function$8#offset$268#statement$a == b && b == c#is_condition$true"))) {
	Mark.print("statement${#line-of-block-in-function$8");
	Mark.print("line-in-function$9#offset$300#statement$return \"Tam giác đều\";");
	Mark.print("statement$}#line-of-block-in-function$8");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Tam giác đều";
	}
	Mark.print("line-in-function$11#offset$341#surrounding-control-block$if");
	if ((Mark.print("line-in-function$11#offset$345#statement$a == b || a == c || b == c#is_condition$false") && a == b || a == c || b == c && Mark.print("line-in-function$11#offset$345#statement$a == b || a == c || b == c#is_condition$true"))) {
	Mark.print("statement${#line-of-block-in-function$11");
	Mark.print("line-in-function$12#offset$387#statement$return \"Tam giác cân\";");
	Mark.print("statement$}#line-of-block-in-function$11");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Tam giác cân";
	}
	Mark.print("line-in-function$14#offset$428#surrounding-control-block$if");
	if ((Mark.print("line-in-function$14#offset$432#statement$a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a#is_condition$false") && a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a && Mark.print("line-in-function$14#offset$432#statement$a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a#is_condition$true"))) {
	Mark.print("statement${#line-of-block-in-function$16");
	Mark.print("line-in-function$17#offset$554#statement$return \"Tam giác vuông\";");
	Mark.print("statement$}#line-of-block-in-function$16");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Tam giác vuông";
	}
	Mark.print("line-in-function$19#offset$597#statement$return \"Tam giác thường\";");
	Mark.print("statement$}#line-of-block-in-function$1");
	return "Tam giác thường";
	}

	public static void main(String[] args) {
		kiemTraLoaiTamGiac(8, 8, 10);
	}
}
