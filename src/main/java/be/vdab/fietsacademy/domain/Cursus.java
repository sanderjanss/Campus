package be.vdab.fietsacademy.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cursus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String naam;

    protected Cursus() {
    }

    public Cursus(String naam) {
        id = UUID.randomUUID().toString();
        this.naam = naam;
    }

    public String getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
