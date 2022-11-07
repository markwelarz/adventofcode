import io
import unittest
from day01 import part1, part2


class Day01Tests(unittest.TestCase):

    def test_part1_example1(self):
        self.assertEqual(part1(io.StringIO("""199
                                            200
                                            208
                                            210
                                            200
                                            207
                                            240
                                            269
                                            260
                                            263""")), 7)

    def test_part1_solution(self):
        f = open("day01input.txt", "r")
        answer = part1(f)
        self.assertEqual(answer, 1529)
        f.close()
        print(answer)


    def test_part2_example1(self):
        self.assertEqual(part2(io.StringIO("""199
                                            200
                                            208
                                            210
                                            200
                                            207
                                            240
                                            269
                                            260
                                            263""")), 5)

    def test_part2_solution(self):
        f = open("day01input.txt", "r")
        answer = part2(f)
        self.assertEqual(answer, 1567)
        f.close()
        print(answer)
