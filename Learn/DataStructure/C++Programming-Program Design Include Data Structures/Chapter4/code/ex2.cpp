#include <iostream>
#include <fstream>
#include <iomanip>

int main() {
    std::ifstream inFile; //input file stream variable
    std::ofstream outFile; //output file stream variable
    int test1, test2, test3, test4, test5;
    double average;
    char studentId;

    inFile.open("a:test.txt"); //open input file

    if (!inFile) {
        std::cout <<"Cannot open input file. "
                  <<"The program teminates."<< std::endl;
        return 1;
    }

    outFile.open("a:textavg.out"); // open output file

//    outFile << fixed << showpoint;
//    outFile << setprecision(2);

    outFile << "Processing data" << std::endl;

    inFile >> studentId;

    outFile << "Student Id: " << studentId << std::endl;

    inFile >> test1 >> test2 >> test3 >> test4 >> test5;

    outFile << "Test scores: " << std::setw(4) << test1
            << std::setw(4) << test2 << std::setw(4) << test3
            << std::setw(4) << test4 << test5 << std::endl;

    average = static_cast<double>(test1 + test2 + test3 + test4 + test5) / 5.0;

    outFile << "Averge test score:  " << std::setw(6) << average << std::endl;

    inFile.close();
    outFile.close();

    return 0;
}