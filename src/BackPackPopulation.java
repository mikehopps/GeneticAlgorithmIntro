import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by michael_hopps on 11/27/17.
 */
public class BackPackPopulation {

    private ArrayList<BackPackGene> genes;
    private double elitism;
    private int size;

    public BackPackPopulation(int size){
        genes = new ArrayList<>();
        this.size = size;
        fill();
        elitism = .2;
    }

    //fill
    //if in top 20% (elitism), keep, else mateX
    public void fill(){
        while(genes.size() < size){
            if(genes.size() < size / 3) {
                genes.add(new BackPackGene());
            }else{
                mate();
            }
        }
//
    }
    public void mate(){
        int a = (int)(Math.random()*genes.size());
        int b = (int)(Math.random()*genes.size());
        while(a == b)
            b = (int)(Math.random()*genes.size());
        BackPackGene[] kids = genes.get(a).mate(genes.get(b));
        genes.add(kids[0]);
        genes.add(kids[1]);
    }

    public void sort(){
        Collections.sort(genes, new Comparator<BackPackGene>() {
            @Override
            public int compare(BackPackGene o1, BackPackGene o2) {
                return o2.getCostScore() - o1.getCostScore();
            }
        });
    }
    public void kill(){
        int target = (int)(elitism*genes.size());
        while(genes.size() > target){
            genes.remove(genes.size()-1);
        }
    }
    public void generation(){

        sort();
        kill();
        mate();
        fill();
        mutate();
        sort();
//        print();
    }
    public void print(){
        for(BackPackGene g: genes){
            System.out.print(g.getCostScore() + "\t");
        }
        System.out.println();
    }
public void mutate(){
    //all but top score have chance to mutate
    for (int j = 1; j < genes.size(); j++) {
        genes.get(j).mutate(.7);
    }
}

}
