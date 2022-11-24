package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day13Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				6,10
				0,14
				9,10
				0,3
				10,4
				4,11
				6,0
				6,12
				4,1
				0,13
				10,12
				3,4
				3,0
				8,4
				1,10
				2,14
				8,10
				9,0

				fold along y=7
				fold along x=5
				""";

		var sol = new Day13();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(17);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				6,10
				0,14
				9,10
				0,3
				10,4
				4,11
				6,0
				6,12
				4,1
				0,13
				10,12
				3,4
				3,0
				8,4
				1,10
				2,14
				8,10
				9,0

				fold along y=7
				fold along x=5
				""";

		var sol = new Day13();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(16);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day13();
		CharSource input = Files.asCharSource(new ClassPathResource("day13input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(618);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day13();
		CharSource input = Files.asCharSource(new ClassPathResource("day13input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(98);
	}

}
