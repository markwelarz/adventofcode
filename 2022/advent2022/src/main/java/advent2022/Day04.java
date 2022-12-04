package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Range;
import com.google.common.io.CharSource;

public class Day04
{
	public long part1(CharSource input) throws IOException
	{
		return read(input).stream()
				.filter(PairOfElves::encloses)
				.count();
	}

	public long part2(CharSource input) throws IOException
	{
		return read(input).stream()
				.filter(PairOfElves::overlaps)
				.count();
	}

	List<PairOfElves> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			List<PairOfElves> pairs = new ArrayList<>();

			sc.useDelimiter(",|\\-|\\s+");
			
			while(sc.hasNext())
			{
				int from1 = sc.nextInt();
				int to1 = sc.nextInt();
				int from2 = sc.nextInt();
				int to2 = sc.nextInt();
				
				pairs.add(new PairOfElves(Range.closed(from1, to1), Range.closed(from2, to2)));
			}
			
			return pairs;
		}
	}

	record PairOfElves(Range<Integer> first, Range<Integer> second)
	{
		boolean encloses()
		{
			return first.encloses(second) || second.encloses(first);
		}

		boolean overlaps()
		{
			return first.isConnected(second);
		}
	}
}
