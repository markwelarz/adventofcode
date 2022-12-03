package advent2022;

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
				vJrwpWtwJgWrhcsFMMfFFhFp
				jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
				PmmdzqPrVvPwwTWBwg
				wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
				ttgJtRGJQctTZtZT
				CrZsJsPPZsGzwwsLwLmpwMDw
				""";

		var sol = new Day03();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(157);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				vJrwpWtwJgWrhcsFMMfFFhFp
				jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
				PmmdzqPrVvPwwTWBwg
				wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
				ttgJtRGJQctTZtZT
				CrZsJsPPZsGzwwsLwLmpwMDw
				""";

		var sol = new Day03();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(70);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day03();
		CharSource input = Files.asCharSource(new ClassPathResource("day03input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(7674);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day03();
		CharSource input = Files.asCharSource(new ClassPathResource("day03input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(2805);
	}
}
