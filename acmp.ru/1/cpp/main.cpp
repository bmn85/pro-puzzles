#include <fstream>

int main() {
	std::ifstream i("input.txt");
	int a, b;
	i >> a >> b;
	i.close();

	std::ofstream o("output.txt");
	o << a + b;
	o.close();

	return 0;
}
