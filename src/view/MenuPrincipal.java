package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.MusicaDAO;
import database.Conexao;
import database.Inicializador;
import model.Musica;

public class MenuPrincipal {
	public static void main(String[] args) {
		Inicializador.criarTabela();
		try (Connection conexao = Conexao.conectar();
				Scanner leia = new Scanner(System.in)) {

			MusicaDAO dao = new MusicaDAO(conexao);
			int opcao;

			do {
				System.out.println("\n----MENU PRINCIPAL---- ");
				System.out.println("1 - Cadastrar música");
				System.out.println("2 - Listar todas as músicas");
				System.out.println("3 - Buscar música por ID");
				System.out.println("4 - Atualizar música");
				System.out.println("5 - Deletar música");
				System.out.println("0 - Sair");
				System.out.print("Escolha uma opção: ");
				opcao = leia.nextInt();
				leia.nextLine();

				switch (opcao) {
				case 1 -> {
					Musica nova = solicitarDados(leia);
					dao.inserir(nova);
					System.out.println("Música cadastrada com sucesso!");
				}
				case 2 -> {
					List<Musica> lista = dao.listarTodas();
					if (lista.isEmpty()) {
						System.out.println("Nenhuma música cadastrada.");
					} else {
						for (Musica m : lista) {
							exibirDados(m);
						}
					}
				}
				case 3 -> {
				    System.out.print("ID da música que deseja buscar: ");
				    int idBusca = leia.nextInt();
				    leia.nextLine();
				    Musica encontrada = dao.buscarPorId(idBusca);
				    if (encontrada != null) {
				        exibirDados(encontrada);
				    } else {
				        System.out.println("Nenhuma música encontrada com esse ID.");
				    }
				}
				case 4 -> {
				    Musica atualizada = solicitarDadosAtualizados(leia);
				    dao.atualizar(atualizada);
				    System.out.println("Música atualizada com sucesso!");
				}
				case 5 -> {
				    System.out.print("ID da música que deseja deletar: ");
				    int id = leia.nextInt();
				    leia.nextLine();
				    dao.deletar(id);
				    System.out.println("Música deletada com sucesso!");
				}
				case 0 -> System.out.println("Programa encerrado.");
				default -> System.out.println("Opção inválida, tente novamente.");
				}

			} while (opcao != 0);

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
		}
	}

	private static Musica solicitarDados(Scanner leia) {
		String titulo;
		String artista;
		String genero;
		int duracao = -1;
		String dataLancamento;

		do {
			System.out.print("Título: ");
			titulo = leia.nextLine().trim();
			if (titulo.isEmpty()) {
				System.out.println("O título não pode ser vazio.");
			}
		} while (titulo.isEmpty());


		do {
			System.out.print("Artista: ");
			artista = leia.nextLine().trim();
			if (artista.isEmpty()) {
				System.out.println("O artista não pode ser vazio.");
			}
		} while (artista.isEmpty());

		System.out.print("Gênero: ");
		genero = leia.nextLine().trim();

		do {
			System.out.print("Duração (em segundos): ");
			String entrada = leia.nextLine();

			if (entrada.matches("\\d+")) {
				duracao = Integer.parseInt(entrada);
			} else {
				System.out.println("Por favor, digite um número inteiro positivo.");
				duracao = -1;
			}

		} while (duracao < 0);


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		while (true) {
			System.out.print("Data de lançamento (DD/MM/YYYY): ");
			String entrada = leia.nextLine();
			try {
				Date data = sdf.parse(entrada);
				dataLancamento = sdf.format(data);
				break;
			} catch (ParseException e) {
				System.out.println("Data inválida. Tente novamente no formato DD/MM/YYYY.");
			}
		}

		return new Musica(titulo, artista, genero, duracao, dataLancamento);
	}

	private static Musica solicitarDadosAtualizados(Scanner leia) {
		String titulo;
		String artista;
		String genero;
		int duracao = -1;
		String dataLancamento;

		System.out.print("ID da música que deseja atualizar: ");
		int id = leia.nextInt();
		leia.nextLine();

		do {
			System.out.print("Novo título: ");
			titulo = leia.nextLine().trim();
			if (titulo.isEmpty()) {
				System.out.println("O título não pode ser vazio.");
			}
		} while (titulo.isEmpty());

		do {
			System.out.print("Novo artista: ");
			artista = leia.nextLine().trim();
			if (artista.isEmpty()) {
				System.out.println("O artista não pode ser vazio.");
			}
		} while (artista.isEmpty());

		System.out.print("Novo gênero: ");
		genero = leia.nextLine().trim();

		do {
			System.out.print("Nova duração (em segundos): ");
			String entrada = leia.nextLine();

			if (entrada.matches("\\d+")) {
				duracao = Integer.parseInt(entrada);
			} else {
				System.out.println("Por favor, digite um número inteiro positivo.");
				duracao = -1;
			}

		} while (duracao < 0);


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		while (true) {
			System.out.print("Nova data de lançamento (DD/MM/YYYY): ");
			String entrada = leia.nextLine();
			try {
				Date data = sdf.parse(entrada);
				dataLancamento = sdf.format(data);
				break;
			} catch (ParseException e) {
				System.out.println("Data inválida. Tente novamente no formato DD/MM/YYYY.");
			}
		}

		Musica musicaAtualizada = new Musica(titulo, artista, genero, duracao, dataLancamento);
		musicaAtualizada.setId(id);
		return musicaAtualizada;
	}



	private static String formatarDuracao(int segundos) {
		int minutos = segundos / 60;
		int restoSegundos = segundos % 60;
		return String.format("%02d:%02d", minutos, restoSegundos);
	}

	private static String formatarData(String dataOriginal) {
		try {
			SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
			Date data = formatoBanco.parse(dataOriginal);
			SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy");
			return formatoBR.format(data);
		} catch (Exception e) {
			return dataOriginal;
		}
	}

	private static void exibirDados(Musica musica) {
		System.out.println("\n------------------");
		System.out.println("ID: " + musica.getId());
		System.out.println("Título: " + musica.getTitulo());
		System.out.println("Artista: " + musica.getArtista());
		System.out.println("Gênero: " + musica.getGenero());
		System.out.println("Duração: " + formatarDuracao(musica.getDuracao()));
		System.out.println("Data de lançamento: " + musica.getDataLancamento());
		System.out.println("Data de cadastro: " + formatarData(musica.getDataCadastro()));
	}
}
