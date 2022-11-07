package advent2021;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.CharSource;

public class Day06
{
	public int part1(CharSource input, int cycles) throws IOException
	{
		Multiset<Integer> lanternfishAges = read(input);

		for (int i = 0; i < cycles; i++)
		{
			Multiset<Integer> lanternfishAges1 = HashMultiset.create();

			for (var age : lanternfishAges.elementSet())
			{
				if (age > 0)
				{
					lanternfishAges1.add(age - 1, lanternfishAges.count(age));
				}
				else if (age == 0)
				{
					lanternfishAges1.add(6, lanternfishAges.count(age));
					lanternfishAges1.add(8, lanternfishAges.count(age));
				}
			}

			lanternfishAges = lanternfishAges1;
		}

		return lanternfishAges.size();
	}

	public long part2(CharSource input, int cycles) throws IOException
	{
		Map<Integer, Long> lanternfishAges = read(input).entrySet().stream()
				.collect(Collectors.toMap(v -> v.getElement(), v -> Long.valueOf(v.getCount())));

		for (int i = 0; i < cycles; i++)
		{
			Map<Integer, Long> lanternfishAges1 = new HashMap<>();

			for (var age : lanternfishAges.keySet())
			{
				if (age > 0)
				{
					long fromLastCycle = lanternfishAges.get(age);
					lanternfishAges1.merge(age - 1, fromLastCycle, (v1, v2) -> v1 + v2);
				}
				else if (age == 0)
				{
					lanternfishAges1.put(6, lanternfishAges.get(age));
					lanternfishAges1.put(8, lanternfishAges.get(age));
				}
			}

			lanternfishAges = lanternfishAges1;
		}

		return lanternfishAges.values().stream().mapToLong(v -> v).sum();
	}

	private Multiset<Integer> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			String line = sc.nextLine();

			Multiset<Integer> lanternfishAges = Splitter.on(",")
					.splitToStream(line)
					.map(v -> Integer.parseInt(v))
					.collect(Collectors.toCollection(HashMultiset::create));

			return lanternfishAges;
		}
	}
}
