package org.example.nextask.dao;

import jakarta.persistence.EntityManager;
import org.example.nextask.model.ToDo;
import org.example.nextask.util.JPAUtil;

public class ToDoDAO {
    public ToDo searchToDoById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(ToDo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createToDo(ToDo todo) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateToDo(ToDo todo) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteToDo(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            ToDo todo = em.find(ToDo.class, id);
            if (todo != null) {
                em.remove(todo);
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
