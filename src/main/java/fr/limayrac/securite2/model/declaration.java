package fr.limayrac.securite2.model;

import lombok.Data;

import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "declaration")
public class declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Date date;

    @Column(nullable = false, unique = true, length = 50)
    private String lieu;

    @Column(nullable = false, unique = true, length = 50)
    private String intitulé;

    
}
