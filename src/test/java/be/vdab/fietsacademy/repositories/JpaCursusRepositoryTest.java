package be.vdab.fietsacademy.repositories;

import be.vdab.fietsacademy.domain.Cursus;
import be.vdab.fietsacademy.domain.GroepsCursus;
import be.vdab.fietsacademy.domain.IndividueleCursus;
import be.vdab.fietsacademy.repository.JpaCursusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertCursus.sql")
@Import(JpaCursusRepository.class)
public class JpaCursusRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final LocalDate EEN_DATUM = LocalDate.of(2019, 1,1);
    private static final String GROEPS_CURSUSSEN = "groepscursussen";
    private static final String INDIVIDUELE_CURSUSSEN = "individuelecursussen";

    @Autowired
    private JpaCursusRepository cursusRepository;
    @Autowired private EntityManager manager;

    private String idVanTestGroepsCursus(){
        return super.jdbcTemplate.queryForObject("select id from groepscursussen where naam = 'testGroep'", String.class);
    }

    private String idVanTestIndividueleCursus(){
        return super.jdbcTemplate.queryForObject("select id from individuelecursussen where naam = 'testIndividueel'", String.class);
    }

    @Test
    public void findGroepsCursusById(){
        Optional<Cursus> optionalCursus = cursusRepository.findById(idVanTestGroepsCursus());
        assertThat(((GroepsCursus) optionalCursus.get()).getNaam()).isEqualTo("testGroep");
    }

    @Test
    public void findIndividueleCursusById(){
        Optional<Cursus> optionalCursus = cursusRepository.findById(idVanTestIndividueleCursus());
        assertThat(((IndividueleCursus) optionalCursus.get()).getNaam()).isEqualTo("testIndividueel");
    }

    @Test
    public void findByOnbestaandeId(){
        assertThat(cursusRepository.findById("")).isNotPresent();
    }

    @Test
    public void createGroepsCursus() {
        GroepsCursus cursus = new GroepsCursus("testGroep2", EEN_DATUM, EEN_DATUM);
        cursusRepository.create(cursus);
        manager.flush();
        assertThat(super.countRowsInTableWhere(GROEPS_CURSUSSEN,
                "id='" + cursus.getId() + "'")).isOne();
    }
    @Test
    public void createIndividueleCursus() {
        IndividueleCursus cursus = new IndividueleCursus("testIndividueel2", 7);
        cursusRepository.create(cursus);
        manager.flush();
        assertThat(super.countRowsInTableWhere(INDIVIDUELE_CURSUSSEN,
                "id='" + cursus.getId() + "'")).isOne();
    }
}
