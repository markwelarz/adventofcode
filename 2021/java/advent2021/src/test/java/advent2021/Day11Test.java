package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day11Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				11111
				19991
				19191
				19991
				11111
				""";

		var sol = new Day11();
		int answer = sol.part1(CharSource.wrap(input), 2);
		assertThat(answer).isEqualTo(9);
	}

	@Test
	public void part1Test2() throws Exception
	{
		var input = """
				5483143223
				2745854711
				5264556173
				6141336146
				6357385478
				4167524645
				2176841721
				6882881134
				4846848554
				5283751526
				""";

		var sol = new Day11();
		int answer = sol.part1(CharSource.wrap(input), 10);
		assertThat(answer).isEqualTo(204);
	}

	@Test
	public void part1Test3() throws Exception
	{
		var input = """
				5483143223
				2745854711
				5264556173
				6141336146
				6357385478
				4167524645
				2176841721
				6882881134
				4846848554
				5283751526
				""";

		var sol = new Day11();
		int answer = sol.part1(CharSource.wrap(input), 100);
		assertThat(answer).isEqualTo(1656);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				5483143223
				2745854711
				5264556173
				6141336146
				6357385478
				4167524645
				2176841721
				6882881134
				4846848554
				5283751526
				""";

		var sol = new Day11();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(329);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day11();
		CharSource input = Files.asCharSource(new ClassPathResource("day11input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input, 100);
		assertThat(answer).isEqualTo(1627);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day11();
		CharSource input = Files.asCharSource(new ClassPathResource("day11input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(1627);
	}
}
