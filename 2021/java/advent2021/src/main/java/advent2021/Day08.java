package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.google.common.collect.Iterables;
import com.google.common.collect.MoreCollectors;
import com.google.common.collect.Sets;
import com.google.common.io.CharSource;

public class Day08
{
	private static final int DIGIT_1_SEGS = 2;
	private static final int DIGIT_4_SEGS = 4;
	private static final int DIGIT_7_SEGS = 3;
	private static final int DIGIT_8_SEGS = 7;
	private static final int DIGIT_069_SEGS = 6;
	private static final int DIGIT_235_SEGS = 5;

	public int part1(CharSource input) throws IOException
	{
		var displays = read(input);
		return displays.stream().mapToInt(v -> v.count1478()).sum();
	}

	public int part2(CharSource input) throws IOException
	{
		List<Display> displays = read(input);
		int total = 0;

		for (var display : displays)
		{
			List<SegmentMapping> mapping = this.newSegmentMapping();

			apply147Rule(mapping, display);
			apply17DiffRule(mapping, display); // find a
			apply069Rule(mapping, display); // eliminate possibilities for c,d,e
			apply235Rule(mapping, display); // eliminate possibilities for b,c,e,f

			eliminatePossibles(mapping);

			mapping.forEach(v -> {
				Validate.isTrue(v.possibleInputSegmmnts().size() == 1);
			});
			
			total += decodeOutput(display, mapping);
		}

		return total;
	}

	private int decodeOutput(Display display, List<SegmentMapping> mapping)
	{
		String fromChars = "";
		String toChars = "";

		for (var m : mapping)
		{
			fromChars += Iterables.getOnlyElement(m.possibleInputSegmmnts());
			toChars += m.outputSegment();
		}
		
		String digits="";
		for (var output : display.outputs())
		{
			char[] decodedDigits = StringUtils.replaceChars(output, fromChars, toChars).toCharArray();
			Arrays.sort(decodedDigits);
			String sortedDecoded = String.valueOf(decodedDigits);
			
			digits += switch (sortedDecoded)
			{
				case "abcefg" -> "0";
				case "cf" -> "1";
				case "acdeg" -> "2";
				case "acdfg" -> "3";
				case "bcdf" -> "4";
				case "abdfg" -> "5";
				case "abdefg" -> "6";
				case "acf" -> "7";
				case "abcdefg" -> "8";
				case "abcdfg" -> "9";
				default -> throw new IllegalArgumentException(sortedDecoded);
			};
		}

		return Integer.parseInt(digits);
	}

	// can reduce possibilities for b,c,e,f
	private void apply235Rule(List<SegmentMapping> mapping, Display display)
	{
		List<Set<String>> fiveDigitDisplays = display.inputs.stream()
				.filter(v -> v.length() == DIGIT_235_SEGS)
				.map(v -> this.stringToSet(v))
				.toList();

		Validate.isTrue(fiveDigitDisplays.size() == 3);

		var cde1 = Sets.symmetricDifference(fiveDigitDisplays.get(0), fiveDigitDisplays.get(1));
		var cde2 = Sets.symmetricDifference(fiveDigitDisplays.get(1), fiveDigitDisplays.get(2));
		var cde3 = Sets.symmetricDifference(fiveDigitDisplays.get(2), fiveDigitDisplays.get(0));

		Set<String> possiblesForBCEF = new HashSet<>();
		possiblesForBCEF.addAll(cde1);
		possiblesForBCEF.addAll(cde2);
		possiblesForBCEF.addAll(cde3);

		this.signalXIsOneOf("b", mapping, possiblesForBCEF);
		this.signalXIsOneOf("c", mapping, possiblesForBCEF);
		this.signalXIsOneOf("e", mapping, possiblesForBCEF);
		this.signalXIsOneOf("f", mapping, possiblesForBCEF);
	}

	// can reduce possibilities for c,d,e
	private void apply069Rule(List<SegmentMapping> mapping, Display display)
	{
		List<Set<String>> sixDigitDisplays = display.inputs.stream()
				.filter(v -> v.length() == DIGIT_069_SEGS)
				.map(v -> this.stringToSet(v))
				.toList();

		Validate.isTrue(sixDigitDisplays.size() == 3);

		var cde1 = Sets.symmetricDifference(sixDigitDisplays.get(0), sixDigitDisplays.get(1));
		var cde2 = Sets.symmetricDifference(sixDigitDisplays.get(1), sixDigitDisplays.get(2));
		var cde3 = Sets.symmetricDifference(sixDigitDisplays.get(2), sixDigitDisplays.get(0));

		Set<String> possiblesForCDE = new HashSet<>();
		possiblesForCDE.addAll(cde1);
		possiblesForCDE.addAll(cde2);
		possiblesForCDE.addAll(cde3);

		this.signalXIsOneOf("c", mapping, possiblesForCDE);
		this.signalXIsOneOf("d", mapping, possiblesForCDE);
		this.signalXIsOneOf("e", mapping, possiblesForCDE);
	}

