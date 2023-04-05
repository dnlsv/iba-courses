#include <iostream>
#include <thread>

using namespace std;

void threadFunc(int num)
{
	cout << "Thread id = "<< this_thread::get_id() << endl;
	this_thread::sleep_for(1000ms);
}

int main()
{
	cout << "Enter the number of threads" << endl;
	int num;
	cin >> num;

	thread* th = new thread[num];

	for (int i = 0; i < num; i++)
	{
		th[i] = thread(threadFunc, i);
	}

	for (int i = 0; i < num; i++)
	{
		th[i].join();
	}

	return 0;
}
