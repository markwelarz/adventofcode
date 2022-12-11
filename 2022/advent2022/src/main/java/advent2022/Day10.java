package advent2022;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;

public class Day10
{
	public int part1(CharSource input) throws IOException
	{
		return input.readLines(new Register());
	}

	public int part2(CharSource input) throws IOException
	{
		return input.readLines(new Register2());
	}

	class Register implements LineProcessor<Integer>
	{
		int cycle = 0;
		int value = 1;
		int signalStrengthSum = 0;

		@Override
		public boolean processLine(String line) throws IOException
		{
			if (line.equals("noop"))
			{
				cycle();
			}
			else if (line.startsWith("addx"))
			{
				cycle();
				cycle();

				int arg = Integer.parseInt(StringUtils.substringAfter(line, " "));
				value += arg;
			}

			return true;
		}

		private void cycle()
		{
			cycle++;
			if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220)
			{
				takeSignalStrength(cycle);
			}
		}

		private void takeSignalStrength(int cycleToUse)
		{
			signalStrengthSum += (cycleToUse * value);
		}

		@Override
		public Integer getResult()
		{
			return signalStrengthSum;
		}
	}

	class Register2 implements LineProcessor<Integer>
	{
		int cycle = 0;
		int value = 1;
		int signalStrengthSum = 0;

		@Override
		public boolean processLine(String line) throws IOException
		{
			if (line.equals("noop"))
			{
				cycle();
			}
			else if (line.startsWith("addx"))
			{
				cycle();
				cycle();

				int arg = Integer.parseInt(StringUtils.substringAfter(line, " "));
				value += arg;
			}

			return true;
		}

		private void cycle()
		{
			if (value == (cycle % 40) || value == (cycle % 40) - 1 || value == (cycle % 40) + 1)
				System.out.print("#");
			else
				System.out.print(".");

			cycle++;
			if (cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 || cycle == 240)
				System.out.println();
		}

		@Override
		public Integer getResult()
		{
			return 0;
		}
	}
}
