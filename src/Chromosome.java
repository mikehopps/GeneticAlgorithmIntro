/**
 * Created by michael_hopps on 11/13/17.
 */
public class Chromosome {

    private String code;
    private int costScore;

    //Makes a random string of the given length.
    public Chromosome(int length){
        code = "";
        for (int i = 0; i < length; i++) {
            char c = (char)(int)(Math.random()*256);
            code += c;
        }
        costScore = 9999;
    }
    public Chromosome(String code){
        this.code = code;
        costScore = 9999;
    }

    public Chromosome[] mate(Chromosome other){
        int pivot = code.length()/2+1;
        String firstChild = code.substring(0, pivot) + other.getCode().substring(pivot);
        String secondChild = other.getCode().substring(0, pivot) + code.substring(pivot);
        Chromosome[] kids = {new Chromosome(firstChild), new Chromosome(secondChild)};
        return kids;
    }

    public Chromosome[] mate2(Chromosome other){
        String kid1 = "";
        String kid2 = "";
        for (int i = 0; i < code.length(); i++) {
            if(i % 2 == 0) {
                kid1 += code.charAt(i);
                kid2 += other.code.charAt(i);
            }else{
                kid2 += code.charAt(i);
                kid1 += other.code.charAt(i);
            }
        }
        Chromosome[] ans = {new Chromosome(kid1), new Chromosome(kid2)};
        return ans;

    }

    public void mutate(double chance){
        if(Math.random() > chance)
            return; //don't mutate.
        int index = (int)(Math.random()*code.length());
        int charInt = (int)(code.charAt(index));
        int sign = 1;
        if(Math.random() < .5)
            sign *= -1;
        charInt += (int)(Math.random()*5+1)*sign;   //+- 1-3 to char
        code = code.substring(0, index) + (char)(charInt) + code.substring(index+1);
        if(Math.random() < chance)
            mutate(chance);
    }

    public void calculateScore(String goal){
        int total = 0;
        for (int i = 0; i < code.length(); i++) {
            char a = goal.charAt(i);
            char b = code.charAt(i);
            int diff = (int)a - (int)b;
            total += diff*diff;
        }
        costScore = total > -1 ? total : 999999;
    }

    public String getCode() {
        return code;
    }

    public int getCostScore() {
        return costScore;
    }
}
