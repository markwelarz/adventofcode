package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashBiMap;
import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;
import com.google.common.math.Quantiles;

public class Day10
{
	public int part1(CharSource input) throws IOException
	{
		return input.readLines(new InvaldSyntaxErrorScorer());
	}

	public long part2(CharSource input) throws IOException
	{
		var scores = input.readLines(new AutoCompleteScorer());
		return (long) Quantiles.median().compute(scores);
	}
}

class AutoCompleteScorer implements LineProcessor<List<Long>>
{
	private HashBiMap<Character, Character> matchingPairs = HashBiMap.create(Map.of('(', ')', '[', ']', '<', '>', '{', '}'));

	private List<Long> scores = new ArrayList<>();

	@Override
	public boolean processLine(String line) throws IOException
	{
		Deque<Character> openChunked = new LinkedList<>();

		for (char ch : line.toCharArray())
		{
			switch (ch)
			{
				case '(', '[', '{', '<' -> openChunked.push(ch);
				case ')', ']', '}', '>' ->
				{
					char lastOpening = openChunked.pop();
					if (lastOpening != matchingPairs.inverse().get(ch))
						return true;
				}
			}
		}

		long lineScore = 0;
		while (!openChunked.isEmpty())
		{
			char ch = openChunked.pop();
			switch (ch)
			{
				case '(' -> lineScore = (lineScore * 5) + 1;
				case '[' -> lineScore = (lineScore * 5) + 2;
				case '{' -> lineScore = (lineScore * 5) + 3;
				case '<' -> lineScore = (lineScore * 5) + 4;
			}
		}

		this.scores.add(lineScore);

		return true;
	}

	@Override
	public List<Long> getResult()
	{
		return this.scores;
	}
}

class InvaldSyntaxErrorScorer implements LineProcessor<Integer>
{
	private HashBiMap<Character, Character> matchingPairs = HashBiMap.create(Map.of('(', ')', '[', ']', '<', '>', '{', '}'));

	private Map<Character, Integer> scores = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

	private int score = 0;

	@Override
	public boolean processLine(String line) throws IOException
	{
		Deque<Character> openChunked = new LinkedList<>();

		for (char ch : line.toCharArray())
		{
			switch (ch)
			{
				case '(', '[', '{', '<' -> openChunked.push(ch);
				case ')', ']', '}', '>' ->
				{
					char lastOpening = openChunked.pop();
					if (lastOpening != matchingPairs.inverse().get(ch))
					{
						score += scores.get(ch);
						return true;
					}
				}
			}
		}

		return true;
	}

	@Override
	public Integer getResult()
	{
		return this.score;
	}
}
