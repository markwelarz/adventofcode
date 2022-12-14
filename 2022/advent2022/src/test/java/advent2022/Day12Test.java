package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day12Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				Sabqponm
				abcryxxl
				accszExk
				acctuvwj
				abdefghi
				""";
		var sol = new Day12();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(31);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				Sabqponm
				abcryxxl
				accszExk
				acctuvwj
				abdefghi
				""";
		var sol = new Day12();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(29);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day12();
		CharSource input = Files.asCharSource(new ClassPathResource("day12input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(497);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day12();
		CharSource input = Files.asCharSource(new ClassPathResource("day12input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(492);
	}
}
