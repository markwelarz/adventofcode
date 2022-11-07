package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Splitter;
import com.google.common.collect.MoreCollectors;
import com.google.common.io.CharSource;

public class Day04
{
	public int part1(CharSource input) throws IOException
	{
		int score = read(input).stream()
				.sorted(Comparator.comparingInt(v -> v.completeAt))
				.limit(1)
				.map(v -> v.score)
				.collect(MoreCollectors.onlyElement());

		return score;
	}

	public int part2(CharSource input) throws IOException
	{
		int score = read(input).stream()
				.sorted(Comparator.<Board>comparingInt(v -> v.completeAt).reversed())
				.limit(1)
				.map(v -> v.score)
				.collect(MoreCollectors.onlyElement());

		return score;
	}

	private List<Board> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			String numbers = sc.nextLine();
			List<Integer> calledNumbers = Splitter.on(",")
					.splitToStream(numbers)
					.map(v -> Integer.parseInt(v))
					.toList();

			List<Board> boards = new ArrayList<>();

			while (sc.hasNext())
			{
				List<Integer> boardNumbers = new ArrayList<>();

				for (int i = 0; i < 25; i++)
				{
					int read = sc.nextInt();
					boardNumbers.add(read);
				}

				var board = Board.create(boardNumbers, calledNumbers);
				boards.add(board);
			}

			return boards;
		}
	}

	static class Board
	{
		int score;
		int completeAt;

		public Board(int score, int completeAt)
		{
			super();
			this.score = score;
			this.completeAt = completeAt;
		}

		static Board create(List<Integer> boardNumbers, List<Integer> calledNumbers)
		{
			Set<Integer> uncalledNumbers = new HashSet<>(boardNumbers);
			Map<Integer, Integer> rowCalledCount = new HashMap<>();
			Map<Integer, Integer> colCalledCount = new HashMap<>();
			int calledNumber = 0;
			int completeAt = 24;
			
			for (int i = 0; i < calledNumbers.size(); i++)
			{
				calledNumber = calledNumbers.get(i);
				if (boardNumbers.contains(calledNumber) && completeAt == 24)
				{
					int position = boardNumbers.indexOf(calledNumber);

					rowCalledCount.merge(row(position), 1, (v1, v2) -> v1 + 1);
					colCalledCount.merge(col(position), 1, (v1, v2) -> v1 + 1);
					uncalledNumbers.remove(calledNumber);

					if (rowCalledCount.values().contains(5) || colCalledCount.values().contains(5))
					{
						int sumUncalled = uncalledNumbers.stream().mapToInt(v -> v).sum();
						return new Board(sumUncalled * calledNumber, i);
					}
				}
			}

			throw new IllegalStateException("board never wins");
		}

		private static int row(int position)
		{
			return switch (position)
			{
				case 0, 1, 2, 3, 4 -> 0;
				case 5, 6, 7, 8, 9 -> 1;
				case 10, 11, 12, 13, 14 -> 2;
				case 15, 16, 17, 18, 19 -> 3;
				case 20, 21, 22, 23, 24 -> 4;
				default -> throw new IllegalArgumentException("unknown position: " + position);
			};
		}

		private static int col(int position)
		{
			return switch (position)
			{
				case 0, 5, 10, 15, 20 -> 0;
				case 1, 6, 11, 16, 21 -> 1;
				case 2, 7, 12, 17, 22 -> 2;
				case 3, 8, 13, 18, 23 -> 3;
				case 4, 9, 14, 19, 24 -> 4;
				default -> throw new IllegalArgumentException("unknown position: " + position);
			};
		}
	}
}
