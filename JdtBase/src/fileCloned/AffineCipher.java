package fileCloned;

class AffineCipher {

    // Key values of a and b
    static int a = 17;
    static int b = 20;

    static String encryptMessage(char[] msg) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$3#offset$91#statement$String cipher=\"\";");
	String cipher="";
	Mark.print("line-in-function$4#offset$119#surrounding-control-block$for");
	Mark.print("line-in-function$4#offset$124#statement$int i=0");
	for(int i=0; (Mark.print("line-in-function$4#offset$135#statement$i < msg.length") && i < msg.length); i++) {
	Mark.print("statement${#line-of-block-in-function$4");
	Mark.print("line-in-function$9#offset$400#surrounding-control-block$if");
	if ((Mark.print("line-in-function$9#offset$404#statement$msg[i] != ' '") && msg[i] != ' ')) {
	Mark.print("statement${#line-of-block-in-function$9");
	Mark.print("line-in-function$10#offset$437#statement$cipher=cipher + (char)((((a * (msg[i] - 'A')) + b) % 26) + 'A');");
	cipher=cipher + (char)((((a * (msg[i] - 'A')) + b) % 26) + 'A');
	Mark.print("statement$}#line-of-block-in-function$9");
	} else {
	Mark.print("statement${#line-of-block-in-function$11");
	Mark.print("line-in-function$12#offset$580#statement$cipher+=msg[i];");
	cipher+=msg[i];
	Mark.print("statement$}#line-of-block-in-function$11");
	}
	Mark.print("statement$}#line-of-block-in-function$4");
	Mark.print("line-in-function$4#offset$151#statement$i++");
	}
	Mark.print("line-in-function$15#offset$630#statement$return cipher;");
	Mark.print("statement$}#line-of-block-in-function$1");
	return cipher;
	}

    static String decryptCipher(String cipher) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$53#statement$String msg=\"\";");
	String msg="";
	Mark.print("line-in-function$3#offset$78#statement$int a_inv=0;");
	int a_inv=0;
	Mark.print("line-in-function$4#offset$101#statement$int flag=0;");
	int flag=0;
	Mark.print("line-in-function$8#offset$225#surrounding-control-block$for");
	Mark.print("line-in-function$8#offset$230#statement$int i=0");
	for(int i=0; (Mark.print("line-in-function$8#offset$241#statement$i < 26") && i < 26); i++) {
	Mark.print("statement${#line-of-block-in-function$8");
	Mark.print("line-in-function$9#offset$268#statement$flag=(a * i) % 26;");
	flag=(a * i) % 26;
	Mark.print("line-in-function$13#offset$403#surrounding-control-block$if");
	if ((Mark.print("line-in-function$13#offset$407#statement$flag == 1") && flag == 1)) {
	Mark.print("statement${#line-of-block-in-function$13");
	Mark.print("line-in-function$14#offset$436#statement$a_inv=i;");
	a_inv=i;
	Mark.print("statement$}#line-of-block-in-function$13");
	}
	Mark.print("statement$}#line-of-block-in-function$8");
	Mark.print("line-in-function$8#offset$249#statement$i++");
	}
	Mark.print("line-in-function$17#offset$479#surrounding-control-block$for");
	Mark.print("line-in-function$17#offset$484#statement$int i=0");
	for(int i=0; (Mark.print("line-in-function$17#offset$495#statement$i < cipher.length()") && i < cipher.length()); i++) {
	Mark.print("statement${#line-of-block-in-function$17");
	Mark.print("line-in-function$21#offset$727#surrounding-control-block$if");
	if ((Mark.print("line-in-function$21#offset$731#statement$cipher.charAt(i) != ' '") && cipher.charAt(i) != ' ')) {
	Mark.print("statement${#line-of-block-in-function$21");
	Mark.print("line-in-function$22#offset$774#statement$msg=msg + (char)(((a_inv * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');");
	msg=msg + (char)(((a_inv * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
	Mark.print("statement$}#line-of-block-in-function$21");
	} else {
	Mark.print("statement${#line-of-block-in-function$23");
	Mark.print("line-in-function$24#offset$925#statement$msg+=cipher.charAt(i);");
	msg+=cipher.charAt(i);
	Mark.print("statement$}#line-of-block-in-function$23");
	}
	Mark.print("statement$}#line-of-block-in-function$17");
	Mark.print("line-in-function$17#offset$516#statement$i++");
	}
	Mark.print("line-in-function$28#offset$983#statement$return msg;");
	Mark.print("statement$}#line-of-block-in-function$1");
	return msg;
	}

    // Driver code
    public static void main(String[] args) {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$49#statement$String msg=\"AFFINE CIPHER\";");
	String msg="AFFINE CIPHER";
	Mark.print("line-in-function$5#offset$127#statement$String cipherText=encryptMessage(msg.toCharArray());");
	String cipherText=encryptMessage(msg.toCharArray());
	Mark.print("line-in-function$6#offset$190#statement$System.out.println(\"Encrypted Message is : \" + cipherText);");
	System.out.println("Encrypted Message is : " + cipherText);
	Mark.print("line-in-function$9#offset$298#statement$System.out.println(\"Decrypted Message is: \" + decryptCipher(cipherText));");
	System.out.println("Decrypted Message is: " + decryptCipher(cipherText));
	Mark.print("statement$}#line-of-block-in-function$1");
	}
}
