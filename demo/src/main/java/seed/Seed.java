package seed;

import demo.spring.models.persistence.User;
import demo.spring.utilities.CryptoUtility;

import javax.persistence.*;

import java.util.UUID;

public class Seed {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction entr = em.getTransaction();
            Query q = em.createNativeQuery("delete from users");
            entr.begin();
            q.executeUpdate();
            em.persist(createUser("superuser", "1234"));
            em.persist(createUser("superuser2", "1234"));
            em.persist(createUser("superuser3", "1234"));
            em.persist(createUser("superuser4", "1234"));
            entr.commit();
        } finally {
            em.close();
        }
    }

    private static User createUser(String userName, String password) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setLoginName(userName);
        CryptoUtility util = new CryptoUtility();
        String salt = util.generateSalt();
        String hash = util.generateHash(password, salt);
        user.setPasswordHash(hash);
        user.setSalt(salt);
        return user;
    }
}
