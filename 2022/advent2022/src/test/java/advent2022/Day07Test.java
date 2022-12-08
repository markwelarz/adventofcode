package advent2022;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day07Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				$ cd /
				$ ls
				dir a
				14848514 b.txt
				8504156 c.dat
				dir d
				$ cd a
				$ ls
				dir e
				29116 f
				2557 g
				62596 h.lst
				$ cd e
				$ ls
				584 i
				$ cd ..
				$ cd ..
				$ cd d
				$ ls
				4060174 j
				8033020 d.log
				5626152 d.ext
				7214296 k
				""";

		var sol = new Day07();
		int answer = sol.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(95437);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				$ cd /
				$ ls
				dir a
				14848514 b.txt
				8504156 c.dat
				dir d
				$ cd a
				$ ls
				dir e
				29116 f
				2557 g
				62596 h.lst
				$ cd e
				$ ls
				584 i
				$ cd ..
				$ cd ..
				$ cd d
				$ ls
				4060174 j
				8033020 d.log
				5626152 d.ext
				7214296 k
				""";

		var sol = new Day07();
		int answer = sol.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(24933642);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var sol = new Day07();
		CharSource input = Files.asCharSource(new ClassPathResource("day07input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part1(input);
		assertThat(answer).isEqualTo(1783610);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var sol = new Day07();
		CharSource input = Files.asCharSource(new ClassPathResource("day07input.txt").getFile(), Charsets.UTF_8);
		int answer = sol.part2(input);
		assertThat(answer).isEqualTo(-1);
	}
}
