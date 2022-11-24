package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day12Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				start-A
				start-b
				A-c
				A-b
				b-d
				A-end
				b-end
				""";

		var sol = new Day12();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(10);
	}

	@Test
	public void part1Test2() throws Exception
	{
		var input = """
				dc-end
				HN-start
				start-kj
				dc-start
				dc-HN
				LN-dc
				HN-end
				kj-sa
				kj-HN
				kj-dc
				""";

		var sol = new Day12();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(19);
	}

	@Test
	public void part1Test3() throws Exception
	{
		var input = """
				fs-end
				he-DX
				fs-he
				start-DX
				pj-DX
				end-zg
				zg-sl
				zg-pj
				pj-he
				RW-he
				fs-DX
				pj-RW
				zg-RW
				start-pj
				he-WI
				zg-he
				pj-fs
				start-RW
				""";

		var sol = new Day12();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(226);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				start-A
				start-b
				A-c
				A-b
				b-d
				A-end
				b-end
				""";

		var sol = new Day12();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(36);
	}

	@Test
	public void part2Test2() throws Exception
	{
		var input = """
				dc-end
				HN-start
				start-kj
				dc-start
				dc-HN
				LN-dc
				HN-end
				kj-sa
				kj-HN
				kj-dc
				""";

		var sol = new Day12();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(103);
	}

	@Test
	public void part2Test3() throws Exception
	{
		var input = """
				fs-end
				he-DX
				fs-he
				start-DX
				pj-DX
				end-zg
				zg-sl
				zg-pj
				pj-he
				RW-he
				fs-DX
				pj-RW
				zg-RW
				start-pj
				he-WI
				zg-he
				pj-fs
				start-RW
				""";

		var sol = new Day12();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(3509);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day12();
		CharSource input = Files.asCharSource(new ClassPathResource("day12input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(3779);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day12();
		CharSource input = Files.asCharSource(new ClassPathResource("day12input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(96988);
	}

}
