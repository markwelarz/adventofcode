package advent2022;

import java.io.IOException;
import java.util.Objects;

import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphBuilder;

import com.google.common.collect.Iterables;
import com.google.common.io.CharSource;

public class Day12
{
	public int part1(CharSource input) throws IOException
	{
		DefaultDirectedGraph<Point, DefaultEdge> paths = graph(input);
		Point start = Iterables.find(paths.vertexSet(), v -> v.start);
		Point end = Iterables.find(paths.vertexSet(), v -> v.end);
		BFSShortestPath<Point, DefaultEdge> shortestPath = new BFSShortestPath<>(paths);
		var path = shortestPath.getPath(start, end);
		return path.getLength();
	}

	public int part2(CharSource input) throws IOException
	{
		DefaultDirectedGraph<Point, DefaultEdge> paths = graph(input);
		Point end = Iterables.find(paths.vertexSet(), v -> v.end);
		int minSteps = paths.vertexSet()
				.stream()
				.filter(v -> v.height == 'a')
				.mapToInt(v-> stepsFrom(paths, v, end))
				.min()
				.getAsInt();

		return minSteps;
	}

	int stepsFrom(DefaultDirectedGraph<Point, DefaultEdge> paths, Point start, Point end)
	{
		BFSShortestPath<Point, DefaultEdge> shortestPath = new BFSShortestPath<>(paths);
		var path = shortestPath.getPath(start, end);
		if (path == null)
			return Integer.MAX_VALUE;
		else
			return path.getLength();
	}

	DefaultDirectedGraph<Point, DefaultEdge> graph(CharSource input) throws IOException
	{
		var grid = read(input);
		var graphBuilder = DefaultDirectedGraph.<Point, DefaultEdge>createBuilder(DefaultEdge.class);

		for(int y=0; y<grid.length; y++)
		{
			for(int x=0; x<grid[y].length; x++)
				graphBuilder.addVertex(grid[y][x]);
		}
		
		for(int y=0; y<grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				safeAddEdgeHeightCheck(graphBuilder, grid, y, x, y, x + 1); // right
				safeAddEdgeHeightCheck(graphBuilder, grid, y, x, y, x - 1); // left
				safeAddEdgeHeightCheck(graphBuilder, grid, y, x, y + 1, x); // down
				safeAddEdgeHeightCheck(graphBuilder, grid, y, x, y - 1, x); // up
			}
		}
		return graphBuilder.build();
	}

	void safeAddEdgeHeightCheck(GraphBuilder<Point, DefaultEdge, ? extends DefaultDirectedGraph<Point, DefaultEdge>> graphBuilder, Point[][] grid, int y1,
			int x1, int y2, int x2)
	{
		try
		{
			Objects.checkIndex(y1, grid.length);
			Objects.checkIndex(y2, grid.length);
			Objects.checkIndex(x1, grid[y1].length);
			Objects.checkIndex(x2, grid[y2].length);
			if (grid[y2][x2].height - grid[y1][x1].height <= 1)
				graphBuilder.addEdge(grid[y1][x1], grid[y2][x2]);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("not adding " + y1 + " " + x1 + " " + y2 + " " + x2);
		}
	}

	Point[][] read(CharSource input) throws IOException
	{
		var lines = input.readLines();

		Point[][] grid = new Point[lines.size()][lines.get(0).length()];
		int lineNumber = 0;

		for (String line : lines)
		{
			var chars = line.toCharArray();
			int charNumber = 0;
			for (char ch : chars)
			{
				char adjustedCh = switch (ch)
				{
					case 'S' -> 'a';
					case 'E' -> 'z';
					default -> ch;
				};

				grid[lineNumber][charNumber] = new Point(adjustedCh, lineNumber, charNumber, ch == 'S', ch == 'E');
				charNumber++;
			}
			lineNumber++;
		}
		return grid;
	}

	record Point(char height, int y, int x, boolean start, boolean end)
	{
	}
}
