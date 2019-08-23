/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author sofieamalielandt
 */
public class EmployeeDTO {
    private long id;
    private String name;
    private String adress;

    public EmployeeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();
        this.adress = emp.getAdress();
    }
    
    
    
    
}
