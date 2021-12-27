package com.AssessmentOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.CompanyExp;


public class OneToManyUniD {
	EntityManagerFactory emf = null;
	EntityManager entityManager = null;
	EntityTransaction transaction = null;
	
	void customerCreate() {
		
		try {
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();

		//Create
			//Parent
			Customer customer = new Customer();
			customer.setCustomerName("Ash");
			customer.setContactNo("2345678");
			//Child
			Account account1 = new Account();
			account1.setAccount_no(12945607);
			account1.setAmount(23456.78);
			account1.setStatus("Active");
			
			Account account2 = new Account();
			account2.setAccount_no(34055060);
			account2.setAmount(0);
			account2.setStatus("Inactive");

			List<Account> list = new ArrayList<Account>();
			list.add(account1);
			list.add(account2);
			
			customer.setListAccount(list);
		
		//Read
			
		//Update
			
		//Delete
			
			
			// save call
			entityManager.persist(customer);
			System.out.println("Company details successfull....");

		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		} finally {
			if (transaction != null) {
				transaction.commit();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		
	}
	
	void customerRead(int cid) {
		try{
		emf = Persistence.createEntityManagerFactory("corebanking");
		entityManager = emf.createEntityManager();
		transaction = entityManager.getTransaction();

		transaction.begin();
		
		Customer customer = entityManager.getReference(Customer.class, cid);
		
		System.out.println("The Customer Detail...");
		System.out.println(customer.getCid());
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getContactNo());
		
		List<Account> account = customer.getListAccount();
		
		for(Account a: account) {
			System.out.println(a.getAccount_id());
			System.out.println(a.getAccount_no());
			System.out.println(a.getAmount());
			System.out.println(a.getStatus());
		}
		
	}catch (Exception e) {
		System.out.println(e);
		transaction.rollback();
	}finally {
		if (transaction != null) {
			transaction.commit();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (emf != null) {
			emf.close();
		}
	}
}	
	void customerUpdate(int cid) {
		try{
		emf = Persistence.createEntityManagerFactory("corebanking");
		entityManager = emf.createEntityManager();
		transaction = entityManager.getTransaction();

		transaction.begin();
		
		Customer customer = entityManager.getReference(Customer.class, cid);
		
		
		System.out.print("Enter new ContactNo ");
		
		Scanner scan = new Scanner(System.in);
		String UpdCname = scan.next();
		customer.setCustomerName(UpdCname);
		
		
		System.out.print("Enter new ContactNo ");
		String UpdContact = scan.next();
		customer.setContactNo(UpdContact);
		
	}catch(Exception e) {
		System.out.println(e);
		transaction.rollback();
	}finally {
		if (transaction != null) {
			transaction.commit();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (emf != null) {
			emf.close();
		}
	}
	}
	
	void customerDelete(int cid) {
		try{
			emf = Persistence.createEntityManagerFactory("corebanking");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			Customer customer = entityManager.getReference(Customer.class, cid);
			List<Account> account = customer.getListAccount();
			entityManager.remove(customer);
			for( Account a: account) {
			entityManager.remove(a);
			}
			System.out.println("Delete Sucessful");
		}catch(Exception e) {
			System.out.println(e);
			transaction.rollback();
		}finally {
			if (transaction != null) {
				transaction.commit();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}	
	}
	public static void main(String[] args) {
		OneToManyUniD oTmU = new OneToManyUniD();
		//oTmU.customerCreate();
		//oTmU.customerRead(1);
		//oTmU.customerUpdate(1);
		oTmU.customerDelete(1);
	}

}
