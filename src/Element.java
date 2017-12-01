/**
 * Created by michael_hopps on 11/17/17.
 */
public class Element {

    int weight, value;
    String name;

    public Element(String name,int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.name = name;
    }

    public String toString(){
        return name + " weight: " + weight + " value: " + value;
    }
}
