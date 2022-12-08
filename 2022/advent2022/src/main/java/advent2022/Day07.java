package advent2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Streams;
import com.google.common.graph.Traverser;
import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;

public class Day07
{
	public int part1(CharSource input) throws IOException
	{
		Dir root = input.readLines(new LP());
		
		Iterable<Dir> it = Traverser.<Dir>forTree(v -> v.subdirs)
				.breadthFirst(root);
		
		int size = Streams.stream(it)
				.filter(d -> d.size() <= 100000)
				.mapToInt(Dir::size).sum();

		return size;
	}

	public int part2(CharSource input) throws IOException
	{
		Dir root = input.readLines(new LP());

		int totalSpace = 70000000;
		int spaceRequired = 30000000;
		int usedSpace = root.size();
		int freeSpace = totalSpace - usedSpace;
		int freeUp = spaceRequired - freeSpace;

		Iterable<Dir> it = Traverser.<Dir>forTree(v -> v.subdirs)
				.breadthFirst(root);

		int dirToDeleteSize = Streams.stream(it)
				.mapToInt(Dir::size)
				.filter(v -> v >= freeUp)
				.sorted()
				.min()
				.getAsInt();

		return dirToDeleteSize;
	}

	class LP implements LineProcessor<Dir>
	{
		private Deque<Dir> pathTo = new LinkedList<>();
		private Dir root = new Dir("/", new ArrayList<>(), new ArrayList<>());

		LP()
		{
			pathTo.add(root);
		}

		@Override
		public boolean processLine(String line) throws IOException
		{
			if (line.startsWith("$"))
			{
				if (line.startsWith("$ cd "))
				{
					String folderName = StringUtils.substringAfterLast(line, " ");
					switch (folderName)
					{
						case ".." ->
						{
							pathTo.removeLast();
							break;
						}
						case "/" ->
						{
							pathTo.clear();
							pathTo.add(root);
							break;
						}
						default ->
						{
							Dir newDir = Iterables.tryFind(pathTo.getLast().subdirs, v -> v.name.equals(folderName))
									.or(() -> new Dir(folderName, new ArrayList<>(), new ArrayList<>()));
							pathTo.addLast(newDir);
						}
					}

				}
			}
			else
			{
				// dir listing
				String[] split = StringUtils.split(line, " ");
				String sizeOrDir = split[0];
				String fileOrFolder = split[1];

				if (sizeOrDir.equals("dir"))
				{
					pathTo.getLast().subdirs.add(new Dir(fileOrFolder, new ArrayList<>(), new ArrayList<>()));
				}
				else
				{
					pathTo.getLast().files().add(new F(fileOrFolder, Integer.parseInt(sizeOrDir)));
				}
			}

			return true;
		}

		@Override
		public Dir getResult()
		{
			return root;
		}
	}

	record F(String name, int size)
	{
	}

	record Dir(String name, List<Dir> subdirs, List<F> files)
	{
		int size()
		{
			int dirSize = subdirs.stream().mapToInt(Dir::size).sum();
			int fileSize = files.stream().mapToInt(F::size).sum();
			return dirSize + fileSize;
		}
	}
}
