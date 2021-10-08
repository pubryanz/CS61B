public class MaxArray {
    public static int max(int[] m) {
        for (int i = 0; i < m.length - 1; i++) {
            if (m[i] > m[i + 1]) {
                int temp = m[i + 1];
                m[i + 1] = m[i];
                m[i] = temp;
            }
        }
        return m[m.length - 1];
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
