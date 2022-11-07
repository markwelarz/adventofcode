import io
import unittest

from day14 import part1, polymerize


class Day14Tests(unittest.TestCase):

    def test_polymerize_example1(self):
        self.assertEqual(polymerize(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 1), "NCNBCHB")

    def test_polymerize_example2(self):
        self.assertEqual(polymerize(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 2), "NBCCNBBBCBHCB")

    def test_polymerize_example3(self):
        self.assertEqual(polymerize(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 3), "NBBBCNCCNBBNBNBBCHBHHBCHB")

    def test_polymerize_example4(self):
        self.assertEqual(polymerize(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 4), "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB")

    def test_part1_example1(self):
        self.assertEqual(part1(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 10), 1588)

    def test_part1_solution(self):
        f = open("day14input.txt", "r")
        answer = part1(f, 10)
        self.assertEqual(answer, 2375)
        f.close()
        print(answer)

    def test_part2_example1(self):
        self.assertEqual(part1(io.StringIO("""NNCB

                                                CH -> B
                                                HH -> N
                                                CB -> H
                                                NH -> C
                                                HB -> C
                                                HC -> B
                                                HN -> C
                                                NN -> C
                                                BH -> H
                                                NC -> B
                                                NB -> B
                                                BN -> B
                                                BB -> N
                                                BC -> B
                                                CC -> N
                                                CN -> C"""), 40), 2188189693529)

    def test_part2_solution(self):
        f = open("day14input.txt", "r")
        answer = part1(f, 40)
        self.assertEqual(answer, 1795)
        f.close()
        print(answer)
