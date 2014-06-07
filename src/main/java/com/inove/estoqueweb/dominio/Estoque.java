package com.inove.estoqueweb.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Estoque {

	@Column(name="nome",nullable=false,unique=true)
	private String nome; 
	@Column(name="descricao",nullable=true)
	@Lob
	private String descricao;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id; 
	
	@OneToMany(mappedBy="id",fetch=FetchType.LAZY)
	private List<Produto> produtos; 
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	} 
}
