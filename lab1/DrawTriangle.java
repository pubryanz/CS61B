public class DrawTriangle {
    public static void main(String[] args) {
        for (int row = 1; row <= 5; row += 1) {
            for (int i = 1; i <= row; i += 1) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
