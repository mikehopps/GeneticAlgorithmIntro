import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static javax.script.ScriptEngine.FILENAME;

/**
 * Created by michael_hopps on 11/17/17.
 */
public class BackPackMain {

    static ArrayList<Element> elementList;

    public static void main(String[] args) {
        //create elements.
        loadElements();
        System.out.println(elementList);
        BackPackPopulation pop = new BackPackPopulation(20);
        int numGens = 0;
        while(numGens < 500000){
            pop.generation();
            numGens++;
//            pop.print();
        }
        pop.print();

    }

    public static void loadElements(){
        elementList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./src/Elements.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] arr = sCurrentLine.split(" ");
                elementList.add(new Element(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
