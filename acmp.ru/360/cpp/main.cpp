#include <fstream>
 
const int LINES_STANDBY_COUNT = 5;
 
typedef short int TCell;
 
class StreamMatrix {
    private:
        unsigned short int size;
        int max = -401;
        TCell** window;
        short unsigned int window_size;
         
        std::ifstream* data_source;
         
        static const TCell empty        = -401;
        static const TCell min_infinity = -401;
         
        const int trace[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
         
        enum trace_in { right, left, bottom, top, center };
         
        int reverse_trace(int trace) {
            if (trace == 0) return 1;
            if (trace == 1) return 0;
            if (trace == 2) return 3;
            if (trace == 3) return 2;
        }
 
        int search_cell_max(int x, int y, int trace_exclude, unsigned short int depth) {
            if (depth < 2) {
                int max = this->min_infinity;
                int sum = this->get(x, y);
                 
                for (int t = 0; t < 4; t++) {
                    if (t != trace_exclude) {
                        int sub_sum = this->search_cell_max(x + this->trace[t][0], y + this->trace[t][1], this->reverse_trace(t), depth + 1);
                        if (sub_sum != this->empty && (sum + sub_sum > max)) {
                            max = sum + sub_sum;
                        }
                    }
                }
                return max;
            }
            else if (depth == 2) {
                return this->get(x, y);
            }
 
            return this->min_infinity;
        }
         
        TCell get(short int x, short int y) {
            return (x >= 0 && x < this->size && y >=0 && y < this->window_size) 
                ? this->window[y][x]
                : this->empty;       
        }
         
        void read_line(int window_line) {
            for (int j = 0; j < this->size; j++) {
                *this->data_source >> this->window[window_line][j];
            }
        }
     
        void move_window() {
            this->read_line(0);
            TCell* new_line = this->window[0];
             
            if (this->window_size > 3) {
                for (int i = 0; i < this->window_size - 1; i++) {
                    this->window[i] = this->window[i + 1];
                }
                this->window[this->window_size - 1] = new_line;
            }
        }
         
        void search_by_line(int line) {
            for (int i = 0; i < this->size; i++) {
                int line_max = this->search_cell_max(i, line - 1, center, 0);
                if (line_max > this->max) {
                    this->max = line_max;
                }
            }
        }   
         
    public:
        StreamMatrix(int n, std::ifstream* input) {
            this->size        = n;
            this->window_size = n < LINES_STANDBY_COUNT ? n : LINES_STANDBY_COUNT;
            this->window      = new TCell*[this->window_size];
            this->data_source = input;
        }
         
        ~StreamMatrix() {
            for (int i = 0; i < this->window_size; i++) {
                delete[] this->window[i];
            }
            delete[] this->window;
        }
             
        void search_max() {
            int current_line = 1;
            int more_lines = this->size - this->window_size;
            while (current_line <= this->window_size) {
                this->search_by_line(current_line);
                if (3 == current_line && more_lines > 0) {
                    this->move_window();
                    more_lines--;
                }
                else {
                    current_line++;
                }
            }
        }
         
        void prepare() {
            for (int i = 0; i < this->window_size; i++) {
                this->window[i] = new TCell[this->size];
                this->read_line(i);
            }
        }
 
        int get_max() {
            return this->max;
        }
};
 
int main() {
    std::ifstream input_file("input.txt");
    int N;
    input_file >> N;
     
    StreamMatrix m(N, &input_file);
    m.prepare();
    m.search_max();
  
    std::ofstream output_file("output.txt");
    output_file << m.get_max();
    input_file.close();
    output_file.close();
  
  
    return 0;
}
