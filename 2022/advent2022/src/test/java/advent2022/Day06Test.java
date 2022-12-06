package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day06Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
		var sol = new Day06();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(7);
	}

	@Test
	public void part1Test2() throws Exception
	{
		var input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
		var sol = new Day06();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(5);
	}

	@Test
	public void part1Test3() throws Exception
	{
		var input = "nppdvjthqldpwncqszvftbrmjlhg";
		var sol = new Day06();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(6);
	}

	@Test
	public void part1Test4() throws Exception
	{
		var input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
		var sol = new Day06();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(10);
	}

	@Test
	public void part1Test5() throws Exception
	{
		var input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
		var sol = new Day06();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(11);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
		var sol = new Day06();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(19);
	}

	@Test
	public void part2Test2() throws Exception
	{
		var input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
		var sol = new Day06();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(23);
	}

	@Test
	public void part2Test3() throws Exception
	{
		var input = "nppdvjthqldpwncqszvftbrmjlhg";
		var sol = new Day06();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(23);
	}

	@Test
	public void part2Test4() throws Exception
	{
		var input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
		var sol = new Day06();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(29);
	}

	@Test
	public void part2Test5() throws Exception
	{
		var input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
		var sol = new Day06();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(26);
	}


	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day06();
		CharSource input = Files.asCharSource(new ClassPathResource("day06input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(1134);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day06();
		CharSource input = Files.asCharSource(new ClassPathResource("day06input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(2263);
	}
}
