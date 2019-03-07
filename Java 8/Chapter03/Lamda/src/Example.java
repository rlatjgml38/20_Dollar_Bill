import java.util.Arrays;
import java.util.List;

public class Example {
	
}

Comparator<Apple> byWeight = new Comparatpr<Apple>() {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
};

Comaparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().comapareTo(a2.getWeight());

(int x, int y) -> {
	System.out.println("Result");
	System.out.println(x+y);
}

// 함수형 인터페이스
public interface Adder {
	int add(int a, int b);
}

// 두 추상 add 메서드(하나는 Adder에서 상속받음)를 포함
public interface SmartAdder extends Adder {
	int add(double a, double b);
}

// 추상 메서드가 없음
public interface Nothing {}

Runnable r1 = () -> System.out.println("Hello World 1"); // 람다 사용

Runnable r2 = new Runnable() {
	public void run() {
		System.out.println("Hello World 2"); // 익명 클래스 사용
	}
};

public static void process(Runnable r) {
	r.run();
}

process(r1); // Hello World 1
process(r2); // Hello World 2
process(() -> System.out.println("Hello World 3")); // 직접 전달된 람다 표현식 // Hello World 3


execute(() -> {}); // () -> void
public void excute(Runnable r) {
	r.run(); // () -> void
}

public Collable<String> fetch() { // () -> String
	return () -> "Tricky example ;-)"; // () -> String
}

Predicate<Apple> p = (Apple a) -> a.getWeight(); // (Apple) -> boolean , (Apple) -> Integer


@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}

public static <T, R> List<R> map(List<T> list, Function <T, R> f) {
	List<R> result = new ArrayList<>();
	for (T s : list) {
		result.add(f.apply(s));
	}
	return result;
}

// [7, 2, 6]
List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());

// int가 Integer로 박싱
List<Integer> list = new ArrayList<>();
for (int i=300; i<400; i++) {
	list.add(i);
}

public interface IntPredicate {
	boolean test(int t);
}

// 박싱 없음
IntPredicate evenNumbers = (int i) -> i % 2 == 0;
evenNumbers.test(1000);

// 1000을 Integer로 박싱
Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
oddNumbers.test(1000);

Function<BufferedReader, String> f = (BufferedReader b) -> {
	try {
		return b.readLine();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
};