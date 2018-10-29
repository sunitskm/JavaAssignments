import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CheckAndPrintDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements you want to enter: 1");
        Integer n = sc.nextInt();
        Integer[] num = new Integer[n];
        for(int i = 0;i < n; i++){
            System.out.println("Enter a number ");
            num[i] = sc.nextInt();
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> setDuplicate = new HashSet<>();
        for(int i=0; i < n; i++){
            if(!set.add(num[i])){
                setDuplicate.add(num[i]);
            }
        }

        if(!setDuplicate.isEmpty()){
            System.out.println("Duplicates are found in the list!");
            Iterator<Integer> itr = setDuplicate.iterator();
            System.out.println("The duplicates are :");
            while(itr.hasNext()){
                System.out.println(itr.next());
            }
        }
        else{
            System.out.println("No Duplicates found");
        }
    }


}
