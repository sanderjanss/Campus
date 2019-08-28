package be.vdab.fietsacademy.repository;

import be.vdab.fietsacademy.domain.Cursus;

import java.util.Optional;

public interface CursusRepository {
    Optional<Cursus> findById(String id);
    void create(Cursus cursus);
}
