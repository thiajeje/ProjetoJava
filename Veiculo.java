
public class Veiculo {

	private int codVeiculo;
	private String placa;
	private String descricao;
	private String cor;
	private int numPortas;
	private Proprietario proprietario;

	public Veiculo(int codVeiculo, String placa, String descricao, String cor, int numPortas,
			Proprietario proprietario) {
		this.codVeiculo = codVeiculo;
		this.placa = placa;
		this.cor = cor;
		this.descricao = descricao;
		this.numPortas = numPortas;
		this.proprietario = proprietario;

	}

	public Veiculo() {
		
	}
	
	public int getCodVeiculo() {
		return codVeiculo;
	}

	public void setCodVeiculo(int codVeiculo) {
		this.codVeiculo = codVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(int numPortas) {
		this.numPortas = numPortas;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		String dadosVeiculo = "\n\n************ V E I C U L O ************\n"
		+ "\n Código................: " + codVeiculo
		+ "\n Placa.................: " + placa
		+ "\n Descrição.............: " + descricao
		+ "\n Cor...................: " + cor
		+ "\n Número de portas......: " + numPortas
		+ "\n Código Proprietario...: ";
		
		if(proprietario != null) {
			dadosVeiculo += proprietario.getCodProprietario();
		}
		
		return dadosVeiculo;
	}

}
