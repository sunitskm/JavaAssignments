import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckAndPrintDuplicates {
    public static void main(String[] args) {
        //Taking input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements you want to enter: ");
        Integer n = sc.nextInt();
        Integer[] num = new Integer[n];
        for(int i = 0;i < n; i++){
            System.out.println("Enter a number ");
            num[i] = sc.nextInt();
        }
        //Checking if duplicates are present by Lambdas
        
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> origList = Arrays.asList(num);
        list = origList.stream()
        		.distinct()
        		.collect(Collectors.toList());
        if(list.size()==origList.size()) {
        	System.out.println("No duplicates found");
        }
        else {
        	Set<Integer> set = new HashSet<>();
            Set<Integer> setDuplicate = new HashSet<>();
            for(int i=0; i < n; i++){
                if(!set.add(num[i])){
                    //Adding the duplicates
                    setDuplicate.add(num[i]);
                }
            }
            //Checking if duplicates are present and printing them
                System.out.println("Duplicates are found in the list!");
                Iterator<Integer> itr = setDuplicate.iterator();
                System.out.println("The duplicates are :");
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }
            
            
       
        }
        
         }


}
