package model;

public class Musica {
	private int id;
	private String titulo;
	private String artista;
	private String genero;
	private int duracao;
	private String dataLancamento;
	private String dataCadastro;

	public Musica(int id, String titulo, String artista, String genero, int duracao, String dataLancamento, String dataCadastro) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.duracao = duracao;
		this.dataLancamento = dataLancamento;
		this.dataCadastro = dataCadastro;
	}

	public Musica(String titulo, String artista, String genero, int duracao, String dataLancamento) {
		super();
		this.titulo = titulo;
		this.artista = artista;
		this.genero = genero;
		this.duracao = duracao;
		this.dataLancamento = dataLancamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
