package ru.job4j.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    /**
     * при покупке билета может быть указана неправильное время сеанса,
     * метод buy в таком случае должен выкидывать exception
     */
    @Test(expected = IllegalDateException.class)
    public void buyNotExistDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 22, 50);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    /**
     * при покупке билета может быть указано несуществующее место в кинозале,
     * метод buy в таком случае должен выкидывать exception
     */
    @Test(expected = IllegalSeatException.class)
    public void buyNotExistSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 20, 99, date);
    }

    /**
     * при покупке билета может быть указано уже купленное место в кинозале,
     * метод buy в таком случае должен просить выбрать другое место,
     * а список билетов для этого сеанса должен содержать только первый купленный билет
     */
    @Test
    public void buySeatTaken() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticketOne = cinema.buy(account, 1, 1, date);
        Ticket ticketTwo = cinema.buy(account, 1, 1, date);
        List<Session> sessions = cinema.find(session -> true);
        List<Ticket> tickets = sessions.get(0).getTickets();
        assertTrue(tickets.contains(ticketOne));
        assertFalse(tickets.contains(ticketTwo));
    }

    /**
     * интерфейс Cinema реализует два класса, отвечающие за классические сеансы кино и кино в 3D(Cinema2D.class & Cinema3D.class)
     * Каждый этот класс взаимодействует со своим видом сеансов(Session2D.class & Session3D.class)
     * В программе должна исключаться возможность работать Cinema3D c Session2D и наоборот
     */
    @Test(expected = IllegalSessionException.class)
    public void addIncorrectSession() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session2D());
        List<Session> sessions = cinema.find(session -> true);
    }
}