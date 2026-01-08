package edu.icet.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer nic;
    private String address;
    private String phone;
    private String city;
}
