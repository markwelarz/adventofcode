import logging
from collections import Counter
from io import StringIO


def part1(inputdata, cycles):
    logging.basicConfig(format='%(asctime)s %(message)s', level=logging.DEBUG)

    expanded = polymerize(inputdata, cycles)

    count_each_char = Counter(expanded).most_common()
    return count_each_char[0][1] - count_each_char[-1][1]


def polymerize(inputdata, cycles):
    template = inputdata.readline().strip()

    # blank line
    inputdata.readline()

    insertion_rules = {}
    line = inputdata.readline().strip()
    while line != "":
        map_from, map_to = line.split(" -> ")

        insertion_rules[map_from] = map_to
        line = inputdata.readline().strip()

    in_template = StringIO(template)

    for i in range(cycles):
        logging.warning("doing cycle %s", i)

        out_template = StringIO()

        ch_left = in_template.read(1)
        ch_right = in_template.read(1)

        while ch_right != "":
            l = len(out_template.getvalue())
            if l % 100 == 0:
                print(i, " ", l)
            if ch_left + ch_right in insertion_rules:
                out_template.write(ch_left)
                out_template.write(insertion_rules.get(ch_left + ch_right))
            else:
                out_template.write(ch_left)

            ch_left = ch_right
            ch_right = in_template.read(1)

        out_template.write(ch_left)

        in_template = StringIO(out_template.getvalue())

    return out_template.getvalue()
