package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day11Test
{
	@Test
	public void part1Test1() throws Exception
	{
		CharSource input = Files.asCharSource(new ClassPathResource("day11test1.txt").getFile(), Charsets.UTF_8);
		var sol = new Day11();
		long answer = sol.part1(input);
		assertThat(answer).isEqualTo(10605);
	}

	@Test
	public void part2Test1() throws Exception
	{
		CharSource input = Files.asCharSource(new ClassPathResource("day11test1.txt").getFile(), Charsets.UTF_8);
		var sol = new Day11();
		long answer = sol.part2(input);
		assertThat(answer).isEqualTo(2713310158L);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day11();
		CharSource input = Files.asCharSource(new ClassPathResource("day11input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part1(input);
		assertThat(answer).isEqualTo(110888);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day11();
		CharSource input = Files.asCharSource(new ClassPathResource("day11input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part2(input);
		assertThat(answer).isEqualTo(25590400731L);
	}
}
