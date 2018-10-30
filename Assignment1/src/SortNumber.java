import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class SortNumber {
	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i < 1000; i++) {
			list.add(rand.nextInt(1000));
			
		}
		sortList(list);
		for(int i = 0;i < 1000; i++) {
			System.out.println(list.get(i));
			
		}
	}
	private static void sortList(List<Integer> num) {
		Collections.sort(num, (s1,s2) -> s1.compareTo(s2));
	}

}
