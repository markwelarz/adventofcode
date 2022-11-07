def part1(inputdata):
    depths = list()
    line = inputdata.readline().strip()
    while line != "":
        depths.append(line)
        line = inputdata.readline().strip()

    # 2 sliding windows
    adjacent_pairs = list(zip(depths[:-1], depths[1:]))
    greater_depth_readings = [pair for pair in adjacent_pairs if int(pair[0]) < int(pair[1])]

    return len(greater_depth_readings)

def part2(inputdata):
    depths = list()
    line = inputdata.readline().strip()
    while line != "":
        depths.append(line)
        line = inputdata.readline().strip()

    # 3 sliding windows
    adjacent_triples = list(zip(depths[:-2], depths[1:-1], depths[2:]))
    summed_readings = [int(triple[0]) + int(triple[1]) + int(triple[2]) for triple in adjacent_triples]
    adjacent_pairs = list(zip(summed_readings[:-1], summed_readings[1:]))
    greater_depth_readings = [pair for pair in adjacent_pairs if int(pair[0]) < int(pair[1])]

    return len(greater_depth_readings)
