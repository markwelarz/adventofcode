package advent2022;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import com.google.common.collect.TreeBasedTable;
import com.google.common.io.CharSource;

public class Day08
{
	public long part1(CharSource input) throws IOException
	{
		TreeBasedTable<Integer, Integer, VisibleFrom> grid = read(input);
		
		for (int rowNumber : grid.rowKeySet())
		{
			calculateVisibles(grid.row(rowNumber), (visibleFrom, v) -> visibleFrom.fromLeft = v);
			calculateVisibles(reverse(grid.row(rowNumber)), (visibleFrom, v) -> visibleFrom.fromRight = v);
		}

		for (int colNumber : grid.columnKeySet())
		{
			calculateVisibles(grid.column(colNumber), (visibleFrom, v) -> visibleFrom.fromTop = v);
			calculateVisibles(reverse(grid.column(colNumber)), (visibleFrom, v) -> visibleFrom.fromBottom = v);
		}

		return grid.values().stream()
				.filter(VisibleFrom::visibleFromAnySide)
				.count();
	}

	public long part2(CharSource input) throws IOException
	{
		TreeBasedTable<Integer, Integer, VisibleFrom> grid = read(input);
		int bestScenicScore = 0;

		for (var e : grid.cellSet())
		{
			int thisScenicScore;
			int rowNumber = e.getRowKey();
			int colNumber = e.getColumnKey();

			// look right
			thisScenicScore = viewingDistance(grid.row(rowNumber).tailMap(colNumber + 1), e.getValue().height);
			// look left
			thisScenicScore *= viewingDistance(reverse(grid.row(rowNumber).headMap(colNumber)), e.getValue().height);
			// look down
			thisScenicScore *= viewingDistance(new TreeMap<>(grid.column(colNumber)).tailMap(rowNumber + 1), e.getValue().height);
			// look up
			thisScenicScore *= viewingDistance(reverse(new TreeMap<>(grid.column(colNumber)).headMap(rowNumber)), e.getValue().height);

			bestScenicScore = Math.max(thisScenicScore, bestScenicScore);
		}
		return bestScenicScore;
	}

	private int viewingDistance(Map<Integer, VisibleFrom> line, int startTreeHeight)
	{
		int canSeeTrees = 0;
		var it = line.entrySet().iterator();
		boolean done = false;
		while (it.hasNext() && !done)
		{
			var tree = it.next();
			done = tree.getValue().height >= startTreeHeight;
			canSeeTrees++;
		}

		return canSeeTrees;
	}

	private void calculateVisibles(Map<Integer, VisibleFrom> line, BiConsumer<VisibleFrom, Boolean> visibleSetter)
	{
		int highestInRow = -1;

		for (var e : line.entrySet())
		{
			if (e.getValue().height > highestInRow)
			{
				highestInRow = e.getValue().height;
				visibleSetter.accept(e.getValue(), true);
			}
		}
	}

	private Map<Integer, VisibleFrom> reverse(Map<Integer, VisibleFrom> in)
	{
		SortedMap<Integer, VisibleFrom> reversed = new TreeMap<>(Collections.reverseOrder());
		reversed.putAll(in);
		return reversed;
	}

	TreeBasedTable<Integer, Integer, VisibleFrom> read(CharSource input) throws IOException
	{
		TreeBasedTable<Integer, Integer, VisibleFrom> grid = TreeBasedTable.create();
		var lines = input.readLines();

		int lineNumber = 0;
		for (String line : lines)
		{
			var chars = line.toCharArray();
			int charNumber = 0;
			for (char ch : chars)
			{
				grid.put(lineNumber, charNumber, new VisibleFrom(Character.digit(ch, 10)));

				charNumber++;
			}
			lineNumber++;
		}
		return grid;
	}

	class VisibleFrom
	{
		int height;
		boolean fromLeft;
		boolean fromTop;
		boolean fromRight;
		boolean fromBottom;

		VisibleFrom(int height)
		{
			this.height = height;
		}

		boolean visibleFromAnySide()
		{
			return fromLeft || fromTop || fromRight || fromBottom;
		}

		@Override
		public String toString()
		{
			return "VisibleFrom [height=" + height + ", fromLeft=" + fromLeft + ", fromTop=" + fromTop + ", fromRight=" + fromRight + ", fromBottom="
					+ fromBottom + "]";
		}

	}
}
