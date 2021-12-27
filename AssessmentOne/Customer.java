package com.AssessmentOne;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer_tabl")
public class Customer {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "cid")
	private int cid;

	@Column(name = "customer_name")
	private String c_name;

	@Column(name = "contact_no")
	private String ContactNo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid")
	List<Account> listAccount;
	
	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCustomerName() {
		return c_name;
	}

	public void setCustomerName(String c_name) {
		this.c_name = c_name;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		this.ContactNo = contactNo;
	}


}
