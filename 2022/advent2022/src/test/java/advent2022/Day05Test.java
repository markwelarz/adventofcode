package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day05Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				    [D]
				[N] [C]
				[Z] [M] [P]
				 1   2   3

				move 1 from 2 to 1
				move 3 from 1 to 3
				move 2 from 2 to 1
				move 1 from 1 to 2
				""";

		var sol = new Day05();
		String answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo("CMZ");
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				    [D]
				[N] [C]
				[Z] [M] [P]
				 1   2   3

				move 1 from 2 to 1
				move 3 from 1 to 3
				move 2 from 2 to 1
				move 1 from 1 to 2
				""";

		var sol = new Day05();
		String answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo("MCD");
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day05();
		CharSource input = Files.asCharSource(new ClassPathResource("day05input.txt").getFile(), Charsets.UTF_8);
		String answer = sol.part1(input);
		assertThat(answer).isEqualTo("BWNCQRMDB");
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day05();
		CharSource input = Files.asCharSource(new ClassPathResource("day05input.txt").getFile(), Charsets.UTF_8);
		String answer = sol.part2(input);
		assertThat(answer).isEqualTo("NHWZCBNBF");
	}

}
