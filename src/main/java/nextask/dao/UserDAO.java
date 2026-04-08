package nextask.dao;

import jakarta.persistence.EntityManager;
import nextask.model.User;
import nextask.util.JPAUtil;

import java.util.List;

public class UserDAO {
    public User searchUserById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User searchUserByUsername(String username) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT u FROM User u WHERE u.Username = :Username", User.class)
                    .setParameter("Username", username)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User searchUserByEmail(String email) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT u FROM User u WHERE u.Email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void createUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
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

    public void updateUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
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

    public void deleteUser(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ToDo t WHERE t.User.UserID = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            em.createQuery("DELETE FROM Kategorie k WHERE k.User.UserID = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
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
