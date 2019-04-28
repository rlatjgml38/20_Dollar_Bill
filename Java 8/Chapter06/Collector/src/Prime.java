import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class Prime {
	/**
	 * n 이하의 자연수를 소수와 비소수로 분류
	 * 
	 * @param n
	 * @return
	 */
	public Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2,  n)
				.boxed()
				.collect(partitioningBy(candidate -> isPrime(candidate)));
	}
	
	/**
	 * 제곱근 이하로 대상의 숫자 범위를 제한
	 * 
	 * @param candidate
	 * @return
	 */
	public boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2,  candidateRoot)
				.noneMatch(i -> candidate % i == 0);
	}
	
	/**
	 * 중간 결과 리스트를 전달
	 * 
	 * @param primes
	 * @param candidate
	 * @return
	 */
//	public boolean isPrime(List<Integer> primes, int candidate) {
//		return primes.stream().noneMatch(i -> candidate % i == 0);
//	}
	
	/**
	 * 대상의 제곱보다 큰 소수를 찾으면 겁사를 중단
	 * 자신의 제곱근보다 작은 소수만 찾음
	 * 
	 * @param primes
	 * @param candidate
	 * @return
	 */
	public static boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i -> i <= candidateRoot).stream()
				.noneMatch(p -> candidate % p == 0);
	}
	
	/**
	 * 프레디케이트를 만족하는 가장 긴 요소로 된 리스트 반환
	 * 
	 * @param list
	 * @param p
	 * @return
	 */
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
