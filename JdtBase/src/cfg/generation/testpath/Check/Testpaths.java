package cfg.generation.testpath.Check;

import java.util.ArrayList;

public class Testpaths<N extends ITestpath> extends ArrayList<N> implements ITestpaths {
    /**
     *
     */

//    @Override
//    public ITestpath getLongestTestpath() {
//        ITestpath longestTp = get(0);
//        int lengthTp = longestTp.getRealSize();
//        for (int i = 1; i < size(); i++)
//            if (get(i).getRealSize() > lengthTp) {
//                lengthTp = get(i).getRealSize();
//                longestTp = get(i);
//            }
//        return longestTp;
//    }

    @Override
    public boolean equals(Object o) {
        Testpaths<ITestpath> tp = (Testpaths<ITestpath>) o;
        if (size() == tp.size()) {
            for (int i = 0; i < size(); i++)
                if (!get(i).equals(tp.get(i)))
                    return false;
            return true;
        } else
            return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof ITestpaths) {
            Testpaths<ITestpath> tp = (Testpaths<ITestpath>) o;
            if (size() >= tp.size()) {
                for (ITestpath item : tp)
                    if (!this.contains(item))
                        return false;
                return true;
            } else
                return false;

        } else if (o instanceof ITestpath) {
            AbstractTestpath tp = (AbstractTestpath) o;
            for (ITestpath item : this)
                if (item.equals(tp))
                    return true;
            return false;
        } else
            return false;
    }
//
//    @Override
//    public Testpaths cast() {
//        return this;
//    }
}
