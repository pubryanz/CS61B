package randomizedtest;

import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = randomizedtest.StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                a.addLast(randVal);
                b.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int aSize = a.size();
                int bSize = b.size();
                assertEquals(aSize, bSize);
                System.out.println("size: " + aSize);
            } else if (operationNumber == 2 && a.size() > 0 && b.size() > 0) {
                // getLast
                int aLast = a.getLast();
                int bLast = b.getLast();
                assertEquals(aLast, bLast);
                System.out.println("getLast(" + aLast + ")");
            } else if (operationNumber == 3 && a.size() > 0 && b.size() > 0) {
                // removeLast
                int ARLast = a.removeLast();
                int BRLast = b.removeLast();
                assertEquals(ARLast, BRLast);
                System.out.println("removeLast(" + ARLast + ")");
            }
        }
    }
}
