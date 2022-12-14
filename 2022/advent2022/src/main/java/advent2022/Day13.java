package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.lang3.ArrayUtils;

import com.google.common.io.CharSource;

public class Day13
{
	public int part1(CharSource input) throws IOException
	{
		return this.read(input).stream()
				.filter(v -> comp(v.left, v.right) == Result.ORDERED)
				.mapToInt(Pair::index)
				.sum();
	}

	public int part2(CharSource input) throws IOException
	{
		var pairs = read(input);
		
		Object divider1= new Object[]{ new Object[]{ 2 } };
		Object divider2 = new Object[]{ new Object[]{ 6 } };
		
		pairs.add(new Pair(1000000, divider1, divider2));

		var sorted = pairs.stream()
				.mapMulti((c, a) -> {
					a.accept(c.left);
					a.accept(c.right);
				})
				.sorted((a, b) -> comp(a, b).toComparatorInt())
				.toList();

		int pos1 = sorted.indexOf(divider1) + 1;
		int pos2 = sorted.indexOf(divider2) + 1;
		
		return pos1 * pos2;
	}
	
	Result comp(Object left, Object right)
	{
		System.out.println("left " + left + ", right: " + right);

		if (this.isIntArray(left))
		{
			System.out.println("converting left from primitive-array to object-array");
			return comp(ArrayUtils.toObject((int[]) left), right);
		}
		else if (this.isIntArray(right))
		{
			System.out.println("converting right from primitive-array to object-array");
			return comp(left, ArrayUtils.toObject((int[]) right));
		}
		else if (left.getClass().isArray() && !right.getClass().isArray())
		{
			System.out.println("converting right from single to object-array");
			return comp((Object[]) left, new Object[]
			{ right });
		}
		else if (!left.getClass().isArray() && right.getClass().isArray())
		{
			System.out.println("converting left from single to object-array");
			return comp(new Object[]
			{ left }, right);
		}
		else if (left.getClass().equals(Integer.class) && right.getClass().equals(Integer.class))
		{
			System.out.println("comparing singles");
			return comp((int) left, (int) right);
		}
		else if (isObjectArray(left) && isObjectArray(right))
		{
			System.out.println("comparing arrays");
			return comp((Object[]) left, (Object[]) right);
		}
		else
		{
			System.out.println("left isIntArray " + isIntArray(left));
			System.out.println("left isObjectArray " + isObjectArray(left));
			System.out.println("left is int" + left.getClass().equals(int.class));
			System.out.println("left is Integer " + left.getClass().equals(Integer.class));
			System.out.println("right isIntArray " + isIntArray(right));
			System.out.println("right isObjectArray " + isObjectArray(right));
			System.out.println("right is int " + right.getClass().equals(int.class));
			System.out.println("right is Integer " + right.getClass().equals(Integer.class));

			throw new IllegalArgumentException("left: " + left + ", right: " + right);
		}
	}

	Result comp(Object[] left, Object[] right)
	{
		int i = 0;

		while (i < left.length && i < right.length)
		{
			Result result = comp(left[i], right[i]);
			if (result != Result.EQUAL)
				return result;
			i++;
		}

		if (left.length < right.length)
			return Result.ORDERED;
		else if (left.length > right.length)
			return Result.UNORDERED;
		else
			return Result.EQUAL;
	}

	Result comp(int left, int right)
	{
		if (left < right)
			return Result.ORDERED;
		else if (left > right)
			return Result.UNORDERED;
		else
			return Result.EQUAL;
	}

	boolean isIntArray(Object object)
	{
		return object.getClass().isArray() && object.getClass().getComponentType() == Integer.TYPE;
	}

	boolean isObjectArray(Object object)
	{
		return object.getClass().isArray() && object.getClass().getComponentType() != Integer.TYPE;
	}

	List<Pair> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			List<Pair> pairs = new ArrayList<>();
			JexlEngine jexl = new JexlBuilder().create();
			JexlContext context = new MapContext();
			while (sc.hasNextLine())
			{
				String line1 = sc.nextLine();
				JexlExpression leftExpression = jexl.createExpression(line1);

				String line2 = sc.nextLine();
				JexlExpression rightExpression = jexl.createExpression(line2);

				Object left = leftExpression.evaluate(context);
				Object right = rightExpression.evaluate(context);

				pairs.add(new Pair(pairs.size() + 1, left, right));

				sc.nextLine();
			}

			return pairs;
		}
	}

	record Pair(int index, Object left, Object right)
	{}

	enum Result
	{
		ORDERED, UNORDERED, EQUAL;

		int toComparatorInt()
		{
			return switch (this)
			{
				case ORDERED -> -1;
				case EQUAL -> 0;
				case UNORDERED -> 1;
			};
		}
	}
}
