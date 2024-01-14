package fr.limayrac.securite2.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "declaration")
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 50)
    private String numDossier;

    @ManyToOne
    @JoinColumn(name = "user", unique=false, nullable = false)
    private User idUser;

    @Column(nullable = false, unique = false, length = 50)
    private String date_formation;

    @Column(nullable = false, unique = false, length = 50)
    private String lieu_formation;

    @Column(nullable = false, unique = false, length = 50)
    private String intitule;

    @Column(nullable = false, unique = false, length = 50)
    private String moyenTransport;

    @Column(nullable = false, unique = false,length = 50)
    private String dateTransport;
    
    @Column(nullable = false, unique = false, length = 50)
    private String pointDepart;
    
    @Column(nullable = false, unique = false, length = 50)
    private String destination;

    @Column(nullable = false, unique = false, length = 50)
    private String prixTransport;
    
    @Column(nullable = false, unique = false, length = 50)
    private String typeHebergement;
    
    @Column(nullable = false, unique = false, length = 50)
    private String lieuHebergement;

    @Column(nullable = false, unique = false, length = 50)
    private String prixHebergement;

    @Column(nullable = false, unique = false, length = 50)
    private String justificatifHebergement;

    @Column(nullable = false, unique = false, length = 50)
    private String typeRestauration;
    
    @Column(nullable = false, unique = false, length = 50)
    private String justificatifRestauration;
    
    @Column(nullable = false, unique = false, length = 50)
    private String prixRestauration;

    @Column(nullable = false, unique = false, length = 50)
    private String iban;

    @Column(nullable = false, unique = false, length = 50)
    private String statut;
}
