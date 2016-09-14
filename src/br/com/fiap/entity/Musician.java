package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seqMusician",sequenceName="SEQ_Musician",allocationSize=1)
public class Musician {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMusician")
	private int id;
	
	@Column(name="NM_MUSICIAN")
	private String name;
	
	@Column(name="NM_MUSICAL_GENRE")
	private String genre;
	
	@Column(name="DT_DEBUT")
	private Calendar debutDate;
	
	@Column(name="NM_COUNTRY")
	private String country;
	
	public Musician(int id, String name, String genre, Calendar debutDate,
			String country) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.debutDate = debutDate;
		this.country = country;
	}

	public Musician() {
		super();
	}

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


	public Calendar getDebutDate() {
		return debutDate;
	}


	public void setDebutDate(Calendar debutDate) {
		this.debutDate = debutDate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	

	
	
}
