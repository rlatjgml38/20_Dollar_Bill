
public class Example2 {

}


public class AppleComparator implements Comparator<Apple> {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(g2.getWeight());
	}
}

inventory.sort(new AppleComparator());


inventory.sort(new Comparator<Apple>() {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(g2.getWeight());
	}
});


inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(g2.getWeight()));


inventory.sort((a1, a2) -> a1.getWeight().compareTo(g2.getWeight()));

// Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
import static java.util.Comparator.comparing;
inventory.sort(comparing((a) -> a.getWeight()));


inventory.sort(comparing(Apple::getWeight));


Function<Integer, Integer> f = x -> x+1;
Function<Integer, Integer> g = x -> x*2;
Function<Integer, Integer> h = f.andThen(g);


Function<Integer, Integer> f = x -> x+1;
Function<Integer, Integer> g = x -> x*2;
Function<Integer, Integer> h = f.compose(g);