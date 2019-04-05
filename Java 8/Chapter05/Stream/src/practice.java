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
		
		System.out.println("1. 2011�⿡ �Ͼ ��� Ʈ�������� ã�� ���� ������������ ����");
		transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);
		System.out.println();
		
		System.out.println("2. �ŷ��ڰ� �ٹ��ϴ� ��� ���ø� �ߺ� ���� ����");
		transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
		System.out.println();
		
		System.out.println("3. Cambridge���� �ٹ��ϴ� ��� �ŷ��ڸ� ã�Ƽ� �̸������� ����");
		transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
				.forEach(System.out::println);
		System.out.println();
		
		System.out.println("4. ��� �ŷ����� �̸��� ���ĺ������� ����");
		transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().forEach(System.out::println);
		System.out.println();
		
		System.out.println("5. Milan�� �ŷ��ڰ� �ִ��� ���");
		if(transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity())))
			System.out.println(true);
		System.out.println();
		
		System.out.println("6. Cambridge�� �����ϴ� �ŷ����� ��� Ʈ����� ���� ���");
		transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).forEach(System.out::println);
		System.out.println();
		
		System.out.println("7. ��ü Ʈ����� �� �ִ�");
		System.out.println(transactions.stream().map(t -> t.getValue()).reduce(Integer::max).get());
		System.out.println();
		
		System.out.println("8. ��ü Ʈ����� �� �ּڰ�");
		System.out.println(transactions.stream().map(t -> t.getValue()).reduce(Integer::min).get());
	}
}
