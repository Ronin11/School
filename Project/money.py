class Account:
	STARTINGCASH = 100000
	CURRENTCASH = STARTINGCASH
	NETWORTH = CURRENTCASH
	DIVERSIFICATION = 10
	ASSETS = []

	#[TICKER, #OFSHARES, COSTATPURCHASE]

	def getTotalReturns():
		return (CURRENTCASH/STARTINGCASH-1)*100

	def getDiversifiedCash():
		return (NETWORTH/DIVERSIFICATION)

	def sellAsset(asset, quantity):
		if asset in ASSETS
			print "Selling " asset + " quantity: " + quantity

	def buyAsset(asset, quantity):
		