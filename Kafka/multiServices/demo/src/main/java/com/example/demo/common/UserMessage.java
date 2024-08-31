package com.example.demo.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMessage {

    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String phone;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
}
