package be.vdab.fietsacademy.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/insertDocent.sql")
public class DefaultDocentServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private DefaultDocentService service;
    @Autowired
    private EntityManager manager;

    private long idVanTestMan() {
        return super.jdbcTemplate.queryForObject(
                "select id from docenten where voornaam='testM'", Long.class);
    }

    @Test
    public void opslag() {
        long id = idVanTestMan();
        service.opslag(id, BigDecimal.TEN);
        manager.flush();
        assertThat(super.jdbcTemplate.queryForObject(
                "select wedde from docenten where id=?", BigDecimal.class, id))
                .isEqualByComparingTo("1100");
    }
}