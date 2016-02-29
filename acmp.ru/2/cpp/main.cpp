#include <fstream>

int main() {
	std::ifstream i("input.txt");
	int n;
	i >> n;
	i.close();

	int s = 0;
	int j = 1;
	int k = n > 0 ? 1 : -1;

	while (j != n) {
		s += j;
		j += k;
	}
	s += n;

	std::ofstream o("output.txt");
	o << s;
	o.close();

	return 0;
}
