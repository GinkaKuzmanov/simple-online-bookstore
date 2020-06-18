package org.books.simpleonlinebookstore.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp =LocalDateTime.now();
    @NonNull
    private String error;

    @NonNull
    private String message;
}
