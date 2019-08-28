package be.vdab.fietsacademy.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "groepscursussen")
public class GroepsCursus extends Cursus {
    private static final long serialVersionUID = 1L;
    private LocalDate van;
    private LocalDate tot;

    protected GroepsCursus(){
    }

    public GroepsCursus(LocalDate van, LocalDate tot) {
        this.van = van;
        this.tot = tot;
    }

    public GroepsCursus(String naam, LocalDate van, LocalDate tot) {
        super(naam);
        this.van = van;
        this.tot = tot;
    }

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}
