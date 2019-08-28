package be.vdab.fietsacademy.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="campussen")
public class Campus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String naam;
    @Embedded
    private Adres adres;

    protected Campus() {
    }

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
    }

    public long getId() {
        return Id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }
}
