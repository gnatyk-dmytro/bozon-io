package org.bot;

import org.bot.data.UserContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    private final Configuration configuration;

    public HibernateRunner(Configuration configuration) {
        this.configuration = configuration;
    }

    public void dbAdd(UserContext userContext) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(userContext);
            transaction.commit();
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public UserContext getById(Integer itemId) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            return session.get(UserContext.class, itemId);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    public void dbDrop(Integer itemId) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            UserContext userContext = session.get(UserContext.class, itemId);
            if (userContext != null) {
                session.delete(userContext);
                transaction.commit();
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}
