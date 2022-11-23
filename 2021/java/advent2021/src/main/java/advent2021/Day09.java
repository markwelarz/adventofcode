package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.io.CharSource;

public class Day09
{
	public int part1(CharSource input) throws IOException
	{
		var grid = read(input);
		int riskLevels = 0;

		List<Point> lowPoints = this.lowPoints(grid);
		for (Point p : lowPoints)
		{
			int height = grid[p.y()][p.x()];
			riskLevels += height + 1;
		}

		return riskLevels;
	}

	public int part2(CharSource input) throws IOException
	{
		var grid = read(input);
		List<Point> lowPoints = this.lowPoints(grid);
		List<Set<Point>> basins = new ArrayList<>();

		for (Point p : lowPoints)
		{
			basins.add(basin(grid, p));
		}

		int product = basins.stream()
				.map(v -> v.size())
				.sorted(Comparator.<Integer>naturalOrder().reversed())
				.limit(3)
				.reduce(1, (a, b) -> a * b);

		return product;
	}

	private Set<Point> basin(Integer[][] grid, Point startAt)
	{
		Deque<Point> toInspect = new LinkedList<>();
		Set<Point> partOfBasin = new HashSet<>();
		
		toInspect.add(startAt);
		while (!toInspect.isEmpty())
		{
			Point point = toInspect.removeLast();
			partOfBasin.add(point);

			for (var nextPoint : pointsAround(grid, point))
			{
				if (grid[nextPoint.y()][nextPoint.x()] < 9)
				{
					if (!toInspect.contains(nextPoint) && !partOfBasin.contains(nextPoint))
						toInspect.add(nextPoint);
				}
			}
		}

		return partOfBasin;
	}

	record Point(int y, int x)
	{
	}

	private List<Point> lowPoints(Integer[][] grid)
	{
		List<Point> points = new ArrayList<>();
		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				int height = grid[y][x];
				int above = heightAt(grid, y - 1, x);
				int below = heightAt(grid, y + 1, x);
				int left = heightAt(grid, y, x - 1);
				int right = heightAt(grid, y, x + 1);

				if (height < above && height < below && height < left && height < right)
				{
					points.add(new Point(y, x));
				}
			}
		}
		return points;
	}

	private int heightAt(Integer[][] grid, int y, int x)
	{
		Integer[] row = ArrayUtils.get(grid, y, ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
		Integer value = ArrayUtils.get(row, x, 10);
		return value;
	}

	private Point pointAt(Integer[][] grid, int y, int x)
	{
		if (y >= 0 && y < grid.length)
		{
			if (x >= 0 && x < grid[y].length)
				return new Point(y, x);
		}
		return null;
	}

	private List<Point> pointsAround(Integer[][] grid, Point p)
	{
		List<Point> points = new ArrayList<>();

		int y = p.y();
		int x = p.x();

		Point above = pointAt(grid, y - 1, x);
		if (above != null)
			points.add(above);
		Point below = pointAt(grid, y + 1, x);
		if (below != null)
			points.add(below);
		Point left = pointAt(grid, y, x - 1);
		if (left != null)
			points.add(left);
		Point right = pointAt(grid, y, x + 1);
		if (right != null)
			points.add(right);

		return points;
	}

	Integer[][] read(CharSource input) throws IOException
	{
		var lines = input.readLines();

		Integer[][] grid = new Integer[lines.size()][lines.get(0).length()];
		int lineNumber = 0;

		for (String line : lines)
		{
			var chars = line.toCharArray();
			int charNumber = 0;
			for (char ch : chars)
			{
				grid[lineNumber][charNumber] = Character.digit(ch, 10);
				charNumber++;
			}
			lineNumber++;
		}
		return grid;
	}
}
