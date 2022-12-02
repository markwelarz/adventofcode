package advent2022;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.collections4.IteratorUtils;

import com.google.common.io.CharSource;

public class Day02
{
	public int part1(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			int score = 0;
			while(sc.hasNext())
			{
				String theirMove = sc.next();
				String yourMove = sc.next();

				score += yourScore(mapMove(theirMove), mapMove(yourMove));
			}
			
			return score;
		}
	}

	public int part2(CharSource input) throws IOException
	{
		try (Scanner sc = new Scanner(input.openBufferedStream()))
		{
			int score = 0;
			while (sc.hasNext())
			{
				String theirMove = sc.next();
				String outcome = sc.next();

				RPS yourMove = chooseYourMove(mapMove(theirMove), mapOutcome(outcome));
				score += yourScore(mapMove(theirMove), yourMove);
			}

			return score;
		}
	}

	private RPS mapMove(String move)
	{
		return switch (move)
		{
			case "X" -> RPS.ROCK;
			case "Y" -> RPS.PAPER;
			case "Z" -> RPS.SCISSORS;
			case "A" -> RPS.ROCK;
			case "B" -> RPS.PAPER;
			case "C" -> RPS.SCISSORS;
			default -> throw new IllegalArgumentException();
		};
	}

	private Outcome mapOutcome(String outcome)
	{
		return switch (outcome)
		{
			case "X" -> Outcome.LOSE;
			case "Y" -> Outcome.DRAW;
			case "Z" -> Outcome.WIN;
			default -> throw new IllegalArgumentException();
		};
	}

	public int yourScore(RPS theirMove, RPS yourMove)
	{
		int score = switch (yourMove)
		{
			case ROCK -> 1;
			case PAPER -> 2;
			case SCISSORS -> 3;
		};

		score += outcome(theirMove, yourMove);
		
		return score;
	}

	public int outcome(RPS theirMove, RPS yourMove)
	{
		var betterMoves = betterMoves();

		if (theirMove==yourMove)
			return 3;
		
		IteratorUtils.find(betterMoves, v -> v == theirMove);
		var potentialWinningMove = betterMoves.next();
		if (potentialWinningMove == yourMove)
			return 6;
		else
			return 0;
	}

	private RPS chooseYourMove(RPS theirMove, Outcome neededOutcome)
	{
		var betterMoves = betterMoves();

		if (neededOutcome == Outcome.DRAW)
			return theirMove;
		else
		{
			IteratorUtils.find(betterMoves, v -> v == theirMove);

			if (neededOutcome == Outcome.WIN)
				return winningMove(theirMove);
			else
				return losingMove(theirMove);
		}
	}

	private RPS winningMove(RPS move)
	{
		var betterMoves = betterMoves();
		IteratorUtils.find(betterMoves, v -> v == move);
		return betterMoves.next();
	}

	private RPS losingMove(RPS move)
	{
		var betterMoves = betterMoves();
		IteratorUtils.find(betterMoves, v -> v == move);
		betterMoves.previous();
		return betterMoves.previous();
	}

	// model the rock/paper/scissors model as a cyclic iterator
	// the subsequent element beats the current
	private ListIterator<RPS> betterMoves()
	{
		return IteratorUtils.loopingListIterator(List.of(RPS.ROCK, RPS.PAPER, RPS.SCISSORS));
	}
	
	enum Outcome
	{
		LOSE, DRAW, WIN
	}

	enum RPS
	{
		ROCK, PAPER, SCISSORS
	}
}
