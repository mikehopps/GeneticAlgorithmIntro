import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by michael_hopps on 11/13/17.
 */
public class Population {

    private ArrayList<Chromosome> individuals;
    private int generationNumber;
    private String goal;

    public Population(int n, String goal){
        individuals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //Make random Chromosomes to start the Population
            Chromosome temp = new Chromosome(goal.length());
            individuals.add(temp);
        }
        this.goal = goal;
        generationNumber = 0;

    }

    public boolean nextGen() {
        display();

        Chromosome[] kids = individuals.get(0).mate(individuals.get(1));
        individuals.set(individuals.size()-1, kids[0]);
        individuals.set(individuals.size()-2, kids[1]);

        Chromosome[] kids2 = individuals.get(0).mate2(individuals.get(1));
        individuals.set(individuals.size()-3, kids2[0]);
        individuals.set(individuals.size()-4, kids2[1]);

        for(Chromosome c: individuals)  {
            c.mutate(.285);
            c.calculateScore(goal);
        }
        sort();

        generationNumber++;
        if(individuals.get(0).getCostScore() == 0){
            return true;
        }
        return false;
    }

    public void sort(){
        Collections.sort(individuals, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return o1.getCostScore() - o2.getCostScore();
            }
        });
    }
    public void display() {
//        System.out.println("Generation " + generationNumber + " size " + individuals.size());
//        for (Chromosome c : individuals)
//            System.out.println(c.getCode() + " (" + c.getCostScore() + ")" + " " + c.getCode().length());
    }

    public int getGenerationNumber(){
        return generationNumber;
    }
}
