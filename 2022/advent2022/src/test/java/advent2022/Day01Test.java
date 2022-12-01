package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day01Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				1000
				2000
				3000

				4000

				5000
				6000

				7000
				8000
				9000

				10000
				""";

		var sol = new Day01();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(24000);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				1000
				2000
				3000

				4000

				5000
				6000

				7000
				8000
				9000

				10000
				""";

		var sol = new Day01();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(45000);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day01();
		CharSource input = Files.asCharSource(new ClassPathResource("day01input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(71934);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day01();
		CharSource input = Files.asCharSource(new ClassPathResource("day01input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(211447);
	}
}
