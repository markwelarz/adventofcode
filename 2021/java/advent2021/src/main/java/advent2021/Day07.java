package advent2021;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.io.CharSource;

public class Day07
{
	public int part(CharSource input, boolean part1) throws IOException
	{
		var crabs = read(input);
		var stats = crabs.stream().collect(Collectors.summarizingInt(v -> v.position()));

		int bestCost = Integer.MAX_VALUE;

		for (int moveTo = stats.getMin(); moveTo <= stats.getMax(); moveTo++)
		{
			int cost;
			if (part1)
				cost = costWhenMoveEverythingTo1(crabs, moveTo);
			else
				cost = costWhenMoveEverythingTo2(crabs, moveTo);

			if (cost < bestCost)
			{
				bestCost = cost;
			}
		}

		return bestCost;
	}

	int costWhenMoveEverythingTo1(List<Crab> crabs, int toPosition)
	{
		int totalCost = crabs.stream()
				.mapToInt(v -> Math.abs(v.position() - toPosition))
				.sum();

		return totalCost;
	}

	int costWhenMoveEverythingTo2(List<Crab> crabs, int toPosition)
	{
		int totalCost = crabs.stream()
				.mapToInt(v -> Math.abs(v.position() - toPosition))
				.map(n -> n * (n + 1) / 2)
				.sum();

		return totalCost;
	}

	List<Crab> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{			
			String line = sc.nextLine();
			
			return Splitter.on(",")
					.splitToStream(line)
					.map(v -> Integer.parseInt(v))
					.map(Crab::new)
					.toList();

		}
	}
}

record Crab(int position)
{
}
