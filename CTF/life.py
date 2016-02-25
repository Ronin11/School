import socket
import gameOfLife

hostname = "144.39.229.43"
port = 1970
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((hostname, port))
#data = bytes.fromhex('00000000')
s.send("\n") 
flag = s.recv(4096)
print flag
#string = ""

generations = 0
temp = flag.split(" ")
for i in range(0, len(temp)):
    if(temp[i] == "Generations"):
        generations = int(temp[i-1])

Z = []
tempArray = []
header = ""
things = 0
for item in flag:
    if item == "\n":
        things += 1;
    if things > 5:
        if(item == " "):
            tempArray.append(0)
            #string += "0"
        elif(item == "*"):
            tempArray.append(1)
            #string += "1"
        elif(item == "\n"):
            Z.append(tempArray[:])
            tempArray = []
            ##string += "\n"
        #if(item != "#") and item != "\n":
            #string += " "
    if things < 5:
        header += item
header += "\n"
del Z[0]
del Z[-1]

#gameOfLife.setZ(Z)
newval = gameOfLife.run(generations, Z)
#print newval
answer = ""

for i in range(0, len(newval[0])+2):
    answer += "#"
answer += "\n"
for arr in newval:
    answer += "#"
    for item in arr:
        if(item == 0):
            answer += " "
        elif(item == 1):
            answer += "*"
    answer += "#\n"

for i in range(0, len(newval[0])+2):
    answer += "#"
#answer = header + answer
print answer


s.send(answer) 
flag = s.recv(4096)
print flag
s.close()