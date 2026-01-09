package edu.icet.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private Double nic;
    private String address;
    private String phone;
    private String city;
}
