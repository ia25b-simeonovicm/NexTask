package org.example.nextask.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class JPAContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Wird beim Serverstart ausgeführt
        JPAUtil.getEntityManagerFactory();
        System.out.println("Datenbankverbindung erfolgreich aufgebaut!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Wird beim Serverstopp ausgeführt
        JPAUtil.shutdown();
        System.out.println("Datenbankverbindung geschlossen.");
    }
}