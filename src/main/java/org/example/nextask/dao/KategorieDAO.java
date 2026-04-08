package org.example.nextask.dao;

import jakarta.persistence.EntityManager;
import org.example.nextask.model.Kategorie;
import org.example.nextask.util.JPAUtil;

import java.util.ArrayList;
import java.util.List;

public class KategorieDAO {
    public Kategorie searchKategorieById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Kategorie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Kategorie> getAllKategorieByUser(int UserID) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT t FROM Kategorie t WHERE t.User.UserID = :UserID", Kategorie.class)
                    .setParameter("UserID", UserID)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public Kategorie getAllKategorieByUserAndName(int userID, String name) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(
                            "SELECT k FROM Kategorie k WHERE k.User.UserID = :userID AND k.Name = :name",
                            Kategorie.class)
                    .setParameter("userID", userID)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Kategorie> getAllCategories() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT k FROM Kategorie k", Kategorie.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void createKategorie(Kategorie kategorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(kategorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateKategorie(Kategorie kategorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(kategorie);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteKategorie(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Kategorie kategorie = em.find(Kategorie.class, id);
            if (kategorie != null) {
                // Erst alle Todos dieser Kategorie löschen
                em.createQuery("DELETE FROM ToDo t WHERE t.Kategorie.KategorieID = :id")
                        .setParameter("id", id)
                        .executeUpdate();
                em.remove(kategorie);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
