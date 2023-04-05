all: threads.exe

threads.exe: threads.o
	 g++ -o threads.exe threads.o -pthread

threads.o: threads.cpp
	 g++ -c threads.cpp
     
clean:
	 rm threads.o threads.exe
