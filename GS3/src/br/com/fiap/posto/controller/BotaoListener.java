package br.com.fiap.posto.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import br.com.fiap.posto.Janela;
import br.com.fiap.posto.org.PostoOrg;
import br.com.fiap.posto.model.Posto;

public class BotaoListener implements ActionListener{

	private Janela view;
	private PostoOrg org = new PostoOrg();
	
	public BotaoListener(Janela view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.paramString().contains("Crescente")){
			org.ordemAsc();
			view.ordernarAsc();
		}else if(e.paramString().contains("Decrescente")){
			org.ordemDes();
			view.ordernarDes();
		}else if(e.paramString().contains("Salvar")){
		
			Posto posto = new Posto();
			
			posto.setNome(view.getNomePosto().getText());
			
			posto.setRua(view.getRua().getText());
			posto.setBairro(view.getBairro().getText());
			posto.setCidade(view.getCidade().getText());
			posto.setEstado(view.getEstado().getText());
			
			posto.setTipo1(view.getTipo1());
			posto.setTipo2(view.getTipo2());
			posto.setCss2(view.getCSS2());
			posto.setChademo(view.getCHAdeMO());
			
			posto.setValor(new BigDecimal(view.getValor().getText()));
					
			org.inserir(posto);
						
			view.carregarDados();
		}else if(e.paramString().contains("Cancelar")) {
			view.setNomePosto();
			view.setRua();
			view.setBairro();
			view.setCidade();
			view.setEstado();
			view.setValor();
		}
	}

}
