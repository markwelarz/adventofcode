package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day02Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				A Y
				B X
				C Z
				""";

		var sol = new Day02();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(15);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				A Y
				B X
				C Z
				""";

		var sol = new Day02();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(12);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day02();
		CharSource input = Files.asCharSource(new ClassPathResource("day02input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(11386);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day02();
		CharSource input = Files.asCharSource(new ClassPathResource("day02input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(13600);
	}

}
