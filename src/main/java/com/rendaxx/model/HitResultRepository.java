package com.rendaxx.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ApplicationScoped
public class HitResultRepository {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager entityManager;

    @Transactional
    public void saveHit(HitResult hitResult) {
        entityManager.persist(hitResult);
    }

    public List<HitResult> getAllHits() {
        CriteriaQuery<HitResult> criteria = entityManager.getCriteriaBuilder().createQuery(HitResult.class);
        criteria.from(HitResult.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}
