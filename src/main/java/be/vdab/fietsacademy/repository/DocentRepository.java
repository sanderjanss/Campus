package be.vdab.fietsacademy.repository;

import be.vdab.fietsacademy.domain.Docent;

import java.util.Optional;

public interface DocentRepository {
    Optional<Docent> findById(long id);
    void create(Docent docent);
    void delete(long id);
}
