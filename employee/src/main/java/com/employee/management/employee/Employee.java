package com.employee.management.employee;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First name cannot be empty.")
    @Size(min = 5, max = 250)
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Email id cannot be empty.")
    private String emailId;

    @NotNull(message = "Age cannot be empty.")
    @Min(value = 18)
    @Max(value=60)
    private int age;


    private double salary;

}
