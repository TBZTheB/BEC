package fileCloned;


public class IIRFilter {

    private final int order;
    private final double[] coeffsA;
    private final double[] coeffsB;
    private final double[] historyX;
    private final double[] historyY;

    public IIRFilter(int order) throws IllegalArgumentException {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$70#surrounding-control-block$if");
	if ((Mark.print("line-in-function$2#offset$74#statement$order < 1") && order < 1)) {
	Mark.print("statement${#line-of-block-in-function$2");
	Mark.print("line-in-function$3#offset$99#statement$throw new IllegalArgumentException(\"order must be greater than zero\");");
	Mark.print("statement$}#line-of-block-in-function$2");
	throw new IllegalArgumentException("order must be greater than zero");
	}
	Mark.print("line-in-function$5#offset$188#statement$this.order=order;");
	this.order=order;
	Mark.print("line-in-function$6#offset$216#statement$coeffsA=new double[order + 1];");
	coeffsA=new double[order + 1];
	Mark.print("line-in-function$7#offset$257#statement$coeffsB=new double[order + 1];");
	coeffsB=new double[order + 1];
	Mark.print("line-in-function$10#offset$324#statement$coeffsA[0]=1.0;");
	coeffsA[0]=1.0;
	Mark.print("line-in-function$11#offset$350#statement$coeffsB[0]=1.0;");
	coeffsB[0]=1.0;
	Mark.print("line-in-function$13#offset$377#statement$historyX=new double[order];");
	historyX=new double[order];
	Mark.print("line-in-function$14#offset$415#statement$historyY=new double[order];");
	historyY=new double[order];
	Mark.print("statement$}#line-of-block-in-function$1");
	}


    public void setCoeffs(double[] aCoeffs, double[] bCoeffs) throws IllegalArgumentException {
	Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
	Mark.print("line-in-function$2#offset$100#surrounding-control-block$if");
	if ((Mark.print("line-in-function$2#offset$104#statement$aCoeffs.length != order") && aCoeffs.length != order)) {
	Mark.print("statement${#line-of-block-in-function$2");
	Mark.print("line-in-function$3#offset$143#statement$throw new IllegalArgumentException(\"aCoeffs must be of size \" + order + \", got \"+ aCoeffs.length);");
	Mark.print("statement$}#line-of-block-in-function$2");
	throw new IllegalArgumentException("aCoeffs must be of size " + order + ", got "+ aCoeffs.length);
	}
	Mark.print("line-in-function$6#offset$262#surrounding-control-block$if");
	if ((Mark.print("line-in-function$6#offset$266#statement$aCoeffs[0] == 0.0") && aCoeffs[0] == 0.0)) {
	Mark.print("statement${#line-of-block-in-function$6");
	Mark.print("line-in-function$7#offset$299#statement$throw new IllegalArgumentException(\"aCoeffs.get(0) must not be zero\");");
	Mark.print("statement$}#line-of-block-in-function$6");
	throw new IllegalArgumentException("aCoeffs.get(0) must not be zero");
	}
	Mark.print("line-in-function$10#offset$389#surrounding-control-block$if");
	if ((Mark.print("line-in-function$10#offset$393#statement$bCoeffs.length != order") && bCoeffs.length != order)) {
	Mark.print("statement${#line-of-block-in-function$10");
	Mark.print("line-in-function$11#offset$432#statement$throw new IllegalArgumentException(\"bCoeffs must be of size \" + order + \", got \"+ bCoeffs.length);");
	Mark.print("statement$}#line-of-block-in-function$10");
	throw new IllegalArgumentException("bCoeffs must be of size " + order + ", got "+ bCoeffs.length);
	}
	Mark.print("line-in-function$14#offset$551#surrounding-control-block$for");
	Mark.print("line-in-function$14#offset$556#statement$int i=0");
	for(int i=0; (Mark.print("line-in-function$14#offset$567#statement$i <= order") && i <= order); i++) {
	Mark.print("statement${#line-of-block-in-function$14");
	Mark.print("line-in-function$15#offset$598#statement$coeffsA[i]=aCoeffs[i];");
	coeffsA[i]=aCoeffs[i];
	Mark.print("line-in-function$16#offset$635#statement$coeffsB[i]=bCoeffs[i];");
	coeffsB[i]=bCoeffs[i];
	Mark.print("statement$}#line-of-block-in-function$14");
	Mark.print("line-in-function$14#offset$579#statement$i++");
	}
	Mark.print("statement$}#line-of-block-in-function$1");
	}

    /**
     * Process a single sample
     *
     * @param sample the sample to process
     * @return the processed sample
     */
    public double process(double sample) {
	Mark.print("statement${#line-of-block-in-function$7#opening-function$true");
	Mark.print("line-in-function$8#offset$180#statement$double result=0.0;");
	double result=0.0;
	Mark.print("line-in-function$11#offset$229#surrounding-control-block$for");
	Mark.print("line-in-function$11#offset$234#statement$int i=1");
	for(int i=1; (Mark.print("line-in-function$11#offset$245#statement$i <= order") && i <= order); i++) {
	Mark.print("statement${#line-of-block-in-function$11");
	Mark.print("line-in-function$12#offset$276#statement$result+=(coeffsB[i] * historyX[i - 1] - coeffsA[i] * historyY[i - 1]);");
	result+=(coeffsB[i] * historyX[i - 1] - coeffsA[i] * historyY[i - 1]);
	Mark.print("statement$}#line-of-block-in-function$11");
	Mark.print("line-in-function$11#offset$257#statement$i++");
	}
	Mark.print("line-in-function$14#offset$367#statement$result=(result + coeffsB[0] * sample) / coeffsA[0];");
	result=(result + coeffsB[0] * sample) / coeffsA[0];
	Mark.print("line-in-function$17#offset$450#surrounding-control-block$for");
	Mark.print("line-in-function$17#offset$455#statement$int i=order - 1");
	for(int i=order - 1; (Mark.print("line-in-function$17#offset$474#statement$i > 0") && i > 0); i--) {
	Mark.print("statement${#line-of-block-in-function$17");
	Mark.print("line-in-function$18#offset$500#statement$historyX[i]=historyX[i - 1];");
	historyX[i]=historyX[i - 1];
	Mark.print("line-in-function$19#offset$543#statement$historyY[i]=historyY[i - 1];");
	historyY[i]=historyY[i - 1];
	Mark.print("statement$}#line-of-block-in-function$17");
	Mark.print("line-in-function$17#offset$481#statement$i--");
	}
	Mark.print("line-in-function$22#offset$593#statement$historyX[0]=sample;");
	historyX[0]=sample;
	Mark.print("line-in-function$23#offset$623#statement$historyY[0]=result;");
	historyY[0]=result;
	Mark.print("line-in-function$25#offset$654#statement$return result;");
	Mark.print("statement$}#line-of-block-in-function$7");
	return result;
	}
}
