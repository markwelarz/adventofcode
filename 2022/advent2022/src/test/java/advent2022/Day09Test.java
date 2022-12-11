package advent2022;

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
				R 4
				U 4
				L 3
				D 1
				R 4
				D 1
				L 5
				R 2
				""";

		var sol = new Day09();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(13);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				R 4
				U 4
				L 3
				D 1
				R 4
				D 1
				L 5
				R 2
				""";

		var sol = new Day09();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(1);
	}

	@Test
	public void part2Test2() throws Exception
	{
		var input = """
				R 5
				U 8
				L 8
				D 3
				R 17
				D 10
				L 25
				U 20
				""";

		var sol = new Day09();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(36);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day09();
		CharSource input = Files.asCharSource(new ClassPathResource("day09input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(5902);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day09();
		CharSource input = Files.asCharSource(new ClassPathResource("day09input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(2445);
	}

}
