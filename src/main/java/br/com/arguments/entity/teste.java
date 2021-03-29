package br.com.arguments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TESTE")
public class teste {
	
	 @Id
	 @Column(name="ID")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;

}
