package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Cursus;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

public class JpaCursusRepository implements CursusRepository {
    private final EntityManager manager;

    public JpaCursusRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Cursus> findById(UUID id) {
        return Optional.ofNullable(manager.find(Cursus.class, id));
    }

    @Override
    public void create(Cursus cursus) {
        manager.persist(cursus);
    }
}
