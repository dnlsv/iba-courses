all: cfile.exe

cfile.exe: cfile.o
	 g++ -o cfile.exe cfile.o -pthread

cfile.o: cfile.cpp
	 g++ -c cfile.cpp
     
clean:
	 rm cfile.o cfile.exe
