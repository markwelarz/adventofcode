package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day02Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				forward 5
				down 5
				forward 8
				up 3
				down 8
				forward 2""";

		var day2 = new Day02();
		int answer = day2.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(150);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day2 = new Day02();
		CharSource input = Files.asCharSource(new ClassPathResource("day02input.txt").getFile(), Charsets.UTF_8);
		int answer = day2.part1(input);
		assertThat(answer).isEqualTo(1694130);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				forward 5
				down 5
				forward 8
				up 3
				down 8
				forward 2""";

		var day2 = new Day02();
		int answer = day2.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(900);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day2 = new Day02();
		CharSource input = Files.asCharSource(new ClassPathResource("day02input.txt").getFile(), Charsets.UTF_8);
		int answer = day2.part2(input);
		assertThat(answer).isEqualTo(1698850445);
	}
}
