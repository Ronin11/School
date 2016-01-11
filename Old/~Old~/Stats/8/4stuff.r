Town<-read.csv("http://www.math.usu.edu/cfairbourn/Stat2300/RStudioFiles/data/Town.csv")


Inside<-subset(Town, Town$Bldg.Type=="Twnhs")
End<-subset(Town, Town$Bldg.Type =="TwnhsE")

InsideSale<-Inside$SalePrice
EndSale<-End$SalePrice

t.test(InsideSale, EndSale)