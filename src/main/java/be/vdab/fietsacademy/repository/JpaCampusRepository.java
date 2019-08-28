package be.vdab.fietsacademy.repository;

import be.vdab.fietsacademy.domain.Campus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
public class JpaCampusRepository implements CampusRepository{
    private EntityManager manager;

    public JpaCampusRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Campus campus) {
        manager.persist(campus);
    }

    @Override
    public Optional<Campus> findById(long id) {
        return Optional.ofNullable(manager.find(Campus.class, id));
    }
}
