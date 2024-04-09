package com.example.eshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
//@Entity
//@Table
public class EmailMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String to;
    private String subject;
    private String message;
//    private Integer code;
//    private boolean isVerify = false;
//    private LocalDateTime codeGeneratedTime;

}
