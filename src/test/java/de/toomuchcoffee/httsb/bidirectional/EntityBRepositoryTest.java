package de.toomuchcoffee.httsb.bidirectional;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureEmbeddedDatabase(provider = DOCKER)
class EntityBRepositoryTest {

    @Autowired
    private EntityBRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldResolveReverseSideOfRelationship() {
        EntityA entityA = new EntityA();
        entityManager.persist(entityA);

        EntityB entityB = new EntityB();
        entityB.setA(entityA);
        repository.save(entityB);

        assertThat(entityA.getBs()).isNullOrEmpty();

        entityManager.refresh(entityA); // <-- this is where the magic happens

        assertThat(entityA.getBs()).hasSize(1);
    }
}