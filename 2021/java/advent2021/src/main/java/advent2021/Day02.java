package advent2021;

import java.io.IOException;
import java.util.Scanner;

import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;

public class Day02
{
	public int part1(CharSource input) throws IOException
	{
		var p1cc = new Part1CourseCalculator();
		return input.readLines(p1cc);
	}
	
	public int part2(CharSource input) throws IOException
	{
		var p2cc = new Part2CourseCalculator();
		return input.readLines(p2cc);
	}

	class Part1CourseCalculator implements LineProcessor<Integer>
	{
		int horizPosition=0;
		int depth = 0; 
		
		@Override
		public boolean processLine(String line) throws IOException
		{
			try (Scanner s = new Scanner(line))
			{
				String direction =  s.next();
				int amount = s.nextInt();
				
				switch (direction)
				{
					case "forward" -> horizPosition += amount;
					case "down" -> depth += amount;
					case "up" -> depth -= amount;
					default -> throw new IllegalArgumentException(direction);
				}
				
				return true;
			}
		}

		@Override
		public Integer getResult()
		{
			return horizPosition * depth;
		}
	}

	class Part2CourseCalculator implements LineProcessor<Integer>
	{
		int horizPosition = 0;
		int depth = 0;
		int aim = 0;

		@Override
		public boolean processLine(String line) throws IOException
		{
			try (Scanner s = new Scanner(line))
			{
				String direction = s.next();
				int amount = s.nextInt();

				switch (direction)
				{
					case "forward" ->
					{
						horizPosition += amount;
						depth += (aim * amount);
					}
					case "down" -> aim += amount;
					case "up" -> aim -= amount;
					default -> throw new IllegalArgumentException(direction);
				}

				return true;
			}
		}

		@Override
		public Integer getResult()
		{
			return horizPosition * depth;
		}
	}
}
