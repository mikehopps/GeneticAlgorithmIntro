/**
 * Created by michael_hopps on 11/13/17.
 */
public class Main {

    public static void main(String[] args) {

        int popSize = 12;
        String goal = "This is a much longer string. Good luck!";
        Population pop;
        int sum = 0;
        int num = 1000;

//        for (int i = 0; i < 1000; i++) {
//            pop = new Population(12, "Hello World");
//            while(pop.nextGen());
//        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            pop = new Population(popSize, goal);
            while (!pop.nextGen())
            sum += pop.getGenerationNumber();
        }
        System.out.println((System.currentTimeMillis()-time)/num + " ms per solution");
        System.out.println(sum/num + " generations");


    }
}
