package advent2022;

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
				[1,1,3,1,1]
				[1,1,5,1,1]

				[[1],[2,3,4]]
				[[1],4]

				[9]
				[[8,7,6]]

				[[4,4],4,4]
				[[4,4],4,4,4]

				[7,7,7,7]
				[7,7,7]

				[]
				[3]

				[[[]]]
				[[]]

				[1,[2,[3,[4,[5,6,7]]]],8,9]
				[1,[2,[3,[4,[5,6,0]]]],8,9]

				""";

		var sol = new Day13();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(13);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				[1,1,3,1,1]
				[1,1,5,1,1]

				[[1],[2,3,4]]
				[[1],4]

				[9]
				[[8,7,6]]

				[[4,4],4,4]
				[[4,4],4,4,4]

				[7,7,7,7]
				[7,7,7]

				[]
				[3]

				[[[]]]
				[[]]

				[1,[2,[3,[4,[5,6,7]]]],8,9]
				[1,[2,[3,[4,[5,6,0]]]],8,9]

				""";

		var sol = new Day13();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(140);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day13();
		CharSource input = Files.asCharSource(new ClassPathResource("day13input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(6076);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day13();
		CharSource input = Files.asCharSource(new ClassPathResource("day13input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(24805);
	}
}
