package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.io.CharSource;

import advent2021.Day09.Point;

public class Day11
{
	public int part1(CharSource input, int cycles) throws IOException
	{
		var grid = read(input);
		int flashingCount = 0;

		for (int i = 0; i < cycles; i++)
		{
			increaseLevelsAll(grid);
			increaseAdjacentToFlash(grid);
			flashingCount += resetFlashingTo0(grid);
		}

		return flashingCount;
	}

	public int part2(CharSource input) throws IOException
	{
		boolean allLightsOn = false;
		var grid = read(input);
		int cycle = 0;

		while (!allLightsOn)
		{
			increaseLevelsAll(grid);
			increaseAdjacentToFlash(grid);
			int flashingCount = resetFlashingTo0(grid);
			allLightsOn = flashingCount == grid.length * grid[0].length;
			cycle++;
		}
		return cycle;
	}

	private int resetFlashingTo0(Integer[][] grid)
	{
		int resetCount=0;
		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				if(grid[y][x]==10)
				{
					grid[y][x] = 0;
					resetCount++;
				}
			}
		}
		return resetCount;
	}

	private void increaseAdjacentToFlash(Integer[][] grid)
	{
		Deque<Point> gt9s=allGt9(grid);
		while(!gt9s.isEmpty())
		{
			var flashingPoint = gt9s.pop();
			var pointsToIncrease= this.pointsAround(grid, flashingPoint);
			for (Point p : pointsToIncrease)
			{
				if (grid[p.y()][p.x()] != 10)
				{
					grid[p.y()][p.x()]++;
					if (grid[p.y()][p.x()] == 10)
						gt9s.addLast(p);
				}
			}
		}
	}

	private void increaseLevelsAll(Integer[][] grid)
	{
		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				grid[y][x]++;
			}
		}
	}

	private Deque<Point> allGt9(Integer[][] grid)
	{
		Deque<Point> ps = new LinkedList<>();
		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				if (grid[y][x] == 10)
					ps.add(new Point(y, x));
			}
		}
		return ps;
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

		Point topRight = pointAt(grid, y - 1, x + 1);
		if (topRight != null)
			points.add(topRight);
		Point bottomRight = pointAt(grid, y + 1, x + 1);
		if (bottomRight != null)
			points.add(bottomRight);
		Point topLeft = pointAt(grid, y - 1, x - 1);
		if (topLeft != null)
			points.add(topLeft);
		Point bottomLeft = pointAt(grid, y + 1, x - 1);
		if (bottomLeft != null)
			points.add(bottomLeft);

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
