package be.vdab.fietsacademy.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.vdab.fietsacademy.domain.Geslacht;
import be.vdab.fietsacademy.repository.JpaDocentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaDocentRepository.class)
@Sql("/insertDocent.sql")
public class JpaDocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    JpaDocentRepository repository;

    private long idVanTestMan() {
        return super.jdbcTemplate.queryForObject(
                "select id from docenten where voornaam = 'testM'", Long.class);
    }

    @Test
    public void findById(){
        assertThat(repository.findById(idVanTestMan()).get().getVoornaam()).isEqualTo("testM");
    }

    @Test
    public void findByOnbestaandeId(){
        assertThat(repository.findById(-1)).isNotPresent();
    }

    private long idVanTestVrouw() {
        return super.jdbcTemplate.queryForObject(
                "select id from docenten where voornaam='testV'", Long.class);
    }
    @Test
    public void man() {
        assertThat(repository.findById(idVanTestMan()).get().getGeslacht())
                .isEqualTo(Geslacht.MAN);
    }
    @Test
    public void vrouw() {
        assertThat(repository.findById(idVanTestVrouw()).get().getGeslacht())
                .isEqualTo(Geslacht.VROUW);
    }
}
