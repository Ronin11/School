from zipline.api import order_target, record, symbol, history, add_history
from pybrain.tools.shortcuts import buildNetwork
from pybrain.structure import RecurrentNetwork
from pybrain.structure import LinearLayer, SigmoidLayer, TanhLayer
from pybrain.structure import FullConnection
from pybrain.supervised.trainers import BackpropTrainer
from pybrain.datasets import SupervisedDataSet
from random import random
import numpy as np
import talib


# simpleNetwork = buildNetwork(3,100,2)

simpleNetwork = RecurrentNetwork();
simpleNetwork.addInputModule(LinearLayer(2, name='in'))
simpleNetwork.addModule(TanhLayer(10, name='hidden1'))
simpleNetwork.addModule(TanhLayer(10, name='hidden2'))
simpleNetwork.addOutputModule(TanhLayer(1, name='out'))
simpleNetwork.addConnection(FullConnection(simpleNetwork['in'], simpleNetwork['hidden1'], name='c1'))
simpleNetwork.addConnection(FullConnection(simpleNetwork['hidden1'], simpleNetwork['hidden2'], name='c2'))
simpleNetwork.addConnection(FullConnection(simpleNetwork['hidden2'], simpleNetwork['out'], name='c3'))
simpleNetwork.sortModules()

ds = SupervisedDataSet(2,1)
ds.addSample((30, 20), (1))
ds.addSample((70, 80), (-1))
trainer = BackpropTrainer(simpleNetwork, ds)
trainer.trainEpochs(500)





def initialize(context):
    context.amtOwned = 0
    context.hardTrainPhase = 500
    context.buyData = []
    context.windowLength = 10
    context.WindowData = {'price':[], 'volume':[], 'high':[], 'low':[]}
    context.ticker = 'TTI'
    context.returns = 0;

    print "init"


def handle_data(context, data):
    if data[context.ticker]['price'] == 0:
        return
    if len(context.WindowData['price']) < context.windowLength:
        context.WindowData['price'].append(data[context.ticker]['price'])
        context.WindowData['volume'].append(data[context.ticker]['volume'])
        context.WindowData['high'].append(data[context.ticker]['high'])
        context.WindowData['low'].append(data[context.ticker]['low'])
    else:

        high = np.asarray(context.WindowData['high'])
        low = np.asarray(context.WindowData['low'])
        price = np.asarray(context.WindowData['price'])
        volume = np.asarray(context.WindowData['volume'], dtype=np.float64)

        obs = []
        # obs.append(talib.SMA(price, timeperiod=5)[-1])
        # obs.append(talib.SMA(price, timeperiod=10)[-1])
        obs.append(talib.RSI(price, timeperiod=8)[-1])
        obs.append(talib.MFI(high, low, price, volume, timeperiod=5)[-1])
        print "Obs: ",obs

        output = simpleNetwork.activate(obs)
        print "Activation: ", output
        if context.amtOwned == 0:
            if output >=.25:
                print "buy"
                context.amtOwned = 100
                context.buyData = obs[:]
                context.buyData.append(data[context.ticker]['price'])
        else:
            if output <= -.25:
                print "sell"
                context.returns += data[context.ticker]['price'] - context.buyData[-1]
                if data[context.ticker]['price'] - context.buyData[-1] >= 0:
                    ds = SupervisedDataSet(2,1)
                    ds.addSample(context.buyData[:-1], (1))
                    ds.addSample(obs, (-1))
                    trainer = BackpropTrainer(simpleNetwork, ds)
                    trainer.trainEpochs(50)
                context.amtOwned = 0

        context.WindowData['price'].pop(0);
        context.WindowData['volume'].pop(0);
        context.WindowData['high'].pop(0);
        context.WindowData['low'].pop(0);
        context.WindowData['price'].append(data[context.ticker]['price'])
        context.WindowData['volume'].append(data[context.ticker]['volume'])
        context.WindowData['high'].append(data[context.ticker]['high'])
        context.WindowData['low'].append(data[context.ticker]['low'])

        print "::::::::::::RETURNS::::::::::",context.returns




# Note: this function can be removed if running
# this algorithm on quantopian.com
def analyze(context=None, results=None):
    import matplotlib.pyplot as plt
    import logbook
    logbook.StderrHandler().push_application()
    log = logbook.Logger('Algorithm')

    fig = plt.figure()
    ax1 = fig.add_subplot(211)
    results.portfolio_value.plot(ax=ax1)
    ax1.set_ylabel('Portfolio value (USD)')

    ax2 = fig.add_subplot(212)
    ax2.set_ylabel('Price (USD)')

    # If data has been record()ed, then plot it.
    # Otherwise, log the fact that no data has been recorded.
    if ('AAPL' in results and 'short_mavg' in results and
            'long_mavg' in results):
        results['AAPL'].plot(ax=ax2)
        results[['short_mavg', 'long_mavg']].plot(ax=ax2)

        trans = results.ix[[t != [] for t in results.transactions]]
        buys = trans.ix[[t[0]['amount'] > 0 for t in
                         trans.transactions]]
        sells = trans.ix[
            [t[0]['amount'] < 0 for t in trans.transactions]]
        ax2.plot(buys.index, results.short_mavg.ix[buys.index],
                 '^', markersize=10, color='m')
        ax2.plot(sells.index, results.short_mavg.ix[sells.index],
                 'v', markersize=10, color='k')
        plt.legend(loc=0)
    else:
        msg = 'AAPL, short_mavg & long_mavg data not captured using record().'
        ax2.annotate(msg, xy=(0.1, 0.5))
        log.info(msg)

    plt.show()




if __name__ == '__main__':
    from datetime import datetime
    import pytz
    from zipline.algorithm import TradingAlgorithm
    from zipline.utils.factory import load_bars_from_yahoo

    tickerFile = open("data/russel_microcap.csv");
    tickerList = [];
    for line in tickerFile:
        tickerList.append(line.split(' ')[-1].strip('\n'))
    tickerFile.close();
    print tickerList[0:10]
    print len(tickerList);

    # Set the simulation start and end dates.
    start = datetime(2012, 1, 1, 0, 0, 0, 0, pytz.utc)
    end = datetime(2015, 8, 1, 0, 0, 0, 0, pytz.utc)

    # Load price data from yahoo.
    data = load_bars_from_yahoo(stocks=tickerList, indexes={}, start=start,
                           end=end)

    print data.shape
    data.fillna(0, inplace=True)
    print 'cleaned'



    # Create and run the algorithm.
    algo = TradingAlgorithm(initialize=initialize, handle_data=handle_data,
                             identifiers=tickerList)
    results = algo.run(data)
    #
     # Plot the portfolio and asset data.
     # analyze(results=results)
