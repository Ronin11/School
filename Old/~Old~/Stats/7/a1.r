Diet<-read.csv("http://www.math.usu.edu/cfairbourn/Stat2300/RStudioFiles/data/Diet.csv")

#require the necessary packages
require(MASS)
require(mosaic)
require(openintro)

#read in the grocery data
grocery<-read.csv("http://www.math.usu.edu/cfairbourn/Stat2300/RStudioFiles/data/grocery.csv")

#create a vector of the difference between Dave's prices and the competitor
diff<-grocery$compete-grocery$dave

#conduct a t-test on the differences
t.test(diff, mu=0, alternative="greater")

#another command that does the same thing:
t.test(grocery$compete, grocery$dave, mu=0, alternative="greater", paired=TRUE)

#conduct a t-test for the typing example
#enter the ergonomic data
ergo<-c(71,104,79,100,99,83,77,93,90,75)

#enter the standard data
standard<-c(72,93,83,80,91,76,81,94,77,67)

#calculate the differences
diff<-ergo-standard

#conduct the t-test
t.test(diff, mu=0, alternative="two.sided")
#or, alternately
t.test(ergo, standard, mu=0, paired=TRUE)