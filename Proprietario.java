
public class Proprietario {

	private int idProprietario;
	private String nome;
	private String email;
	private float peso;
	private String sexo;
	private String cnh;

	public Proprietario() {

	}

	public Proprietario(int idProprietario, String nome, String email, float peso, String sexo, String cnh) {
		this.idProprietario = idProprietario;
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.sexo = sexo;
		this.cnh = cnh;
	}

	public int getCodProprietario() {
		return idProprietario;
	}

	public void setCodProprietario(int codProprietario) {
		this.idProprietario = codProprietario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getPeso() {
		return peso;
	}

	public void setSexo(float peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@Override
	public String toString() {
		String dadosProprietario = "\n\n************ P R O P R I E T Á R I O ************\n"
				+"\n ID......:" + idProprietario
				+"\n Nome....:" + nome
				+"\n E-mail..:" + email
				+"\n Peso....:" + peso
				+"\n Sexo....:" + sexo
				+"\n CNH.....:" + cnh;
		
		return dadosProprietario;
	}
	
}
