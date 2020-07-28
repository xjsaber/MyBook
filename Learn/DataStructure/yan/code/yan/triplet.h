//
// Created by xjaber on 2017/2/21.
//

#ifndef C_TRIPLET_HS
#define C_TRIPLET_H
#define OK 1;
#define ERROR 0
#define OVERFLOW -1

typedef int ElemType;
typedef int Status;
typedef ElemType *Triplet;

class Triplet_Class {
    private:

    public:
        /*
         * 初始化
         */
        Status InitTriplet(Triplet &T, ElemType v1, ElemType v2, ElemType v3);

        Status DestroyTriplet(Triplet &T);

        Status Get(Triplet T, int i, ElemType &e);

        Status Put(Triplet T, int i, ElemType e);

        Status Max(Triplet T,ElemType &e);

        Status Min(Triplet T,ElemType &e);

        Status IsAscending(Triplet T);
};

#endif //C_TRIPLET_H
