def normalization(data, new_max, new_min):
    old_max = 0
    old_min = 0

    # Finde altes Max- und Minimum
    for i in range(len(data)):
        if old_max < data[i]:
            old_max = data[i]
        elif old_min > data[i]:
            old_min = data[i]

    old_range = (old_max - old_min)

    for i in range(len(data)):
        if old_range == 0:
            data[i] = new_min
        else:
            new_range = (new_max - new_min)
            data[i] = (((data[i] - old_min) * new_range) / old_range) + new_min


from pybrain.datasets import SupervisedDataSet
DS = SupervisedDataSet(5, 1)

DS.addSample((44.055, 44.54, 44.04, 43.975, 43.49), (42.04,))
DS.addSample((44.54, 44.04, 43.975, 43.49, 42.04), (42.6,))
DS.addSample((44.04, 43.975, 43.49, 42.04, 42.6), (42.46,))
DS.addSample((43.975, 43.49, 42.04, 42.6, 42.46), (41.405,))
DS.addSample((43.49, 42.04, 42.6, 42.46, 41.405), (42.385,))
DS.addSample((42.04, 42.6, 42.46, 41.405, 42.385), (42.655,))
DS.addSample((42.6, 42.46, 41.405, 42.385, 42.655), (41.53,))
DS.addSample((42.46, 41.405, 42.385, 42.655, 41.53), (40.09,))
DS.addSample((41.405, 42.385, 42.655, 41.53, 40.09), (39.8,))
DS.addSample((42.385, 42.655, 41.53, 40.09, 39.8), (40.2,))
DS.addSample((42.655, 41.53, 40.09, 39.8, 40.2), (39.915,))
DS.addSample((41.53, 40.09, 39.8, 40.2, 39.915), (40.21,))
DS.addSample((40.09, 39.8, 40.2, 39.915, 40.21), (40.34,))
DS.addSample((39.8, 40.2, 39.915, 40.21, 40.34), (41.195,))
DS.addSample((40.2, 39.915, 40.21, 40.34, 41.195), (41.595,))
DS.addSample((39.915, 40.21, 40.34, 41.195, 41.595), (41.975,))
DS.addSample((40.21, 40.34, 41.195, 41.595, 41.975), (42.045,))
DS.addSample((40.34, 41.195, 41.595, 41.975, 42.045), (40.13,))
DS.addSample((41.195, 41.595, 41.975, 42.045, 40.13), (38.99,))
DS.addSample((41.595, 41.975, 42.045, 40.13, 38.99), (39.81,))
DS.addSample((41.975, 42.045, 40.13, 38.99, 39.81), (40.23,))
DS.addSample((42.045, 40.13, 38.99, 39.81, 40.23), (40.47,))
DS.addSample((40.13, 38.99, 39.81, 40.23, 40.47), (40.45,))
DS.addSample((38.99, 39.81, 40.23, 40.47, 40.45), (40.01,))
DS.addSample((39.81, 40.23, 40.47, 40.45, 40.01), (40.23,))
DS.addSample((40.23, 40.47, 40.45, 40.01, 40.23), (40.2,))
DS.addSample((40.47, 40.45, 40.01, 40.23, 40.2), (41.605,))
DS.addSample((40.45, 40.01, 40.23, 40.2, 41.605), (42.1,))
DS.addSample((40.01, 40.23, 40.2, 41.605, 42.1), (42.135,))
DS.addSample((40.23, 40.2, 41.605, 42.1, 42.135), (41.95,))
DS.addSample((40.2, 41.605, 42.1, 42.135, 41.95), (41.145,))
DS.addSample((41.605, 42.1, 42.135, 41.95, 41.145), (40.635,))
DS.addSample((42.1, 42.135, 41.95, 41.145, 40.635), (41.25,))
DS.addSample((42.135, 41.95, 41.145, 40.635, 41.25), (41.19,))
DS.addSample((41.95, 41.145, 40.635, 41.25, 41.19), (42.065,))
DS.addSample((41.145, 40.635, 41.25, 41.19, 42.065), (42.025,))
DS.addSample((40.635, 41.25, 41.19, 42.065, 42.025), (42.09,))
DS.addSample((41.25, 41.19, 42.065, 42.025, 42.09), (41.79,))
DS.addSample((41.19, 42.065, 42.025, 42.09, 41.79), (43.11,))


from pybrain.tools.shortcuts import buildNetwork
FNN = buildNetwork(DS.indim, 15, DS.outdim, bias=True)

from pybrain.supervised.trainers import BackpropTrainer
TRAINER = BackpropTrainer(FNN, dataset=DS, learningrate = 0.005, \
    momentum=0.1, verbose=True)

for i in range(1000):
    TRAINER.train()