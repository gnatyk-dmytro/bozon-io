package org.bot;

import lombok.Setter;
import org.bot.data.UserContext;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.validation.constraints.NotNull;

public class HibernateRunner {

    @Setter
    @NotNull
    private static Configuration configuration;
    @NotNull
    private static final Session session = configuration.buildSessionFactory().openSession();

    public static void dbAdd(UserContext userContext) {
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(userContext);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserContext getById(Integer itemId) {
        try (session) {
            return session.get(UserContext.class, itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dbDrop(Integer itemId) {
        try (session) {
            Transaction transaction = session.beginTransaction();
            var userContext = session.get(UserContext.class, itemId);
            if (userContext != null) {
                session.delete(userContext);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
