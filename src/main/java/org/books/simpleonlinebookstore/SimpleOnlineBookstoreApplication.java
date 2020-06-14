package org.books.simpleonlinebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleOnlineBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleOnlineBookstoreApplication.class, args);
//        User user = new User();
//       double sum =   user.getMusic().stream().map(
//                music -> {
//                    Double result = 0d;
//                    result += music.calculatePrice();
//                    return result;
//                }
//        ).mapToDouble(Double::doubleValue).sum();
    }

}
