#include <fstream>

int main() {
	std::ifstream i("input.txt");
	int a;
	i >> a;
	i.close();

	int b = (a - 5) / 10;

	std::ofstream o("output.txt");
	o << b * (b + 1) << 25 << std::endl;
	o.close();

	return 0;
}
