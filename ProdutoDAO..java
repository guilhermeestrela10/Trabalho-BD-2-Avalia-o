import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProdutoDAO {

    public void inserir(String nome, double preco) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setDouble(2, preco);
            ps.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto.");
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM produtos ORDER BY id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | R$ " +
                    rs.getDouble("preco")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos.");
            e.printStackTrace();
        }
    }

    public void atualizar(int id, String nome, double preco) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setDouble(2, preco);
            ps.setInt(3, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto.");
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto.");
            e.printStackTrace();
        }
    }

    public void filtrarPorNome(String nomeBusca) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM produtos WHERE nome ILIKE ? ORDER BY nome ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | R$ " +
                    rs.getDouble("preco")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao filtrar produtos.");
            e.printStackTrace();
        }
    }

    public void ordenarPorPreco() {
        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT * FROM produtos ORDER BY preco ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("nome") + " | R$ " +
                    rs.getDouble("preco")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao ordenar produtos.");
            e.printStackTrace();
        }
    }
}
