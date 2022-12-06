package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import com.google.common.io.CharSource;

public class Day05
{
	public String part1(CharSource input) throws IOException
	{
		var initialState = read(input);
		SortedMap<Integer, Deque<String>> crateStacksByStackNumber = initialState.initialPositions();

		for (var moveInstruction : initialState.moveInstructions())
		{
			Deque<String> fromCrateStack = crateStacksByStackNumber.get(moveInstruction.stackFrom());
			Deque<String> toCrateStack = crateStacksByStackNumber.get(moveInstruction.stackTo());
			for (int i = 0; i < moveInstruction.count(); i++)
			{
				String crate = fromCrateStack.removeFirst();
				toCrateStack.addFirst(crate);
			}
		}

		String topCrates = crateStacksByStackNumber.values().stream()
				.map(Deque::getFirst)
				.collect(Collectors.joining());

		return topCrates;
	}

	public String part2(CharSource input) throws IOException
	{
		var initialState = read(input);
		SortedMap<Integer, Deque<String>> crateStacksByStackNumber = initialState.initialPositions();

		for (var moveInstruction : initialState.moveInstructions())
		{
			Deque<String> fromCrateStack = crateStacksByStackNumber.get(moveInstruction.stackFrom());
			Deque<String> toCrateStack = crateStacksByStackNumber.get(moveInstruction.stackTo());

			Deque<String> movingCrates = new LinkedList<>();
			for (int i = 0; i < moveInstruction.count(); i++)
			{
				String crate = fromCrateStack.removeFirst();
				movingCrates.addFirst(crate);
			}
			movingCrates.forEach(toCrateStack::addFirst);
		}

		String topCrates = crateStacksByStackNumber.values().stream()
				.map(Deque::getFirst)
				.collect(Collectors.joining());

		return topCrates;
	}

	StateAndInstructions read(CharSource input) throws IOException
	{
		var lines = input.readLines();
		int i=0;
		List<CrateAndPosition> cratesWithCharacterPositions = new ArrayList<>();

		String line = lines.get(i);
		while (line.contains("["))
		{			
			var crates = Pattern.compile("[A-Z]").matcher(line)
					.results()
					.map(v -> new CrateAndPosition(v.start(), v.group()))
					.toList();

			cratesWithCharacterPositions.addAll(crates);
			line = lines.get(++i);
		}

		line = lines.get(i);
		Map<Integer, Integer> positionNumberToStackNumber = Pattern.compile("\\d").matcher(line)
				.results()
				.collect(Collectors.toMap(MatchResult::start, v -> Integer.parseInt(v.group())));

		SortedMap<Integer, Deque<String>> cratesInStacks = cratesWithCharacterPositions.stream()
				.map(v -> v.toStackPosition(positionNumberToStackNumber))
				.collect(Collectors.groupingBy(CrateAndPosition::position, TreeMap::new,
						Collectors.mapping(CrateAndPosition::name, Collectors.toCollection(LinkedList::new))));
		
		List<MoveInstruction> moveInstructions = new ArrayList<>();

		for (i += 2; i < lines.size(); i++)
		{
			var numbersOnLine = Pattern.compile("\\d+").matcher(lines.get(i))
					.results()
					.map(MatchResult::group)
					.map(Integer::parseInt)
					.toList();

			Validate.isTrue(numbersOnLine.size() == 3);

			moveInstructions.add(new MoveInstruction(numbersOnLine.get(0), numbersOnLine.get(1), numbersOnLine.get(2)));
		}

		return new StateAndInstructions(cratesInStacks, moveInstructions);
	}

	record MoveInstruction(int count, int stackFrom, int stackTo)
	{
	}

	record CrateAndPosition(int position, String name)
	{
		CrateAndPosition toStackPosition(Map<Integer, Integer> mapping)
		{
			return new CrateAndPosition(mapping.get(position), name);
		}
	}

	record StateAndInstructions(SortedMap<Integer, Deque<String>> initialPositions, List<MoveInstruction> moveInstructions)
	{
	}
}
