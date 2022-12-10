package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.io.CharSource;

public class Day01
{
	public int part1(CharSource input) throws IOException
	{
		var caloriesByElf = read(input);
		return Collections.max(caloriesByElf);
	}

	public int part2(CharSource input) throws IOException
	{
		return read(input).stream()
				.sorted(Comparator.<Integer>naturalOrder().reversed())
				.limit(3)
				.mapToInt(v -> v)
				.sum();
	}

	List<Integer> read(CharSource input) throws IOException
	{
		List<Integer> caloriesByElf = new ArrayList<>();

		int calorieSum = 0;

		for (String line : input.readLines())
		{
			if (line.equals(""))
			{
				caloriesByElf.add(calorieSum);
				calorieSum = 0;
			}
			else
			{
				calorieSum += Integer.parseInt(line);
			}
		}

		caloriesByElf.add(calorieSum);

		return caloriesByElf;
	}
}
