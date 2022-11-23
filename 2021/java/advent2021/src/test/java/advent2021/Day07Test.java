package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day07Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				16,1,2,0,4,2,7,1,2,14
				""";

		var day7 = new Day07();
		int answer = day7.part(CharSource.wrap(input), true);
		assertThat(answer).isEqualTo(37);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day7 = new Day07();
		CharSource input = Files.asCharSource(new ClassPathResource("day07input.txt").getFile(), Charsets.UTF_8);
		int answer = day7.part(input, true);
		assertThat(answer).isEqualTo(328187);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				16,1,2,0,4,2,7,1,2,14
				""";

		var day7 = new Day07();
		int answer = day7.part(CharSource.wrap(input), false);
		assertThat(answer).isEqualTo(168);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day7 = new Day07();
		CharSource input = Files.asCharSource(new ClassPathResource("day07input.txt").getFile(), Charsets.UTF_8);
		int answer = day7.part(input, false);
		assertThat(answer).isEqualTo(91257582);
	}
}
