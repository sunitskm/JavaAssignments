import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class GenerateTwoListsAndMerge {

    public static void main(String[] args) {
        Integer[] randInt1 = new Integer[1000];
        Integer[] randInt2 = new Integer[1000];
        Random rand = new Random();
        Integer count = 0;
        for(int i=0;i<1000;i++) {
            int tempInt = rand.nextInt(1001);
            if(tempInt<=1000){
                count ++;
                //System.out.println("Random Integer generated is "+ tempInt);
            }
            randInt1[i] = tempInt;
        }
        List<Integer> listInt1 = new ArrayList<>();
        listInt1 = Arrays.asList(randInt1);
        List<Integer> result = listInt1.stream()
        		.filter(x -> x<0 || x>1000)
        		.sorted()
        		.collect(Collectors.toList());
        if(result.size() == 0){
            System.out.println("All the integers are validated and lie within the range of 1000, with 1000 being the max");
        }
        else{
            System.out.println("Incorrect Integers genetated");
        }

        //Validating 2nd list
        count = 0;
        for(int i=0;i<1000;i++) {
            int tempInt = rand.nextInt(1001);
            if(tempInt<=1000){
                count ++;
                //System.out.println("Random Integer generated is "+ tempInt);
            }
            randInt2[i] = tempInt;
        }
        List<Integer> listInt2 = new ArrayList<>();
        listInt2 = Arrays.asList(randInt2);
         result = listInt2.stream()
        		.filter(x -> x<0 || x>1000)
        		.sorted()
        		.collect(Collectors.toList());
        if(result.size() == 0){
            System.out.println("All the integers are validated and lie within the range of 1000, with 1000 being the max");
        }
        else{
            System.out.println("Incorrect Integers genetated");
        }
        Arrays.sort(randInt1);
        Arrays.sort(randInt2);

        Integer[] randMerge = mergeIntoOne(randInt1,randInt2);
        Set<Integer> mergeSet = new HashSet<Integer>();
        for (Integer i: randMerge) {
            mergeSet.add(i);
        }
        Iterator<Integer> it = mergeSet.iterator();
        while(it.hasNext()) {
        	//System.out.println(it.next());
        }

    }
    //Function for merging
    public static Integer[] mergeIntoOne(Integer[] randInt1,Integer[] randInt2){
        Integer[] randMerge = new Integer[2000];
        int i = 0, j = 0, k = 0;
        while(i<1000 && j<1000){
            if(randInt1[i]<randInt2[i]){
                randMerge[k++] = randInt1[i++];
            }
            else{
                randMerge[k++] = randInt2[j++];
            }
        }
        while(i<1000){
            randMerge[k++] = randInt1[i++];
        }
        while(j<1000){
            randMerge[k++] = randInt2[j++];
        }
        return randMerge;
    }
}
