package advent2021;

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
				0,9 -> 5,9
				8,0 -> 0,8
				9,4 -> 3,4
				2,2 -> 2,1
				7,0 -> 7,4
				6,4 -> 2,0
				0,9 -> 2,9
				3,4 -> 1,4
				0,0 -> 8,8
				5,5 -> 8,2
				""";

		var day5 = new Day05();
		int answer = day5.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(5);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day5 = new Day05();
		CharSource input = Files.asCharSource(new ClassPathResource("day05input.txt").getFile(), Charsets.UTF_8);
		int answer = day5.part1(input);
		assertThat(answer).isEqualTo(5124);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				0,9 -> 5,9
				8,0 -> 0,8
				9,4 -> 3,4
				2,2 -> 2,1
				7,0 -> 7,4
				6,4 -> 2,0
				0,9 -> 2,9
				3,4 -> 1,4
				0,0 -> 8,8
				5,5 -> 8,2
				""";

		var day5 = new Day05();
		int answer = day5.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(12);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day5 = new Day05();
		CharSource input = Files.asCharSource(new ClassPathResource("day05input.txt").getFile(), Charsets.UTF_8);
		int answer = day5.part2(input);
		assertThat(answer).isEqualTo(19771);
	}
}
