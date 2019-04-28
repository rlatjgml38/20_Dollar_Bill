import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

/**
 * Stream<T>의 모든 요소를 List<T>로 수집하는 ToListCollector 클래스<br>
 * Collectors.toList가 반환하는 결과와 완전히 같지는 않음
 * 
 * @author seohee
 *
 * @param <T>
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new; // return () -> new ArrayList<T>(); 
	}
	
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add; // return (list, item) = list.add(item);
	}
	
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}
	
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
	}
}