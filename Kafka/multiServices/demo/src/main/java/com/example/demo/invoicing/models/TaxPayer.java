package com.example.demo.invoicing.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxPayer {
    
    @Id
    private Long id;

    private String fullName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String curp;
    private String rfc;
    
}
