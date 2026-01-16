package com.book.my.show.service;

import com.book.my.show.DTO.CreateShowRequest;
import com.book.my.show.entity.Seat;
import com.book.my.show.entity.Show;
import com.book.my.show.entity.ShowSeat;
import com.book.my.show.enums.SeatStatus;
import com.book.my.show.repository.SeatRepository;
import com.book.my.show.repository.ShowRepository;
import com.book.my.show.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;

    public ShowService(
            ShowRepository showRepository,
            SeatRepository seatRepository,
            ShowSeatRepository showSeatRepository
    ) {
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional
    public Show createShow(CreateShowRequest request) {
        Show show = new Show();
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show = showRepository.save(show);

        // Create ShowSeat for each seat in screen
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat : seats) {
            ShowSeat ss = new ShowSeat();
            ss.setShow(show);
            ss.setSeat(seat);
            ss.setStatus(SeatStatus.AVAILABLE);
            showSeatRepository.save(ss);
        }

        return show;
    }

    public List<Show> getShowsByMovie(UUID movieId) {
        return showRepository.findByMovieId(movieId);
    }
}

