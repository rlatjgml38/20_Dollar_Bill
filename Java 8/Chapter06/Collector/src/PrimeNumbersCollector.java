import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

// 1단계: Collector 클래스 시그너처 정의
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	// 2단계: 리듀싱 연산 구현
	
	// 누적자를 만드는 함수를 반환
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {{
			put(true, new ArrayList<Integer>());
			put(false, new ArrayList<Integer>());
		}};
	}
	
	// 지금까지 발견한 소수를 포함하는 누적자에 접근
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
			acc.get(isPrime(acc.get(true), (int)candidate)).add(candidate);
		};
	}
	
	// 3단계: 병렬 실행할 수 있는 컬렉터 만들기(가능하다면)
	
	// 병렬 수집 과정에서 두 부분 누적자를 합찰 수 있는 메서드
	// 알고리즘 자체가 순차적이어서 컬렉터를 실제 병렬로 사용할 수 없음
	// 빈 구현으로 남기거나, UnsupportedOperationException을 던지도록 구현해도 좋음
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	// 4단계: finisher 메서드와 컬렉터의 characteristics 메서드
	
	// accumulator의 형식은 컬렉터 결화 형식과 같으므로 변환 필요 없음
	// 항등함수를 반환
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	// IDENTITY_FINISH
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
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
