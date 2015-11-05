from zipline.api import order, record, symbol


def initialize(context):
	print "Buy Apple initialize"
    pass


def handle_data(context, data):
	print "Buy Apple handle_data"
    order(symbol('AAPL'), 10)
    record(AAPL=data[symbol('AAPL')].price)