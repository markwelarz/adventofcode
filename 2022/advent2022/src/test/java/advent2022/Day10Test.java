package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day10Test
{
	@Test
	public void part1Test1() throws Exception
	{
		CharSource input = Files.asCharSource(new ClassPathResource("day10test1.txt").getFile(), Charsets.UTF_8);
		var sol = new Day10();
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(13140);
	}

	@Test
	public void part2Test1() throws Exception
	{
		CharSource input = Files.asCharSource(new ClassPathResource("day10test1.txt").getFile(), Charsets.UTF_8);
		var sol = new Day10();
		sol.part2(input);
		// no test - look at console output
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day10();
		CharSource input = Files.asCharSource(new ClassPathResource("day10input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(16060);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day10();
		CharSource input = Files.asCharSource(new ClassPathResource("day10input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		// no test - look at console output	
	}
}
