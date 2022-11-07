package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.lang3.StringUtils;

import com.google.common.io.CharSource;

public class Day05
{
	public int part1(CharSource input) throws IOException
	{
		return this.bothParts(input, true);
	}

	public int part2(CharSource input) throws IOException
	{
		return this.bothParts(input, false);
	}

	public int bothParts(CharSource input, boolean ignoreDiags) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			MultiSet<Coord> coords = new HashMultiSet<>();

			while (sc.hasNext())
			{
				String firstCoord = sc.next();
				int x1 = Integer.parseInt(StringUtils.substringBefore(firstCoord, ","));
				int y1 = Integer.parseInt(StringUtils.substringAfter(firstCoord, ","));

				sc.next();
				String secondCoord = sc.next();
				int x2 = Integer.parseInt(StringUtils.substringBefore(secondCoord, ","));
				int y2 = Integer.parseInt(StringUtils.substringAfter(secondCoord, ","));

				if (x1 == x2)
				{
					for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++)
						coords.add(new Coord(x1, y));
				}
				else if (y1 == y2)
				{
					for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++)
						coords.add(new Coord(x, y1));
				}
				else if (!ignoreDiags)
				{
					coords.addAll(diagCoords(x1, x2, y1, y2));
				}
			}

			int overlaps = (int) coords.entrySet().stream().filter(v -> v.getCount() > 1).count();
			return overlaps;
		}
	}

	private List<Coord> diagCoords(int x1, int x2, int y1, int y2)
	{
		List<Coord> coords = new ArrayList<>();

		int xStep = 1;
		if (x2 < x1)
			xStep = -1;

		int yStep = 1;
		if (y2 < y1)
			yStep = -1;

		for (int x = x1, y = y1; x != x2; x += xStep, y += yStep)
			coords.add(new Coord(x, y));
		coords.add(new Coord(x2, y2));

		return coords;
	}

	record Coord(int x, int y)
	{
	}
}
