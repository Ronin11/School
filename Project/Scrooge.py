from zipline.api import order_target, record, symbol, history, add_history
from sklearn import linear_model
from sklearn.ensemble import RandomForestRegressor

# The initialize function is the place to set your tradable universe and define any parameters. 
def initialize(context):
    context.stocks = symbols('AAPL')
    context.vwap = {}
    context.price = {}
    context.estimatedPrice = 0

# Will be called on every trade event for the securities you specify. 
def handle_data(context, data):
    # Implement your algorithm logic here.
        # This runs through each stock.  It computes
    # our position at the start of each frame.
    
    price_history = history(bar_count=100, frequency='1d', field='price')
    volume_history = history(bar_count=100, frequency='1d', field='volume')
    for stock in context.stocks:
        #price = data[stock].price 
        #notional = notional + context.portfolio.positions[stock].amount * price
        #tradeday = data[stock].datetime
        training_X = []
        training_Y = []
            
        price_data = price_history[stock].values
        volume_data = volume_history[stock].values
            
        for index in range(0, price_data.size-1):
            training_X.append([price_data[index], volume_data[index]])
                
        for index in range(1, price_data.size):
            training_Y.append(price_data[index]) #

        clf = RandomForestRegressor(n_estimators=100)
        clf.fit (training_X, training_Y)
        est_price = clf.predict([data[stock].price, data[stock].volume])
        error = (abs(data[stock].price-context.estimatedPrice)/data[stock].price)*100
        record(error=error)
        print("Price: " + price_data)
        print("Est Price: " + est_price)
        print("Error: " + error)

        #est_price = context.estimatedPrice, price = data[stock].price,
        context.estimatedPrice = est_price

    
    
    
    
    
