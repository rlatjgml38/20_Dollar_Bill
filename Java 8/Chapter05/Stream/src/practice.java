import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class practice {
	static Trader raoul = new Trader("Raoul", "Cambridge");
	static Trader mario = new Trader("Mario", "Milan");
	static Trader alan = new Trader("Alan", "Cambridge");
	static Trader brian = new Trader("Brian", "Cambridge");
	
	public static void main(String args[]) {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
		
		System.out.println("1. 2011년에 일어난 모든 트랜젝션을 찾아 값을 오름차순으로 정리");
		transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);
		System.out.println();
		
		System.out.println("2. 거래자가 근무하는 모든 도시를 중복 없이 나열");
		transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
		System.out.println();
		
		System.out.println("3. Cambridge에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬");
		transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
				.forEach(System.out::println);
		System.out.println();
		
		System.out.println("4. 모든 거래자의 이름을 알파벳순으로 정렬");
		transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().forEach(System.out::println);
		System.out.println();
		
		System.out.println("5. Milan에 거래자가 있는지 출력");
		if(transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity())))
			System.out.println(true);
		System.out.println();
		
		System.out.println("6. Cambridge에 거주하는 거래자의 모든 트랜잭션 값을 출력");
		transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).forEach(System.out::println);
		System.out.println();
		
		System.out.println("7. 전체 트랜잭션 중 최댓값");
		System.out.println(transactions.stream().map(t -> t.getValue()).reduce(Integer::max).get());
		System.out.println();
		
		System.out.println("8. 전체 트랜잭션 중 최솟값");
		System.out.println(transactions.stream().map(t -> t.getValue()).reduce(Integer::min).get());
	}
}
