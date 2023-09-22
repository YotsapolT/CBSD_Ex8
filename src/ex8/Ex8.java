/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex8;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tong
 */
public class Ex8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createData();
        //printAllCustomer();
        printCustomerByCity("Bangkok");
        
    }
    
    public static void createData(){
        Customer c1 = new Customer(1L, "John", "Henry", "jh@mail.com");
        Address a1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH", c1);
        
        Customer c2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Address a2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH", c2);
        
        Customer c3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Address a3 = new Address(3L, "123/4 Viphavadee Rd.", "Nonthaburi", "20900", "TH", c3);
        
        Customer c4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        Address a4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH", c4);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex8PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(c1);
            em.persist(a1);
            em.persist(c2);
            em.persist(a2);
            em.persist(c3);
            em.persist(a3);
            em.persist(c4);
            em.persist(a4);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void printAllCustomer(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex8PU");
        EntityManager em = emf.createEntityManager();
        for (Customer cust : (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList()){
            System.out.println("---------------------------");
            System.out.print(cust);
            System.out.println("---------------------------");
        }
    }
    
    public static void printCustomerByCity(String city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex8PU");
        EntityManager em = emf.createEntityManager();
        for (Address addr : (List<Address>) em.createNamedQuery("Address.findByCity").setParameter("city", city).getResultList()){
            System.out.println("---------------------------");
            System.out.print(addr);
            System.out.println("---------------------------");
        }
    }
}
