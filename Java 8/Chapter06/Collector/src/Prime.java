import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class Prime {
	/**
	 * n ������ �ڿ����� �Ҽ��� ��Ҽ��� �з�
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
	 * ������ ���Ϸ� ����� ���� ������ ����
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
	 * �߰� ��� ����Ʈ�� ����
	 * 
	 * @param primes
	 * @param candidate
	 * @return
	 */
//	public boolean isPrime(List<Integer> primes, int candidate) {
//		return primes.stream().noneMatch(i -> candidate % i == 0);
//	}
	
	/**
	 * ����� �������� ū �Ҽ��� ã���� �̻縦 �ߴ�
	 * �ڽ��� �����ٺ��� ���� �Ҽ��� ã��
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
	 * ����������Ʈ�� �����ϴ� ���� �� ��ҷ� �� ����Ʈ ��ȯ
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
