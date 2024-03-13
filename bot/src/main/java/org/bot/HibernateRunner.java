package org.bot;

import lombok.Setter;
import org.bot.data.UserContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    @Setter
    private static Configuration configuration;

    public static void dbAdd(UserContext userContext) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(userContext);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserContext getById(Integer itemId) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            return session.get(UserContext.class, itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dbDrop(Integer itemId) {
        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            UserContext userContext = session.get(UserContext.class, itemId);
            if (userContext != null) {
                session.delete(userContext);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
