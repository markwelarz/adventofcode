package advent2021;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.google.common.collect.Iterables;
import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;

public class Day03
{
	public int part1(CharSource input) throws IOException
	{
		var dr = new PowerConsumption();
		return input.readLines(dr);
	}

	public int part2(CharSource input) throws IOException
	{
		List<String> numbers = input.readLines();

		String oxygenRating = findOxygenRating(numbers);
		String co2Rating = findCo2Rating(numbers);

		return Integer.parseInt(oxygenRating, 2) * Integer.parseInt(co2Rating, 2);
	}

	private String findOxygenRating(List<String> numbers)
	{
		List<String> filteredNums = numbers;
		for (int charNumber = 0; charNumber < filteredNums.get(0).length() && filteredNums.size() > 1; charNumber++)
		{
			int finalCharNumber = charNumber;
			char mostCommon = mostCommonFor(filteredNums, finalCharNumber);
			filteredNums = filteredNums.stream()
					.filter(v -> charIsSet(v, finalCharNumber, mostCommon))
					.toList();
		}

		return Iterables.getOnlyElement(filteredNums);
	}

	private String findCo2Rating(List<String> numbers)
	{
		List<String> filteredNums = numbers;
		for (int charNumber = 0; charNumber < filteredNums.get(0).length() && filteredNums.size() > 1; charNumber++)
		{
			int finalCharNumber = charNumber;
			char leastCommon = leastCommonFor(filteredNums, finalCharNumber);
			filteredNums = filteredNums.stream()
					.filter(v -> charIsSet(v, finalCharNumber, leastCommon))
					.toList();
		}

		return Iterables.getOnlyElement(filteredNums);
	}

	private boolean charIsSet(String number, int charNumber, char value)
	{
		return number.charAt(charNumber) == value;
	}

	private char mostCommonFor(List<String> numbers, int charNumber)
	{
		int zeros = 0;
		int ones = 0;
		for (var num : numbers)
		{
			if (num.charAt(charNumber) == '1')
				ones++;
			else
				zeros++;
		}

		if (zeros == ones)
			return '1';
		else if (ones > zeros)
			return '1';
		else
			return '0';
	}

	private char leastCommonFor(List<String> numbers, int charNumber)
	{
		char ch = mostCommonFor(numbers, charNumber);
		if (ch == '1')
			return '0';
		else if (ch == '0')
			return '1';
		else
			throw new IllegalStateException();
	}

	class PowerConsumption implements LineProcessor<Integer>
	{
		int lineCount = 0;
		// key = bit number, value = number of 1s
		Map<Integer, Integer> onesCount = new TreeMap<>();

		@Override
		public boolean processLine(String line) throws IOException
		{
			int numberOfBits = line.length();

			try (Scanner s = new Scanner(line))
			{
				String binaryNumber = s.next();

				for (int i = 0; i < binaryNumber.length(); i++)
				{
					int bitNumber = numberOfBits - i - 1;

					char ch = binaryNumber.charAt(i);
					if (ch == '1')
						onesCount.merge(bitNumber, 1, (v1, v2) -> v1 + 1);
				}

				lineCount++;

				return true;
			}
		}

		@Override
		public Integer getResult()
		{
			int bitSize = onesCount.keySet().stream().mapToInt(v -> v).max().getAsInt() + 1;
			int gamma = 0;

			for (var e : onesCount.entrySet())
			{
				int bitNumber = e.getKey();

				if (e.getValue() > lineCount / 2)
				{
					gamma |= (1 << bitNumber);
				}
			}

			int mask = maskFor(bitSize);
			int epislon = ~(gamma | mask);

			return gamma * epislon;
		}

		private int maskFor(int bitSize)
		{
			int mask = 0;
			for (int i = 0; i < bitSize; i++)
				mask |= 1 << i;

			return ~mask;
		}
	}
}
