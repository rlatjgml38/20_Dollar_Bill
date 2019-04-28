import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectorHarness {
	public static void main(String[] args) {
		long fastest = Long.MAX_VALUE;
		for(int i=0; i<10; i++) {
			long start = System.nanoTime();
			partitionPrimes(1_000_000);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if(duration < fastest) {
				fastest = duration;
			}
		}
		System.out.println("Fastest execurion done in "+fastest+" mesecs");
	} 
	
	
	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
	}
	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(
				() -> new HashMap<Boolean, List<Integer>>() {{
					put(true, new ArrayList<Integer>());
					put(false, new ArrayList<Integer>());
				}},
				(acc, candidate) -> {
					acc.get(isPrime(acc.get(true), candidate)).add(candidate);
				},
				(map1, map2) -> {
					map1.get(true).addAll(map2.get(true));
					map1.get(false).addAll(map2.get(false));
				}
				);
	}
	
	
	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2,  candidateRoot)
				.noneMatch(i -> candidate % i == 0);
	}
	
	public static boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i -> i <= candidateRoot).stream()
				.noneMatch(p -> candidate % p == 0);
	}
	
	public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i=0;
		for (A item : list) {
			if(!p.test(item)) {
				return list.subList(0,  i);
			}
			i++;
		}
		return list;
	}
}
