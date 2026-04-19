import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClienteDAO {

    public void inserir(String nome, String email) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente.");
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM clientes ORDER BY id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | " +
                    rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes.");
            e.printStackTrace();
        }
    }

    public void atualizar(int id, String nome, String email) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setInt(3, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente.");
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente.");
            e.printStackTrace();
        }
    }

    public void filtrarPorNome(String nomeBusca) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM clientes WHERE nome ILIKE ? ORDER BY nome ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | " +
                    rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao filtrar clientes.");
            e.printStackTrace();
        }
    }

    public void ordenarPorNome() {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM clientes ORDER BY nome ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | " +
                    rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao ordenar clientes.");
            e.printStackTrace();
        }
    }
}