	// if we have a 1 and a 7, the difference is the "a" segment
	private void apply17DiffRule(List<SegmentMapping> mapping, Display display)
	{
		Optional<Set<String>> twoSegDisplay = display.inputs.stream()
				.filter(v -> v.length() == DIGIT_1_SEGS)
				.map(v -> this.stringToSet(v))
				.collect(MoreCollectors.toOptional());
				
		Optional<Set<String>> threeSegDisplay = display.inputs.stream()
				.filter(v -> v.length() == DIGIT_7_SEGS)
				.map(v -> this.stringToSet(v))
				.collect(MoreCollectors.toOptional());

		if (twoSegDisplay.isPresent() && threeSegDisplay.isEmpty())
		{
			var oneReadout = twoSegDisplay.get();
			var sevenReadout = threeSegDisplay.get();

			sevenReadout.removeAll(oneReadout);

			String aSignal = Iterables.getOnlyElement(sevenReadout);
			signalXIsOneOf("a", mapping, aSignal);
		}
	}

	private void apply147Rule(List<SegmentMapping> mapping, Display display)
	{
		for (var inputDigit : display.inputs())
		{
			if (inputDigit.length() == DIGIT_1_SEGS)
			{
				signalXIsOneOf("c", mapping, inputDigit);
				signalXIsOneOf("f", mapping, inputDigit);
			}
			else if (inputDigit.length() == DIGIT_4_SEGS)
			{
				signalXIsOneOf("b", mapping, inputDigit);
				signalXIsOneOf("c", mapping, inputDigit);
				signalXIsOneOf("d", mapping, inputDigit);
				signalXIsOneOf("f", mapping, inputDigit);
			}
			else if (inputDigit.length() == DIGIT_7_SEGS)
			{
				signalXIsOneOf("a", mapping, inputDigit);
				signalXIsOneOf("c", mapping, inputDigit);
				signalXIsOneOf("f", mapping, inputDigit);
			}
		}
	}

	private Set<String> stringToSet(String str)
	{
		return str.chars()
				.mapToObj(v -> Character.valueOf((char) v))
				.map(v -> String.valueOf(v))
				.collect(Collectors.toSet());
	}

	private void signalXIsOneOf(String signal, List<SegmentMapping> mapping, String mappings)
	{
		signalXIsOneOf(signal, mapping, stringToSet(mappings));
	}

	private void signalXIsOneOf(String signal, List<SegmentMapping> mapping, Set<String> mappings)
	{
		for (var sm : mapping)
		{
			if (sm.outputSegment().equals(signal))
				sm.possibleInputSegmmnts().retainAll(mappings);
		}
	}

	private void eliminatePossibles(List<SegmentMapping> mapping)
	{
		boolean didAnElim;

		do
		{
			var eliminateThese = mapping.stream()
					.filter(v -> v.possibleInputSegmmnts().size() == 1)
					.map(v -> v.possibleInputSegmmnts())
					.map(v -> Iterables.getOnlyElement(v))
					.toList();

			didAnElim = false;

			for (var el : eliminateThese)
			{
				for (var sm : mapping)
				{
					if (sm.possibleInputSegmmnts().size() != 1)
						didAnElim |= sm.possibleInputSegmmnts().remove(el);
				}
			}
		}
		while (didAnElim);
	}

	List<Display> read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			List<Display> displays = new ArrayList<>();

			while (sc.hasNext())
			{
				List<String> inputs = new ArrayList<>();

				for (int i = 1; i <= 10; i++)
				{
					String signalPattern = sc.next();
					inputs.add(signalPattern);
				}

				Validate.isTrue(sc.hasNext("\\|"), "expected | but was %s", sc.next());

				List<String> outputs = new ArrayList<>();

				for (int i = 1; i <= 4; i++)
				{
					String outputDigit = sc.next();
					outputs.add(outputDigit);
				}

				displays.add(new Display(inputs, outputs));
			}

			return displays;
		}
	}
	
	private List<SegmentMapping> newSegmentMapping()
	{
		return List.of(
				new SegmentMapping("a", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("b", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("c", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("d", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("e", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("f", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")),
				new SegmentMapping("g", Sets.newHashSet("a", "b", "c", "d", "e", "f", "g")));
	}

	record Display(List<String> inputs, List<String> outputs)
	{
		int count1478()
		{
			int count = 0;

			// find 1,4,7,8
			for (String output : outputs)
			{
				switch (output.length())
				{
					case DIGIT_1_SEGS, DIGIT_4_SEGS, DIGIT_7_SEGS, DIGIT_8_SEGS -> count++;
				}
			}
			return count;
		}
	}

	record SegmentMapping(String outputSegment, Set<String> possibleInputSegmmnts)
	{
	}
}
