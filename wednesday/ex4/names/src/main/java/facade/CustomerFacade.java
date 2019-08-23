/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sofieamalielandt
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }

        return instance;
    }

    public Customer findById(long id)
    {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        return customer;
    }
    
    public List<Customer> findByLastname(String name) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastname = :lastname", Customer.class);
        query.setParameter("lastname", name);
        return query.getResultList();
    }

    public long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT COUNT(c) FROM Customer c");

        long count = (long) q.getSingleResult();
        return count;
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer addCustomer(String firstname, String lastname) {
        Customer c = new Customer(firstname, lastname);
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();

            return c;

        } finally {
            em.close();
        }
    }

    public void removeCustomer(Customer c) {
         
        EntityManager em = emf.createEntityManager();

        try {
            
            em.getTransaction().begin();
            Customer cus = em.merge(c);
            em.remove(cus);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}
