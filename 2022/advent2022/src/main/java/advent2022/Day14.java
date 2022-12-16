package advent2022;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.io.CharSource;

public class Day14
{
	Coord drop = new Coord(500, 0);

	public int part1(CharSource input) throws IOException
	{
		Set<Coord> rockCoords = read(input);
		int abyssY = rockCoords.stream().mapToInt(v -> v.y).max().getAsInt() + 1;

		int grains = 0;
		while (!dropSand(rockCoords, abyssY))
			grains++;

		return grains;
	}

	public int part2(CharSource input) throws IOException
	{
		Set<Coord> rockCoords = read(input);
		int abyssY = rockCoords.stream().mapToInt(v -> v.y).max().getAsInt() + 2;

		IntStream.rangeClosed(0, 1000).mapToObj(x -> new Coord(x, abyssY)).forEach(rockCoords::add);

		int grains = 0;
		while (!rockCoords.contains(drop))
		{
			dropSand(rockCoords, abyssY); // will never reach abyssY
			grains++;
		}

		return grains;
	}

	boolean dropSand(Set<Coord> rock, int abyssY)
	{
		boolean stopped = false;
		Coord pos = drop;
		while (!stopped)
		{
			var down = pos.down();
			var downLeft = pos.downLeft();
			var downRight = pos.downRight();
			if (!rock.contains(down) && down.y == abyssY)
				return true;
			if (!rock.contains(down))
				pos = down;
			else if (!rock.contains(downLeft))
				pos = downLeft;
			else if (!rock.contains(downRight))
				pos = downRight;
			else
				stopped = true;
		}

		rock.add(pos);

		return false;
	}

	Set<Coord> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			Set<Coord> allRock = new HashSet<>();

			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				var points = Splitter.on(" -> ").splitToStream(line)
						.map(v -> StringUtils.split(v, ","))
						.map(v -> new Coord(Integer.parseInt(v[0]), Integer.parseInt(v[1])))
						.toList();

				for (int i = 1; i < points.size(); i++)
				{
					var lineOfPoints = points.get(i - 1).fillLine(points.get(i));
					allRock.addAll(lineOfPoints);
				}
			}
			return allRock;
		}
	}

	record Coord(int x, int y)
	{
		Set<Coord> fillLine(Coord to)
		{
			Set<Coord> line = new HashSet<>();

			int fromx = Math.min(x, to.x);
			int tox = Math.max(x, to.x);
			int fromy = Math.min(y, to.y);
			int toy = Math.max(y, to.y);

			for (int x1 = fromx; x1 <= tox; x1++)
			{
				for (int y1 = fromy; y1 <= toy; y1++)
				{
					line.add(new Coord(x1, y1));
				}
			}
			return line;
		}

		Coord down()
		{
			return new Coord(x, y + 1);
		}

		Coord downLeft()
		{
			return new Coord(x - 1, y + 1);
		}

		Coord downRight()
		{
			return new Coord(x + 1, y + 1);
		}
	}

	private void drawDots(Set<Coord> coords)
	{
		int maxx = coords.stream().mapToInt(Coord::x).max().getAsInt();
		int maxy = coords.stream().mapToInt(Coord::y).max().getAsInt();

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
}
