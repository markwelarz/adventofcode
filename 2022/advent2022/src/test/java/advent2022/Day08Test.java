package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day08Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				30373
				25512
				65332
				33549
				35390
				""";

		var sol = new Day08();
		long answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(21);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				30373
				25512
				65332
				33549
				35390
				""";

		var sol = new Day08();
		long answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(8);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day08();
		CharSource input = Files.asCharSource(new ClassPathResource("day08input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part1(input);
		assertThat(answer).isEqualTo(1827);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day08();
		CharSource input = Files.asCharSource(new ClassPathResource("day08input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part2(input);
		assertThat(answer).isEqualTo(335580);
	}
}
