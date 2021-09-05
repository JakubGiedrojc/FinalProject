package com.example.demo.services;


public interface MessageService {
    void sendEmail(int orderId, String title, String content);
}
