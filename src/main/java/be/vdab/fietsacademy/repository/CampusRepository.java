package be.vdab.fietsacademy.repository;

import be.vdab.fietsacademy.domain.Campus;

import java.util.Optional;

public interface CampusRepository {
    void create(Campus campus);
    Optional<Campus> findById(long id);
}
