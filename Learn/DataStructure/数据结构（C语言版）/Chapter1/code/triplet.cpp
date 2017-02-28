//
// Created by xjsaber on 2017/2/21.
//

#include <cstdlib>
#include "triplet.h"

Status Triplet_Class::InitTriplet(Triplet &T, ElemType v1, ElemType v2, ElemType v3) {
    T = (ElemType *) malloc(3 * sizeof(ElemType));
    if (!T) exit(OVERFLOW);
    T[0] = v1, T[1] = v2;
    T[2] = v3;
    return OK;
} //InitTriplet

Status Triplet_Class::DestroyTriplet(Triplet &T) {
    free(T);
    T = NULL;
    return OK;
} //DestroyTriplet

Status Triplet_Class::Get(Triplet T, int i, ElemType &e) {
    if (i < 1 || i > 3) return ERROR;
    e = T[i - 1];
    return OK;
} //get

Status Triplet_Class::Put(Triplet T, int i, ElemType e) {
    if (i < 1 || i > 3) return ERROR;
    T[i - 1] = e;
    return OK;
} //put

Status Triplet_Class::IsAscending(Triplet T) {
    return (T[0] <= T[1]) && (T[1] <= T[2]);
}

Status Triplet_Class::Max(Triplet T, ElemType &e) {
    e = (T[0] >= T[1]) ? ((T[0] >= T[2]) ? T[0] : T[2])
                       : ((T[1] >= T[2]) ? T[1] : T[2]);
    return OK;
} //Max

Status Triplet_Class::Min(Triplet T, ElemType &e) {
    e = (T[0] <= T[1]) ? ((T[0] <= T[2]) ? T[0] : T[2])
                       : ((T[1] <= T[2]) ? T[1] : T[2]);
    return OK;
} //Min
