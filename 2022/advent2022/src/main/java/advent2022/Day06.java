package advent2022;

import java.io.IOException;

import com.google.common.io.CharSource;

public class Day06
{
	public int part1(CharSource input) throws IOException
	{
		String datastream = input.read();
		return firstNDistinctChars(datastream, 4);
	}

	public int part2(CharSource input) throws IOException
	{
		String datastream = input.read();
		return firstNDistinctChars(datastream, 14);
	}

	private int firstNDistinctChars(String datastream, int numOfUniqueCharsToFind) throws IOException
	{
		int startWindow = 0;
		int endWindow = numOfUniqueCharsToFind;

		while (endWindow <= datastream.length())
		{
			String markerCandidate = datastream.substring(startWindow, endWindow);

			if (markerCandidate.chars().distinct().count() == numOfUniqueCharsToFind)
			{
				return endWindow;
			}

			startWindow++;
			endWindow++;
		}

		return -1;
	}
}
