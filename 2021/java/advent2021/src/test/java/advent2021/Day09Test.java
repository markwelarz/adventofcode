package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day09Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				2199943210
				3987894921
				9856789892
				8767896789
				9899965678
				""";

		var day9 = new Day09();
		int answer = day9.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(15);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				2199943210
				3987894921
				9856789892
				8767896789
				9899965678
				""";

		var day9 = new Day09();
		int answer = day9.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(1134);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day9 = new Day09();
		CharSource input = Files.asCharSource(new ClassPathResource("day09input.txt").getFile(), Charsets.UTF_8);
		int answer = day9.part1(input);
		assertThat(answer).isEqualTo(500);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day9 = new Day09();
		CharSource input = Files.asCharSource(new ClassPathResource("day09input.txt").getFile(), Charsets.UTF_8);
		int answer = day9.part2(input);
		assertThat(answer).isEqualTo(970200);
	}
}
