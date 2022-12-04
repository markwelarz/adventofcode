package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day04Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				2-4,6-8
				2-3,4-5
				5-7,7-9
				2-8,3-7
				6-6,4-6
				2-6,4-8
				""";

		var sol = new Day04();
		long answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(2);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				2-4,6-8
				2-3,4-5
				5-7,7-9
				2-8,3-7
				6-6,4-6
				2-6,4-8
				""";

		var sol = new Day04();
		long answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(4);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day04();
		CharSource input = Files.asCharSource(new ClassPathResource("day04input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part1(input);
		assertThat(answer).isEqualTo(496);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day04();
		CharSource input = Files.asCharSource(new ClassPathResource("day04input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part2(input);
		assertThat(answer).isEqualTo(847);
	}
}
