package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day06Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				3,4,3,1,2
				""";

		var day6 = new Day06();
		int answer = day6.part1(CharSource.wrap(input), 18);
		assertThat(answer).isEqualTo(26);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day6 = new Day06();
		CharSource input = Files.asCharSource(new ClassPathResource("day06input.txt").getFile(), Charsets.UTF_8);
		int answer = day6.part1(input, 80);
		assertThat(answer).isEqualTo(360761);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				3,4,3,1,2
				""";

		var day6 = new Day06();
		long answer = day6.part2(CharSource.wrap(input), 256);
		assertThat(answer).isEqualTo(26984457539L);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day6 = new Day06();
		CharSource input = Files.asCharSource(new ClassPathResource("day06input.txt").getFile(), Charsets.UTF_8);
		long answer = day6.part2(input, 256);
		assertThat(answer).isEqualTo(1632779838045L);
	}
}
