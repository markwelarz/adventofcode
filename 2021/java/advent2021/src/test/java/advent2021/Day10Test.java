package advent2021;

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
		var input = """
				[({(<(())[]>[[{[]{<()<>>
				[(()[<>])]({[<{<<[]>>(
				{([(<{}[<>[]}>{[]{[(<()>
				(((({<>}<{<{<>}{[]{[]{}
				[[<[([]))<([[{}[[()]]]
				[{[{({}]{}}([{[{{{}}([]
				{<[[]]>}<{[{[{[]{()[[[]
				[<(<(<(<{}))><([]([]()
				<{([([[(<>()){}]>(<<{{
				<{([{{}}[<[[[<>{}]]]>[]]
				""";

		var sol = new Day10();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(26397);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				[({(<(())[]>[[{[]{<()<>>
				[(()[<>])]({[<{<<[]>>(
				{([(<{}[<>[]}>{[]{[(<()>
				(((({<>}<{<{<>}{[]{[]{}
				[[<[([]))<([[{}[[()]]]
				[{[{({}]{}}([{[{{{}}([]
				{<[[]]>}<{[{[{[]{()[[[]
				[<(<(<(<{}))><([]([]()
				<{([([[(<>()){}]>(<<{{
				<{([{{}}[<[[[<>{}]]]>[]]
				""";

		var sol = new Day10();
		long answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(288957);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day10();
		CharSource input = Files.asCharSource(new ClassPathResource("day10input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(392367);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day10();
		CharSource input = Files.asCharSource(new ClassPathResource("day10input.txt").getFile(), Charsets.UTF_8);
		long answer = sol.part2(input);
		assertThat(answer).isEqualTo(2192104158L);
	}
}
