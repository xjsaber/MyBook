package com.xjsaber.java.thread.code.ch2.cinema;

/**
 * @author xjsaber
 */
public class TicketOffice2 implements Runnable {

    private Cinema cinema;

    public TicketOffice2(Cinema cinema){
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTicket2(2);
        cinema.sellTicket2(4);
        cinema.sellTicket1(2);
        cinema.sellTicket1(1);
        cinema.returnTicket2(2);
        cinema.sellTicket1(3);
        cinema.sellTicket2(2);
        cinema.sellTicket1(2);
    }
}
