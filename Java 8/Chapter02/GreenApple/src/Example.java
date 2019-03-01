import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Example { 
	public static void main(String args[]) {
		List<Apple> inventory = Arrays.asList(new Apple(120, "green"),
				new Apple(155, "green"), new Apple(80, "red"));
				
		inventory.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight()));
			}
		});
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println(inventory.get(0).getWeight());
			}
		});
		
		
		List<Apple> inventory2 = Arrays.asList(new Apple(80, "green"),
				new Apple(155, "green"), new Apple(120, "red"));
		
		
		inventory2.sort((Apple a1, Apple a2) -> Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight())));
		
		
		Thread t2 = new Thread(() -> System.out.println(inventory2.get(0).getWeight()));
		
		t.run();
		t2.run();
	}
}
