package com.xjsaber.java.thread.code.ch2.cinema;

/**
 * @author xjsaber
 */
public class Cinema {

    private long vacanciesCinema1;
    private long vacanciesCinema2;

    private final Object controlCinemal1, controlCinemal2;

    Cinema(){
        controlCinemal1 = new Object();
        controlCinemal2 = new Object();
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
    }

    public boolean sellTicket1(int number){
        synchronized (controlCinemal1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema1 -= number;
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean sellTicket2(int number){
        synchronized (controlCinemal1) {
            if (number < vacanciesCinema1) {
                vacanciesCinema2 -= number;
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean returnTicket1(int number){
        synchronized (controlCinemal1) {
            vacanciesCinema1 += number;
            return true;
        }
    }

    public boolean returnTicket2(int number){
        synchronized (controlCinemal2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    public long getVacanciesCinema1(){
        return vacanciesCinema1;
    }

    public long getVacanciesCinema2(){
        return vacanciesCinema2;
    }
}
