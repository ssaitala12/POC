package com.hib.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Citizen {
	/*
	 * In the database perspective, as every entity has an id, so the id attribute  is
	 * introduced here.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private String name;
	private String address;
	
	/**
	 * The below annotation says that Citizen has a OneToOne relationship with voterCard (Unidirectional).
	 * Inorder to make ths relationship bidirectional, "mappedBy" should be used in VoterCard.
	 */
	@OneToOne(cascade=CascadeType.ALL) 
	/* The importance of CascadeType.ALL is that, whenever you save the citizen, 
	 * first the voterCard has to be saved and then that id will be stored in 
	 * the citizen table as voter_id. In case, you don't give that CascasdeType.ALL,
	 * then there will be an error saying that the VoterCard should be saved first. 
	 * Instead of CascadeType.ALL, you can even save the voterCard first and then the 
	 * citizen. But as the cascade is very handy, we use it. */
	@JoinColumn(name="fk_voterId")
	/*
	 * By default the join column that will be stored in the Citizen table is <other table name>_id. (VOTERCARD_VoterId). 
	 * In order to override the default we give the above annotation with the name attribute.
	 */
	private VoterCard voterCard;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public VoterCard getVoterCard() {
		return voterCard;
	}
	public void setVoterCard(VoterCard voterCard) {
		this.voterCard = voterCard;
	}
	
	
	
	
}
