import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Example {
	public static void main(String args[]) {
		List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH)
			);
		
		// 에제 4-1  컬렉션 : for-each
		List<String> names = new ArrayList<>();
		for(Dish d: menu) {
			names.add(d.getName());
		}

		// 예제 4-2 컬렉션 : Iterator
		List<String> names2 = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while(iterator.hasNext()) {
			Dish d = iterator.next();
			names.add(d.getName());
		}

		// 예제 4-3 스트림 : 내부 반복
		List<String> names3 = menu.stream().map(Dish::getName).collect(Collectors.toList());
	
		// 4.4.1 중간 연산
		List<String> names4 = menu.stream()
				.filter(d -> {
					System.out.println("filtering "+d.getName());
					return d.getCalories() > 300;
				})
				.map(d -> {
					System.out.println("mapping "+d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(Collectors.toList());
		System.out.println(names4);
		
		/**
		 * 결과
		 * filtering pork
		 * mapping pork
		 * filtering beef
 		 * mapping beef
		 * filtering chicken
		 * mapping chicken
		 * [pork, beef, chicken]
		 */
	}
}

//List<Dish> lowCaloricDishes = new ArrayList<>();
//for(Dish d: menu) {
//	if(d.getCalories() < 400) {
//		lowCaloricDishes.add(d)
//	}
//}
//
//Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
//	public int compare(Dish d1, Dish d2) {
//		return Integer.compare(d1.getCalories(), d2.getCalories());
//	}
//});
//
//List<String> lowCaloricDishesName = new ArrayList<>();
//for(Dish d: lowCaloricDishes) {
//	lowCaloricDishesName.add(d.getName());
//}
//
//
//import static java.util.Comparator.comparing;
//import static java.util.stream.Collectors.toList;
//
//import java.util.Arrays;
//import java.util.Iterator;
//
//import static java.util.stream.Collectors.toList;
//
//List<String> threeHighCaloricDishNames = menu.stream()
//				.filter(d -> d.getCalories() > 300)
//				.map(Dish::getName)
//				.limit(3)
//				.collect(toList());
//
//List<String> title = Arrays.asList("Java8", "In", "Action");
//Stream<String> s = title.stream();
//s.forEach(System.out::println);
//s.forEach(System.out::println);




