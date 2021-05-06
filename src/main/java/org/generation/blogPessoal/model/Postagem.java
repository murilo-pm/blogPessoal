package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	public String titulo;
	
	@NotNull
	@Size(min = 10, max = 500)
	public String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne							//estabelecimento do tipo de relação (quem criará as chaves estrangeiras será o JPA)
	@JsonIgnoreProperties("postagem") 	//dentro do parâmetro: propriedade que será ignorada p/ não criar o loop 
	private Tema tema;
	
	@ManyToOne							//estabelecimento do tipo de relação (quem criará as chaves estrangeiras será o JPA)
	@JsonIgnoreProperties("postagem") 	//dentro do parâmetro: propriedade que será ignorada p/ não criar o loop 
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
}
