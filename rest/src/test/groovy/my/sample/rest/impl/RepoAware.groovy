package my.sample.rest.impl

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

    ItemRepository repo

    def internalSetup() {
        emf = Persistence.createEntityManagerFactory("test-unit")
        em = emf.createEntityManager()
        repo = new ItemRepository()
        repo.setEntityManager(em)
        em.getTransaction().begin()
        repo.init()
    }

    def internalCleanup() {
        em.getTransaction().rollback()
        em.close()
        emf.close()
    }
}
