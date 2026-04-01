package org.example.nextask.dao;

import jakarta.persistence.EntityManager;
import org.example.nextask.model.Kategorie;
import org.example.nextask.util.JPAUtil;

public class KategorieDAO {
    public Kategorie searchKategorieById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Kategorie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createKategorie (Kategorie kategorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(kategorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateKategorie (Kategorie kategorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(kategorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteKategorie (int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Kategorie kategorie = em.find(Kategorie.class, id);
            if (kategorie != null) {
                em.remove(kategorie);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
