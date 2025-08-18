package database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class Inicializador {
    public static void criarTabela() {
        try {
            String sql = new String(Files.readAllBytes(Paths.get("schema.sql")));
            try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
