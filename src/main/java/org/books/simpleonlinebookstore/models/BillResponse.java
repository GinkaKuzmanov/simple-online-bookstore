package org.books.simpleonlinebookstore.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class BillResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    @NonNull
    private String username;
    @NonNull
    private Double currentBill;

}
