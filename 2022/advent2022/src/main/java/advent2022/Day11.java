package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.io.CharSource;
import com.google.common.math.LongMath;

public class Day11
{
	public long part1(CharSource input) throws IOException
	{
		return monkeyStuff(input, 20, true);
	}

	public long part2(CharSource input) throws IOException
	{
		return monkeyStuff(input, 10000, false);
	}

	long monkeyStuff(CharSource input, int times, boolean divideWorry3) throws IOException
	{
		List<Monkey> monkeys = read(input);
		int bigModulo = monkeys.stream().mapToInt(Monkey::divisbleByTest).reduce(1, (a, b) -> a * b);

		Map<Integer, Integer> inspectionsByMonkeyNumber = new HashMap<>();
		for (int round = 1; round <= times; round++)
		{
			for (var m : monkeys)
			{
				inspectionsByMonkeyNumber.merge(m.number, m.items().size(), (a, b) -> a + b);

				while (!m.items().isEmpty())
				{
					long item = m.items.removeFirst();
					long operatedItem = m.op().execute(item, bigModulo);
					if (divideWorry3)
						operatedItem /= 3;
					if (operatedItem % m.divisbleByTest() == 0)
						throwToMonkey(monkeys, m.throw1, operatedItem);
					else
						throwToMonkey(monkeys, m.throw2, operatedItem);
				}
			}

			System.out.println("after round " + round + " " + inspectionsByMonkeyNumber);
		}


		return inspectionsByMonkeyNumber.values().stream()
				.sorted(Collections.reverseOrder())
				.mapToLong(v -> v)
				.limit(2)
				.reduce(1, (a, b) -> a * b);
	}

	void throwToMonkey(List<Monkey> monkeys, int monkeyId, long item)
	{
		monkeys.get(monkeyId).items.addLast(item);
	}

	List<Monkey> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			sc.useDelimiter("(\\s|\\:|\\,)+");
			List<Monkey> monkeys = new ArrayList<>();

			while (sc.hasNext("Monkey"))
			{
				sc.skip("\\s*Monkey");
				int monkeyNumber = sc.nextInt();
				sc.skip("\\:").skip("\\s*Starting items");
				Deque<Long> startingItems = new LinkedList<>();
				while (sc.hasNextInt())
				{
					long startingItem = sc.nextInt();
					startingItems.add(startingItem);
				}

				sc.skip("\\s*Operation: new =");

				String var1 = sc.next();
				String op = sc.next();
				String var2 = sc.next();

				sc.skip("\\s*Test: divisible by");
				int divBy = sc.nextInt();

				sc.skip("\\s*If true: throw to monkey");
				int monkeyThrow1 = sc.nextInt();

				sc.skip("\\s*If false: throw to monkey");
				int monkeyThrow2 = sc.nextInt();

				monkeys.add(new Monkey(monkeyNumber, startingItems, divBy, new Operation(var1, op, var2), monkeyThrow1, monkeyThrow2));
			}
			return monkeys;
		}
	}

	record Operation(String var1, String op, String var2)
	{
		public long execute(long old, int bigModulo)
		{
			long resolvedVar1;
			long resolvedVar2;

			if (var1.equals("old"))
				resolvedVar1 = old;
			else
				resolvedVar1 = Long.parseLong(var1);

			if (var2.equals("old"))
				resolvedVar2 = old;
			else
				resolvedVar2 = Long.parseLong(var2);

			if (op.equals("+"))
				return LongMath.checkedAdd(resolvedVar1, resolvedVar2) % bigModulo;
			else if (op.equals("*"))
				return LongMath.checkedMultiply(resolvedVar1, resolvedVar2) % bigModulo;
			else
				throw new IllegalArgumentException(op);
		}
	}

	record Monkey(int number, Deque<Long> items, int divisbleByTest, Operation op, int throw1, int throw2)
	{

	}
}
