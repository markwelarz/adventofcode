package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day04Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

				22 13 17 11  0
				 8  2 23  4 24
				21  9 14 16  7
				 6 10  3 18  5
				 1 12 20 15 19

				 3 15  0  2 22
				 9 18 13 17  5
				19  8  7 25 23
				20 11 10 24  4
				14 21 16 12  6

				14 21 17 24  4
				10 16 15  9 19
				18  8 23 26 20
				22 11 13  6  5
				 2  0 12  3  7
				""";

		var day4 = new Day04();
		int answer = day4.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(4512);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day4 = new Day04();
		CharSource input = Files.asCharSource(new ClassPathResource("day04input.txt").getFile(), Charsets.UTF_8);
		int answer = day4.part1(input);
		assertThat(answer).isEqualTo(41668);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

				22 13 17 11  0
				 8  2 23  4 24
				21  9 14 16  7
				 6 10  3 18  5
				 1 12 20 15 19

				 3 15  0  2 22
				 9 18 13 17  5
				19  8  7 25 23
				20 11 10 24  4
				14 21 16 12  6

				14 21 17 24  4
				10 16 15  9 19
				18  8 23 26 20
				22 11 13  6  5
				 2  0 12  3  7
				""";

		var day4 = new Day04();
		int answer = day4.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(1924);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day4 = new Day04();
		CharSource input = Files.asCharSource(new ClassPathResource("day04input.txt").getFile(), Charsets.UTF_8);
		int answer = day4.part2(input);
		assertThat(answer).isEqualTo(10478);
	}
}
