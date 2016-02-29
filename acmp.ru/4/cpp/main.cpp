#include <fstream>

int main() {
	std::ifstream i("input.txt");
	int a;
	i >> a;
	i.close();

	std::ofstream o("output.txt");
	o << a << 9 << 9 - a;
	o.close();

	return 0;
}
