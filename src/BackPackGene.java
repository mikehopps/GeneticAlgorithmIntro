import java.awt.*;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 11/17/17.
 */
public class BackPackGene {

    private boolean[] code;
    private int costScore;
    private int maxWeight;

    //Makes a random string of the given length.
    public BackPackGene() {
        code = new boolean[BackPackMain.elementList.size()];
        for(int i = 0; i < code.length; i++){
            code[i] = Math.random() < .2;
        }
        maxWeight = 1000;
        calculateScore();

    }
    public BackPackGene(boolean[] arr){
        code = arr;
        maxWeight = 1000;
        calculateScore();
    }

    public BackPackGene[] mate(BackPackGene other) {
        boolean[] kid1 = new boolean[code.length];
        boolean[] kid2 = new boolean[code.length];
        int pivot = (int)(Math.random()*code.length);
        for (int i = 0; i < code.length; i++) {
            if(i >= pivot){
                kid1[i] = code[i];
                kid2[i] = other.code[i];
            }else{
                kid2[i] = code[i];
                kid1[i] = other.code[i];
            }
        }
        BackPackGene[] kids = new BackPackGene[2];
        kids[0] = new BackPackGene(kid1);
        kids[1] = new BackPackGene(kid2);
        kids[0].mutate(.7); //this adds a bit of +consistency to the results.
        kids[1].mutate(.7);
        return kids;
    }

    public void mutate(double chance) {
        if (Math.random() > chance)
            return; //don't mutate.
        int index = (int) (Math.random() * code.length);
        code[index] = !code[index];
        calculateScore();
    }

    public void calculateScore() {
        int total = 0;
        int weight = 0;
        ArrayList<Element> elements = BackPackMain.elementList;
        for (int i = 0; i < code.length; i++) {
            if(code[i]) {
                total += elements.get(i).value;
                weight += elements.get(i).weight;
            }
        }
        if(weight > maxWeight)
            total -= 50*(weight-maxWeight);
        costScore = total;
    }


    public boolean[] getCode() {
        return code;
    }

    public int getCostScore() {
        return costScore;
    }
}


