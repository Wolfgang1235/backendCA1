package facades;

import entities.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PhoneFacade {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {}

    public static PhoneFacade getPhoneFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Phone createPhone(Phone phone) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }

    public List<Phone> getAllPhones() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Phone getPhoneByPhoneNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Phone p WHERE p.number = :phoneNumber", Phone.class);
            query.setParameter("phoneNumber", number);
            return (Phone) query.getSingleResult();
        } finally {
            em.close();
        }
    }

}
