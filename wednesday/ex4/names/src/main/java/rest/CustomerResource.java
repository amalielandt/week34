/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Customer;
import facade.CustomerFacade;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sofieamalielandt
 */
@Path("customer")
public class CustomerResource {
    
    Gson gson = new Gson();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
    List<Customer> customers = cf.getAllCustomers();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hej";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        //TODO return proper representation object
        return gson.toJson(customers);
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        //TODO return proper representation object
        Random r = new Random();
        Customer c = cf.findById(r.nextInt(customers.size())+1);
        
       return gson.toJson(c);
    }
    
      @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getId(@PathParam("id") long id) {
        //TODO return proper representation object
       Customer c = cf.findById(id);
       return gson.toJson(c);
    }

}
