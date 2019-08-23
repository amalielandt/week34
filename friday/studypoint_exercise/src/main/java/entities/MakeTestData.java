/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sofieamalielandt
 */
public class MakeTestData {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//        EntityManager em = emf.createEntityManager();
        CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
        
            List<BankCustomer> bc = cf.getAllBankCustomers();
            
            for (BankCustomer bankCustomer : bc) {
                
                System.out.println(bankCustomer.getFirstName());
            
        }
            
        
//        List<BankCustomer> bankCustomers = new ArrayList();
//        bankCustomers.add(new BankCustomer("Amanda", "Hansen", "11134534554", 100.5, 5, "Unknown"));
//        bankCustomers.add(new BankCustomer("Amalie", "Landt", "35453455543", 30000.5, 4, "Unknown"));
//        bankCustomers.add(new BankCustomer("Laura", "Nielsen", "34239434438", 1000.0, 3, "Unknown"));
//        bankCustomers.add(new BankCustomer("Jonas", "Haslund", "897567657533", 8600.5, 2, "Unknown"));
//
//        for (BankCustomer bankCustomer : bankCustomers) {
//
//            em.getTransaction().begin();
//            em.persist(bankCustomer);
//            em.getTransaction().commit();
//        }
//
//        em.close();
//        emf.close();

        
    }

}
