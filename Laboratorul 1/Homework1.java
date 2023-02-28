import java.util.Scanner;
public class Homework1 {
    public static boolean isNumeric(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) {
        int number=0;
        long startTime = System.nanoTime();
        if (args.length == 0) {
            System.out.println("Numar insuficient de argumente!");
            Scanner sc1 = new Scanner(System.in);
            while(true) {
                System.out.println("Introduceti un numar:");
                number=sc1.nextInt();
                break;
            }
        }
        else {
            if(isNumeric(args[0])){
                number=Integer.parseInt(args[0]);
            }
            Scanner sc2 = new Scanner(System.in);
            while(!isNumeric(args[0])) {
                System.out.println("Introduceti un numar:");
                number=sc2.nextInt();
            }
        }
        int[][] matrix = new int[number][number];
        for(int i=0; i< number; i++)
            for(int j=0; j< number;j++)
                matrix[i][j]=(i+j)%number+1;

        System.out.println("Afisarea liniilor ca obiecte de tip String:"); //cu stringBuffer
        String aux;
        for(int i=0; i< number && number<30_000; i++) {
            StringBuffer str = new StringBuffer();
            for (int j = 0; j < number; j++){
                str.append(matrix[i][j]);
            }
            aux= str.toString();
            System.out.println(aux);
        }

        System.out.println("Afisarea coloanelor ca obiecte de tip String:");
        for(int i=0; i< number && number<30_000; i++) {
            for (int j = 0; j < number; j++){
                System.out.print(matrix[j][i]);
            }

            System.out.print("\n");
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        if(number>=30_000){
            System.out.println("Timpul de executie in nanosecunde: " + timeElapsed);
            System.out.println("Timpul de executie in milisecunde: " + timeElapsed / 1000000);
        }

        }
    }
