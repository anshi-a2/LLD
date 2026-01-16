package com.book.my.show.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Setter
@Getter
public class CreateShowRequest {
    private UUID movieId;
    private UUID screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

