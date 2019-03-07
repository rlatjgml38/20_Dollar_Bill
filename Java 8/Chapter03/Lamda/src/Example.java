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

// �Լ��� �������̽�
public interface Adder {
	int add(int a, int b);
}

// �� �߻� add �޼���(�ϳ��� Adder���� ��ӹ���)�� ����
public interface SmartAdder extends Adder {
	int add(double a, double b);
}

// �߻� �޼��尡 ����
public interface Nothing {}

Runnable r1 = () -> System.out.println("Hello World 1"); // ���� ���

Runnable r2 = new Runnable() {
	public void run() {
		System.out.println("Hello World 2"); // �͸� Ŭ���� ���
	}
};

public static void process(Runnable r) {
	r.run();
}

process(r1); // Hello World 1
process(r2); // Hello World 2
process(() -> System.out.println("Hello World 3")); // ���� ���޵� ���� ǥ���� // Hello World 3


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

// int�� Integer�� �ڽ�
List<Integer> list = new ArrayList<>();
for (int i=300; i<400; i++) {
	list.add(i);
}

public interface IntPredicate {
	boolean test(int t);
}

// �ڽ� ����
IntPredicate evenNumbers = (int i) -> i % 2 == 0;
evenNumbers.test(1000);

// 1000�� Integer�� �ڽ�
Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
oddNumbers.test(1000);

Function<BufferedReader, String> f = (BufferedReader b) -> {
	try {
		return b.readLine();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
};