package br.com.fiap.posto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.posto.controller.BotaoListener;
import br.com.fiap.posto.org.PostoOrg;
import br.com.fiap.posto.model.Posto;

public class Janela extends JFrame{

	private static final long serialVersionUID = 1L;
	private Input nomePosto = new Input();
	private Input rua = new Input();
	private Input bairro = new Input();
	private Input cidade = new Input();
	private Input estado = new Input();
	private JCheckBox tipo1 = new JCheckBox("Entrada do carro tipo 1");
	private JCheckBox tipo2 = new JCheckBox("Entrada do carro tipo 2");
	private JCheckBox CHAMdeMO = new JCheckBox("Entrada do carro CHAdeMO");
	private JCheckBox CSS2 = new JCheckBox("Entrada do carro CSS2");
	private Input valor = new Input();

	String[] colunas = {"id", "Nome Do Posto", "Rua", "Bairro", "Cidade", "Estado",
			"Tipo 1", "Tipo 2", "CSS2", "CHAdeMO",
			"Valor por kWh"
			}; 
	DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
	JTable tabela = new JTable(tableModel);
	
	public Janela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Posto de abastecimento");
		setSize(1000, 391);
		setResizable(false);
	}
	
	public void init() {
		JTabbedPane abas = new JTabbedPane();	
			JPanel cadastro = new JPanel();		
					
		abas.add("Cadastro", cadastro);
		JPanel nome = new JPanel();
			nome.setBorder(new CompoundBorder(new TitledBorder("Nome do Posto"), new EmptyBorder(12, 0, 0, 0)));
			nome.setLayout(new BoxLayout(nome, BoxLayout.Y_AXIS));
			nome.setMaximumSize(new Dimension(1200, 60));
			nome.add(nomePosto);
		
		JPanel endereco = new JPanel();
		endereco.setBorder(new CompoundBorder(new TitledBorder("Localização"), new EmptyBorder(12, 0, 0, 0)));
			endereco.setLayout(new BoxLayout(endereco, BoxLayout.Y_AXIS));
			endereco.setMaximumSize(new Dimension(1000, 240));
			endereco.add(new Label("Rua"));
			endereco.add(rua);
			endereco.add(new Label("Bairro"));
			endereco.add(bairro);
			endereco.add(new Label("Cidade"));
			endereco.add(cidade);
			endereco.add(new Label("Estado"));
			endereco.add(estado);
			
		JPanel nomeEnderecoBox = new JPanel();
			nomeEnderecoBox.setLayout(new BoxLayout(nomeEnderecoBox, BoxLayout.Y_AXIS));
			nomeEnderecoBox.add(nome);
			nomeEnderecoBox.add(endereco);
			
		JPanel tipos = new JPanel();
		tipos.setBorder(new CompoundBorder(new TitledBorder("Tipos de abastecimento"), new EmptyBorder(12, 0, 0, 0)));
			JPanel tiposBotoes = new JPanel();
				tiposBotoes.setLayout(new GridLayout(2, 2));

				tiposBotoes.add(tipo1);
				tiposBotoes.add(tipo2);
				tiposBotoes.add(CSS2);
				tiposBotoes.add(CHAdeMO);
			
		JPanel valorBox = new JPanel();
		valorBox.setLayout(new BoxLayout(valorBox, BoxLayout.Y_AXIS));
		valorBox.add(new Label("Valor por kW/h( Usar . )"));
		valorBox.add(valor);
			tipos.setLayout(new BorderLayout());
			tipos.setMaximumSize(new Dimension(1000, 310));
			tipos.add(tiposBotoes, BorderLayout.PAGE_START);
			tipos.add(valorBox, BorderLayout.PAGE_END);
			
		JPanel botoes = new JPanel();
			JButton salvar = new JButton("Salvar");
			JButton cancelar = new JButton("Cancelar");
			botoes.add(salvar);
			botoes.add(cancelar);
		
		JPanel informacoes = new JPanel();
			informacoes.setLayout(new BoxLayout(informacoes, BoxLayout.X_AXIS));
			informacoes.add(nomeEnderecoBox);
			informacoes.add(tipos);
			
		JButton imagem = new JButton(new ImageIcon(((new ImageIcon("./img/postinho.jpg")).getImage()).getScaledInstance(250, 278, java.awt.Image.SCALE_SMOOTH)));

		BotaoListener listener = new BotaoListener(this);
		salvar.addActionListener(listener);
		cancelar.addActionListener(listener);
			
		tabela.setDefaultEditor(Object.class, null);
				
		carregarDados();
		
		cadastro.setLayout(new BorderLayout());
		cadastro.add(informacoes, BorderLayout.CENTER);
		cadastro.add(botoes, BorderLayout.PAGE_END);
		cadastro.add(imagem, BorderLayout.LINE_START);
		
		JPanel lista = new JPanel();
		JPanel ordem = new JPanel();
		
		JButton ordenarDES = new JButton("Ordenar Decrescente");
		JButton ordenarASC = new JButton("Ordenar Crescente");

		ordem.add(ordenarDES);
		ordem.add(ordenarASC);
		
		ordenarASC.addActionListener(listener);
		ordenarDES.addActionListener(listener);
		
		lista.setLayout(new BorderLayout());
		lista.add(new JScrollPane(tabela), BorderLayout.CENTER);
		lista.add(ordem, BorderLayout.PAGE_END);
				
		abas.add(lista, "Lista");
		add(abas);
		setVisible(true);
	}
	
	public void ordernarDes() {
		tableModel.setRowCount(0);
		List<Posto> lista = new PostoOrg().ordemDes();
		lista.forEach(posto -> tableModel.addRow(posto.getData()));
	}
	
	
	public void ordernarAsc() {
		tableModel.setRowCount(0);
		List<Posto> lista = new PostoOrg().ordemAsc();
		lista.forEach(posto -> tableModel.addRow(posto.getData()));
	}
	
	public void carregarDados() {
		tableModel.setRowCount(0);
		List<Posto> lista = new PostoOrg().listarTodos();
		lista.forEach(posto -> tableModel.addRow(posto.getData()));
	}
	
	public Input getNomePosto() {
		return nomePosto;
	}

	public Input getRua() {
		return rua;
	}

	public void setNomePosto() {
		this.nomePosto.setText("");
	}

	public void setRua() {
		this.rua.setText("");
	}

	public void setBairro() {
		this.bairro.setText("");
	}

	public void setCidade() {
		this.cidade.setText("");
	}

	public void setEstado() {
		this.estado.setText("");
	}

	public void setValor() {
		this.valor.setText("");
	}

	public Input getBairro() {
		return bairro;
	}

	public Input getCidade() {
		return cidade;
	}

	public Input getEstado() {
		return estado;
	}

	public String getTipo1() {
		if(tipo1.isSelected()) {
			return "OK";
		}
		return "X";
	}

	public String getTipo2() {
		if(tipo2.isSelected()) {
			return "OK";
		}
		return "X";
	}

	public String getCSS2() {
		if(CSS2.isSelected()) {
			return "OK";
		}
		return "X";
	}

	public String getCHAdeMO() {
		if(CHAdeMO.isSelected()) {
			return "OK";
		}
		return "X";
	}

	public Input getValor() {
		return valor;
	}
	
}
