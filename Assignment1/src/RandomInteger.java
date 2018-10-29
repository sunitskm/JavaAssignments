import java.util.Random;
public class RandomInteger {
    public static void main(String[] args) {
        Integer[] randInt = new Integer[1000];
        Random rand = new Random();
        Integer count = 0;
        for(int i=0;i<1000;i++) {
            int tempInt = rand.nextInt(1001);
            if(tempInt<=1000){
                count ++;
                System.out.println("Random Integer generated is "+ tempInt);
            }
            randInt[i] = tempInt;
        }
        if(count == 1000){
            System.out.println("All the integers are validated and lie within the range of 1000, with 1000 being the max");
        }
        else{
            System.out.println("Incorrect Integers genetated");
        }
    }

}
