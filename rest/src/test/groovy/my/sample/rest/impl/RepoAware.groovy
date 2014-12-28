package my.sample.rest.impl

import my.sample.model.api.Item
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
abstract class RepoAware extends Specification {
    EntityManagerFactory emf
    EntityManager em

    JpaItemRepository repo

    def internalSetup() {
        emf = Persistence.createEntityManagerFactory("test-unit")
        em = emf.createEntityManager()
        repo = new JpaItemRepository()
        repo.em = em
        em.getTransaction().begin()

        (1..3).each { repo.add(new Item("$it", "Item #$it", "Description for item #$it")) }
    }

    def internalCleanup() {
        em.getTransaction().rollback()
        em.close()
        emf.close()
    }
}
