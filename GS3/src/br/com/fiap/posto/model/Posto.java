package br.com.fiap.posto.model;

import java.math.BigDecimal;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Posto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	
	private BigDecimal preco;
	private String tipo1;
	private String tipo2;
	private String css2;
	private String chademo;
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}
	@Override
	public String toString() {
		return "Posto [id=" + id + ", nome=" + nome + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", preco=" + preco + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", css2=" + css2
				+ ", chademo=" + chademo + "]";
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	public void setCss2(String css2) {
		this.css2 = css2;
	}

	public void setChademo(String chademo) {
		this.chademo = chademo;
	}
	public Vector<String> getData() {
		
		Vector<String> data = new Vector<String>();
		data.add(id.toString());
		data.add(nome);
		data.add(rua);
		data.add(bairro);
		data.add(cidade);
		data.add(estado);
		data.add(tipo1);
		data.add(tipo2);
		data.add(css2);
		data.add(chademo);
		data.add(preco.toString());
		
		return data;
	}

}
