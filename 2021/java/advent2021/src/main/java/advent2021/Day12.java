package advent2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.io.CharSource;

public class Day12
{
	public int part1(CharSource input) throws IOException
	{
		Cave start = read(input);
		CaveRoute caveRoute = new CaveRoute(this::canVisitSmallCavesOnce);
		caveRoute.visit(start, new ArrayList<>());
		return caveRoute.getRouteCount();
	}

	public int part2(CharSource input) throws IOException
	{
		Cave start = read(input);
		CaveRoute caveRoute = new CaveRoute(this::canVisitOneSmallCaveTwiceAndOtherSmallCavesOnce);
		caveRoute.visit(start, new ArrayList<>());
		return caveRoute.getRouteCount();
	}

	private Predicate<Cave> canVisitSmallCavesOnce(List<String> path)
	{
		return cave -> cave.isLargeCave() || !path.contains(cave.name());
	}

	private Predicate<Cave> canVisitOneSmallCaveTwiceAndOtherSmallCavesOnce(List<String> path)
	{
		Collection<Long> smallCaveVisits = path.stream()
				.filter(p -> StringUtils.isAllLowerCase(p))
				.collect(Collectors.collectingAndThen(Collectors.groupingBy(v -> v, Collectors.counting()), v -> v.values()));

		boolean alreadyVisitedSmallCaveTwice = smallCaveVisits.contains(2L);

		return cave -> !cave.isStartCave() && (
				cave.isLargeCave() || 
				!alreadyVisitedSmallCaveTwice ||
				(alreadyVisitedSmallCaveTwice && !path.contains(cave.name())));
	}

	class CaveRoute
	{
		private Function<List<String>, Predicate<Cave>> allowedToVisit;
		private int routeCount = 0;

		public CaveRoute(Function<List<String>, Predicate<Cave>> allowedToVisit)
		{
			this.allowedToVisit = allowedToVisit;
		}

		public void visit(Cave cave, List<String> path)
		{
			path.add(cave.name);
			if (cave.name.equals("end"))
			{
				this.outputPath(path);
				return;
			}

			var validPaths = cave.connections.stream()
					.filter(allowedToVisit.apply(path))
					.toList();

			for (var connecting : validPaths)
			{
				visit(connecting, new ArrayList<>(path));
			}
		}

		public int getRouteCount()
		{
			return routeCount;
		}

		private void outputPath(List<String> donePath)
		{
			routeCount++;
		}
	}

	Cave read(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			Map<String, Cave> caves = new HashMap<>();

			while (sc.hasNext())
			{
				String line = sc.nextLine();
				String caveLink[] = StringUtils.split(line, "-");
				
				Cave fromCave = caves.computeIfAbsent(caveLink[0], id -> new Cave(caveLink[0], new ArrayList<>()));
				Cave toCave = caves.computeIfAbsent(caveLink[1], id -> new Cave(caveLink[1], new ArrayList<>()));
				
				fromCave.connections.add(toCave);
				toCave.connections.add(fromCave);
			}

			return caves.get("start");
		}
	}

	record Cave(String name, List<Cave> connections)
	{
		public boolean isStartCave()
		{
			return name.equals("start");
		}

		public boolean isLargeCave()
		{
			return StringUtils.isAllUpperCase(name);
		}

		@Override
		public String toString()
		{
			return "Cave [name=" + name + ", connections=" + connections.stream().map(v -> v.name).collect(Collectors.joining(",")) + "]";
		}
	}
}
