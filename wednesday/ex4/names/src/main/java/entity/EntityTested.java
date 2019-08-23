/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import facade.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sofieamalielandt
 */
public class EntityTested {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);

//        cf.addCustomer("Katrine", "Landt");
//        cf.addCustomer("Laura", "Nielsen");

        List<Customer> customers = cf.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        
        long numberOfCustomers = cf.getNumberOfCustomers();
        System.out.println("Number of customers: " + numberOfCustomers);
        
        List<Customer> byLastname = cf.findByLastname("Landt");
        for (Customer customer : byLastname) {
            System.out.println(customer);
        }
        
        Customer byId = cf.findById(4);
        System.out.println(byId);

    }

}
