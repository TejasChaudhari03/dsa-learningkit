package JavaPrograms;
public class LearnPatterns {
    public static void main(String[] args) {

        System.out.println("Learning Design Patterns");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.println("i = " + i + " --- j= " + j);
            }
        }

        int n = 10;
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n - i; j++) {
                row = row + "*";
            }
            System.out.println(row);
        }

        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < i + 1; j++) {
                row = row + j;
            }
            System.out.println(row);
        }

        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < n - (i + 1); j++) {
                row = row + " ";
            }
            for (int k = 0; k < i + 1; k++) {
                row = row + "*";
            }
            System.out.println(row);
        }

        int toggle = 1;
        for (int i = 0; i < n; i++) {
            String row = "";
            for (int j = 0; j < i + 1; j++) {
                row = row + toggle;
                if (toggle == 1) toggle = 0;
                else toggle = 1;
            }

            System.out.println(row);
        }
    }
}
