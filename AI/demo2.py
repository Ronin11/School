import pygal

# Our data is a list of series.
# Each series is a list with a label and a list of data points
data = [["Firefox", [None, None, 0, 16.6,   25,   31, 36.4, 45.5, 46.3, 42.8, 37.1]],
        ["Chrome",  [None, None, None, None, None, None,    0,  3.9, 10.8, 23.8, 35.3]],
        ["IE",      [85.8, 84.6, 84.7, 74.5,   66, 58.6, 54.7, 44.8, 36.2, 26.6, 20.1]],
        ["Others",  [14.2, 15.4, 15.3,  8.9,    9, 10.4,  8.9,  5.8,  6.7,  6.8,  7.5]]]


# Make a Pygal chart
stackedline_chart = pygal.StackedLine(fill=True)
stackedline_chart.title = "Browser usage evolution (in %)"
stackedline_chart.x_labels = map(str, range(2002, 2013))

# For each series in our data, add label and data points
for label, data_points in data:
    stackedline_chart.add(label, data_points)

# Render the chart
stackedline_chart.render()

# Example modified from the pygal.org documentation