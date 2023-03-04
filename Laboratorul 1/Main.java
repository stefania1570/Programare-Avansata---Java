public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World\n");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        for (int i = 0; i < languages.length; i++) {
            System.out.print(languages[i] + " ");
        }
        System.out.print("\n");
        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n + "\n");
        n = n * 3;
        n = n + Integer.parseInt("10101", 2);
        n = n + Integer.parseInt("FF", 16);
        n = n * 6;
        System.out.println(n + "\n");

        //5.Compute the sum of the digits in the result obtained in the previous step. This is the new result. While the new result has more than one digit, continue to sum the digits of the result.
        int s = 0;
        int res = 0;
        do {
            while(n!=0) {
                s = s + n % 10;
                n = n / 10;
            }

            n = s;
            res = s;
            s = 0;
        }while(res>=10);
        n = res;
        System.out.println(n);

        System.out.println("\nWilly-nilly, this semester I will learn " + languages[res]);

    }
}
