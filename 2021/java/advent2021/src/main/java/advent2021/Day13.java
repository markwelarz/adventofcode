package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.io.CharSource;

public class Day13
{
	public int part1(CharSource input) throws IOException
	{
		var instructions = read(input);
		return fold(instructions.dots(), instructions.folds().subList(0, 1));
	}

	public int part2(CharSource input) throws IOException
	{
		var instructions = read(input);
		int dotCount = fold(instructions.dots(), instructions.folds());

		drawDots(instructions.dots());

		return dotCount;
	}

	private void drawDots(Set<Coord> coords)
	{
		int maxx = coords.stream().mapToInt(v -> v.x()).max().getAsInt();
		int maxy = coords.stream().mapToInt(v -> v.y()).max().getAsInt();

		for (int y = 0; y <= maxy; y++)
		{
			for (int x = 0; x <= maxx; x++)
			{
				if (coords.contains(new Coord(x, y)))
					System.out.print("#");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	private int fold(Set<Coord> dots, List<Fold> folds)
	{
		for (Fold fold : folds)
		{
			if (fold.xy.equals("x"))
			{
				List<Coord> foldedLeftCoords = dots.stream()
						.filter(c -> c.x > fold.position())
						.toList();

				for (Coord c : foldedLeftCoords)
				{
					int newX = 2 * fold.position() - c.x();
					dots.remove(c);
					dots.add(new Coord(newX, c.y()));
				}
			}
			else if (fold.xy.equals("y"))
			{
				List<Coord> foldedUpCoords = dots.stream()
						.filter(c -> c.y > fold.position())
						.toList();

				for (Coord c : foldedUpCoords)
				{
					int newY = 2 * fold.position() - c.y();
					dots.remove(c);
					dots.add(new Coord(c.x(), newY));
				}
			}
			else
			{
				throw new IllegalStateException();
			}
		}

		return dots.size();
	}

	Instructions read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			Set<Coord> coords = new HashSet<>();
			List<Fold> folds = new ArrayList<>();

			sc.useDelimiter(",");

			while (sc.hasNextInt())
			{
				String line = sc.nextLine();
				String[] numPair = StringUtils.split(line, ",");
				coords.add(new Coord(Integer.parseInt(numPair[0]), Integer.parseInt(numPair[1])));
			}

			sc.nextLine();

			while (sc.hasNextLine())
			{
				String fold = StringUtils.substringAfterLast(sc.nextLine(), " ");
				String axis = StringUtils.substringBefore(fold, "=");
				int pos = Integer.parseInt(StringUtils.substringAfter(fold, "="));

				folds.add(new Fold(axis, pos));
			}

			return new Instructions(coords, folds);
		}
	}

	record Instructions(Set<Coord> dots, List<Fold> folds)
	{
	}

	record Fold(String xy, int position)
	{
	}

	record Coord(int x, int y)
	{
	}
}
