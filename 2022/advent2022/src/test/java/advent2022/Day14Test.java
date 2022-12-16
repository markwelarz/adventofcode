package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day14Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				498,4 -> 498,6 -> 496,6
				503,4 -> 502,4 -> 502,9 -> 494,9
				""";

		var sol = new Day14();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(24);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				498,4 -> 498,6 -> 496,6
				503,4 -> 502,4 -> 502,9 -> 494,9
				""";

		var sol = new Day14();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(93);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day14();
		CharSource input = Files.asCharSource(new ClassPathResource("day14input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(638);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day14();
		CharSource input = Files.asCharSource(new ClassPathResource("day14input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(-1);
	}

}
