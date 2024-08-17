package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.example.demo.validations.OnAdd;
import com.example.demo.validations.OnUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pet")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Null(groups = {OnAdd.class})
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @Pattern(regexp = "^[\\d]{1,3}\\.[\\w]{4}$")
    private String tag;

    private String name;
    private String species;

    @Min(1)
    @Max(10)
    private Integer age;
}
