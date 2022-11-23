package advent2021;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public class Day08Test
{
	@Test
	public void part1Test1() throws Exception
	{
		var input = """
				be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
				edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
				fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
				fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
				aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
				fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
				dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
				bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
				egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
				gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
				""";

		var day8 = new Day08();
		int answer = day8.part1(CharSource.wrap(input));
		assertThat(answer).isEqualTo(26);
	}

	@Test
	public void part2Test1() throws Exception
	{
		var input = """
				be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
				edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
				fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
				fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
				aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
				fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
				dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
				bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
				egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
				gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
				""";

		var day8 = new Day08();
		int answer = day8.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(61229);
	}

	@Test
	public void part2Test2() throws Exception
	{
		var input = """
				acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
				cdfeb fcadb cdfeb cdbaf
								""";

		var day8 = new Day08();
		int answer = day8.part2(CharSource.wrap(input));
		assertThat(answer).isEqualTo(5353);
	}

	@Test
	public void part1Solution() throws Exception
	{
		var day8 = new Day08();
		CharSource input = Files.asCharSource(new ClassPathResource("day08input.txt").getFile(), Charsets.UTF_8);
		int answer = day8.part1(input);
		assertThat(answer).isEqualTo(375);
	}

	@Test
	public void part2Solution() throws Exception
	{
		var day8 = new Day08();
		CharSource input = Files.asCharSource(new ClassPathResource("day08input.txt").getFile(), Charsets.UTF_8);
		int answer = day8.part2(input);
		assertThat(answer).isEqualTo(1019355);
	}
}
