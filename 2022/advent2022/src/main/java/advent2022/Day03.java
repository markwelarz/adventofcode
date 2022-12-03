package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.CharSource;

public class Day03
{
	public int part1(CharSource input) throws IOException
	{
		var rucksacks = read(input);
		int prioritiesSum = 0;
		for (var r : rucksacks)
		{
			var itemsInBothCompartments = Sets.intersection(r.compartment1(), r.compartment2());
			prioritiesSum += itemsInBothCompartments.stream().mapToInt(v -> v).sum();
		}

		return prioritiesSum;
	}

	public int part2(CharSource input) throws IOException
	{
		var rucksacks = read(input);
		var groupsOf3 = Iterables.partition(rucksacks, 3);
		int sumOfBadges = 0;
		for (var group : groupsOf3)
		{
			var combined1And2 = Sets.intersection(group.get(0).combinedCompartments(), group.get(1).combinedCompartments());
			var combinedAll = Sets.intersection(combined1And2, group.get(2).combinedCompartments());
			sumOfBadges += Iterables.getOnlyElement(combinedAll);
		}

		return sumOfBadges;
	}

	List<Rucksack> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			List<Rucksack> rucksacks = new ArrayList<>();
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				int halfSize = line.length() / 2;

				rucksacks.add(new Rucksack(mapToScores(line, 0, halfSize),
						mapToScores(line, halfSize, line.length())));

			}

			return rucksacks;
		}
	}

	private Set<Integer> mapToScores(String line, int fromInc, int toEx)
	{
		Set<Integer> scores = new HashSet<>();
		var compartment = ArrayUtils.subarray(line.toCharArray(), fromInc, toEx);
		for (char ch : compartment)
		{
			if (Character.isUpperCase(ch))
			{
				scores.add(ch - 38);
			}
			else if (Character.isLowerCase(ch))
			{
				scores.add(ch - 96);
			}
			else
				throw new IllegalArgumentException();
		}
		return scores;
	}

	record Rucksack(Set<Integer> compartment1, Set<Integer> compartment2)
	{
		Set<Integer> combinedCompartments()
		{
			return ImmutableSet.<Integer>builder()
					.addAll(compartment1)
					.addAll(compartment2)
					.build();
		}
	}
}
