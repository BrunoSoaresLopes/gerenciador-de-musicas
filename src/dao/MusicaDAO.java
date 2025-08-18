package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import model.Musica;

public class MusicaDAO {
    private static final Logger logger = Logger.getLogger(MusicaDAO.class.getName());
    private Connection conexao;

    public MusicaDAO(Connection conexao) {
        this.conexao = conexao;
        try {
            FileHandler fileHandler = new FileHandler("logs/app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.info("Conexão com o banco estabelecida.");
        } catch (Exception e) {
            logger.severe("Erro ao configurar o logger: " + e.getMessage());
        }
    }


    public void inserir(Musica musica) throws SQLException {
        String sql = "INSERT INTO musica (titulo, artista, genero, duracao, data_lancamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getArtista());
            stmt.setString(3, musica.getGenero());
            stmt.setInt(4, musica.getDuracao());
            stmt.setString(5, musica.getDataLancamento());
            stmt.executeUpdate();
            logger.info("Música inserida: " + musica.getTitulo());
        } catch (SQLException e) {
            logger.severe("Erro ao inserir música: " + e.getMessage());
            throw e;
        }
    }

    public List<Musica> listarTodas() throws SQLException {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Musica m = new Musica(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getString("genero"),
                    rs.getInt("duracao"),
                    rs.getString("data_lancamento"),
                    rs.getString("data_cadastro")
                );
                lista.add(m);
            }
            logger.info("Listagem de músicas realizada. Total: " + lista.size());
        } catch (SQLException e) {
            logger.severe("Erro ao listar músicas: " + e.getMessage());
            throw e;
        }
        return lista;
    }

    public Musica buscarPorId(int id) {
        String sql = "SELECT * FROM musica WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                logger.info("Música encontrada com ID: " + id);
                return new Musica(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getString("genero"),
                    rs.getInt("duracao"),
                    rs.getString("data_lancamento"),
                    rs.getString("data_cadastro")
                );
            } else {
                logger.warning("Nenhuma música encontrada com ID: " + id);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar música por ID: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Musica musica) throws SQLException {
        String sql = "UPDATE musica SET titulo = ?, artista = ?, genero = ?, duracao = ?, data_lancamento = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getArtista());
            stmt.setString(3, musica.getGenero());
            stmt.setInt(4, musica.getDuracao());
            stmt.setString(5, musica.getDataLancamento());
            stmt.setInt(6, musica.getId());
            stmt.executeUpdate();
            logger.info("Música atualizada: ID " + musica.getId());
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar música: " + e.getMessage());
            throw e;
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM musica WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Música deletada: ID " + id);
        } catch (SQLException e) {
            logger.severe("Erro ao deletar música: " + e.getMessage());
            throw e;
        }
    }
}
