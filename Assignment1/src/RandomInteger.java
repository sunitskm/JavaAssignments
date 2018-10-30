import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class RandomInteger {
    public static void main(String[] args) {
        //Array to store 1000 random Integers
        Integer[] randInt = new Integer[1000];

        Random rand = new Random();
        Integer count = 0;
        for(int i=0;i<1000;i++) {
            //Generate a random Integer
            int tempInt = rand.nextInt(1001);
            if(tempInt<=1000){
                count ++;
                //System.out.println("Random Integer generated is "+ tempInt);
            }
            randInt[i] = tempInt;
        }
        List<Integer> listInt = Arrays.asList(randInt);
        //Validating if all the integers are within 1000 inclusive
        /*if(count == 1000){
            System.out.println("All the integers are validated and lie within the range of 1000, with 1000 being the max");
        }
        else{
            System.out.println("Incorrect Integers genetated");
        }*/
        List<Integer> result = listInt.stream()
        		.filter(n -> n<0 || n>1000)
        		.collect(Collectors.toList());
        		
        if(result.size()==0) {
        	System.out.println("All the integers are validated and lie within the range of 1000, with 1000 being the max");
        }
        else{
        	result.forEach(System.out::println);
            System.out.println("Incorrect Integers genetated");
        }
    }

}
