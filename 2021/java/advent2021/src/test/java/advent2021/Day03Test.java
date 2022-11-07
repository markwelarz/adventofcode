package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day03Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				00100
				11110
				10110
				10111
				10101
				01111
				00111
				11100
				10000
				11001
				00010
				01010""";

		var day3 = new Day03();
		int answer = day3.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(198);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				00100
				11110
				10110
				10111
				10101
				01111
				00111
				11100
				10000
				11001
				00010
				01010""";

		var day3 = new Day03();
		int answer = day3.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(230);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day3 = new Day03();
		CharSource input = Files.asCharSource(new ClassPathResource("day03input.txt").getFile(), Charsets.UTF_8);
		int answer = day3.part1(input);
		assertThat(answer).isEqualTo(741950);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day3 = new Day03();
		CharSource input = Files.asCharSource(new ClassPathResource("day03input.txt").getFile(), Charsets.UTF_8);
		int answer = day3.part2(input);
		assertThat(answer).isEqualTo(903810);
	}
}
