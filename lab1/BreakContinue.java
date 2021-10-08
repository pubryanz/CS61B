public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > 0) {
                int temp = 0;
                for (int j = 0; j <= n; j++) {
                    if (i + j >= a.length) {
                        break;
                    } else {
                        temp = temp + a[i + j];
                    }
                }
                a[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, -1, -1, 10, 5, -1};
        int n = 2;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
