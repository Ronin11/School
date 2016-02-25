import socket, sys

hostname = "144.39.229.43"
port = 2211
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((hostname, port))
#s.send(str(sys.argv[2]) + "\n")    
for i in range(0, 102):
    flag = s.recv(4096)
    #print flag
    flag = flag.split(" ")
    string = ""
    if flag[2] == "last":
        for j in reversed(range(0, int(flag[3]))):
            string += str(flag[len(flag)-j-1]) + " "
    elif flag[2] == "first":
        for j in range(0, int(flag[3])):
            string += str(flag[5+j]) + " "
    string = string.rstrip()
    if not string.endswith("\n"):
        string += "\n"
    #print string
    s.send(string)

s.close()