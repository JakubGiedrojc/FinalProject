package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class ErrorMessage {
   private String message;

   public ErrorMessage(String message) {
      System.out.println("User with this login or email address already exists!");;
   }
}
