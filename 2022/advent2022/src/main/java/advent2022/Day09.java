package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.math3.util.MathUtils;

import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;

public class Day09
{
	public int part1(CharSource input) throws IOException
	{
		return input.readLines(new KnottedRope(2));
	}

	public int part2(CharSource input) throws IOException
	{
		return input.readLines(new KnottedRope(10));
	}

	class KnottedRope implements LineProcessor<Integer>
	{
		List<Coord> knots = new ArrayList<>();
		Set<Coord> tMoves = new HashSet<>();

		public KnottedRope(int knotCount)
		{
			for (int i = 0; i < knotCount; i++)
				knots.add(new Coord(0, 0));
			tMoves.add(new Coord(0, 0));
		}

		@Override
		public boolean processLine(String line) throws IOException
		{
			String[] pairs = StringUtils.split(line, " ");
			switch (pairs[0])
			{
				case "R" -> right(Integer.parseInt(pairs[1]));
				case "U" -> up(Integer.parseInt(pairs[1]));
				case "L" -> left(Integer.parseInt(pairs[1]));
				case "D" -> down(Integer.parseInt(pairs[1]));
				default -> throw new IllegalArgumentException(line);
			};

			return true;
		}

		void left(int delta)
		{
			for (int i = 1; i <= delta; i++)
				moveOne(-1, 0);
		}

		void right(int delta)
		{
			for (int i = 1; i <= delta; i++)
				moveOne(1, 0);
		}

		void up(int delta)
		{
			for (int i = 1; i <= delta; i++)
				moveOne(0, -1);
		}

		void down(int delta)
		{
			for (int i = 1; i <= delta; i++)
				moveOne(0, 1);
		}

		void moveOne(int deltax, int deltay)
		{
			Validate.inclusiveBetween(-1, 1, deltax);
			Validate.inclusiveBetween(-1, 1, deltay);

			var he = knots.get(0);
			he = he.move(deltax, deltay);
			knots.set(0, he);

			for (int secondKnot = 1; secondKnot < knots.size(); secondKnot++)
			{
				var t0 = knots.get(secondKnot - 1);
				var t1 = knots.get(secondKnot);

				if (!t0.touching(t1))
				{
					if (t0.x() == t1.x())
					{
						// move towards y
						t1 = t1.move(0, MathUtils.copySign(1, t0.y() - t1.y()));
					}
					else if (t0.y() == t1.y())
					{
						// move towards x
						t1 = t1.move(MathUtils.copySign(1, t0.x() - t1.x()), 0);
					}
					else
					{
						// move towards x and y
						t1 = t1.move(MathUtils.copySign(1, t0.x() - t1.x()),
								MathUtils.copySign(1, t0.y() - t1.y()));
					}

					knots.set(secondKnot, t1);

					if (secondKnot == knots.size() - 1)
						tMoves.add(t1);
				}
			}
		}

		@Override
		public Integer getResult()
		{
			return tMoves.size();
		}
	}

	record Coord(int x, int y)
	{
		boolean touching(Coord other)
		{
			int xdiff = Math.abs(this.x - other.x);
			int ydiff = Math.abs(this.y - other.y);

			return ((xdiff == 0 || xdiff == 1) && (ydiff == 0 || ydiff == 1));
		}

		Coord move(int deltax, int deltay)
		{
			return new Coord(x + deltax, y + deltay);
		}
	}
}
