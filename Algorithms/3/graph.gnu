set title "NIM Algorithm Analysis"
set ylabel "Time Taken"
set xlabel "Problem Size - n"
#set autoscale
set logscale y 2

set term pngcairo
set output "output.png"

#plot "firstTest.csv" using 1:2 with lines title 'Recursive', \
#"firstTest.csv" using 1:3 with lines title 'Memoized Recursive', \
#"firstTest.csv" using 1:4 with lines title 'DP'

set logscale x 2
plot "secondTest.csv" using 1:2 with lines title 'Memoized Recursive', \
"secondTest.csv" using 1:3 with lines title 'DP'

