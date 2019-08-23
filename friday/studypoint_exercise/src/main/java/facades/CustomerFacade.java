package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(long id) {

        EntityManager em = getEntityManager();
        BankCustomer customer = em.find(BankCustomer.class, id);

        return new CustomerDTO(customer);
    }

    public List<CustomerDTO> getCustomerByName(String name) {

        EntityManager em = emf.createEntityManager();
        List<CustomerDTO> customers = new ArrayList();

        TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstName = :name OR c.lastName = :name", BankCustomer.class);
        query.setParameter("name", name);

        for (BankCustomer customer : query.getResultList()) {

            customers.add(new CustomerDTO(customer));
        }
        return customers;
    }

    public BankCustomer addCustomer(BankCustomer customer) {

        EntityManager em = getEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();

            return customer;

        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers() {
        
        EntityManager em = getEntityManager();

        TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM BankCustomer c", BankCustomer.class);
        return query.getResultList();

    }
    
       public void removeEmployee(BankCustomer c) {
         
        EntityManager em = getEntityManager();

        try {
            
            em.getTransaction().begin();
            BankCustomer cus = em.merge(c);
            em.remove(cus);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}
