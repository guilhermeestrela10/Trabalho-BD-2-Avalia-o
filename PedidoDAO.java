import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PedidoDAO {

    public void inserir(int clienteId, int produtoId, int quantidade) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO pedidos (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ps.setInt(2, produtoId);
            ps.setInt(3, quantidade);
            ps.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido.");
            e.printStackTrace();
        }
    }

    public void atualizar(int id, int clienteId, int produtoId, int quantidade) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "UPDATE pedidos SET cliente_id = ?, produto_id = ?, quantidade = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ps.setInt(2, produtoId);
            ps.setInt(3, quantidade);
            ps.setInt(4, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Pedido atualizado com sucesso!");
            } else {
                System.out.println("Pedido não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar pedido.");
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection conn = Conexao.conectar()) {
            String sql = "DELETE FROM pedidos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Pedido não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir pedido.");
            e.printStackTrace();
        }
    }

    public void listarComInnerJoinELeftJoin() {
        try (Connection conn = Conexao.conectar()) {
            String sql = """
                SELECT pe.id,
                       c.nome AS cliente,
                       p.nome AS produto,
                       pe.quantidade
                FROM pedidos pe
                INNER JOIN clientes c ON pe.cliente_id = c.id
                LEFT JOIN produtos p ON pe.produto_id = p.id
                ORDER BY c.nome ASC
            """;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | Cliente: " +
                    rs.getString("cliente") + " | Produto: " +
                    rs.getString("produto") + " | Quantidade: " +
                    rs.getInt("quantidade")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pedidos com JOIN.");
            e.printStackTrace();
        }
    }

    public void filtrarPorCliente(String nomeCliente) {
        try (Connection conn = Conexao.conectar()) {
            String sql = """
                SELECT pe.id,
                       c.nome AS cliente,
                       p.nome AS produto,
                       pe.quantidade
                FROM pedidos pe
                INNER JOIN clientes c ON pe.cliente_id = c.id
                LEFT JOIN produtos p ON pe.produto_id = p.id
                WHERE c.nome ILIKE ?
                ORDER BY c.nome ASC
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nomeCliente + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | Cliente: " +
                    rs.getString("cliente") + " | Produto: " +
                    rs.getString("produto") + " | Quantidade: " +
                    rs.getInt("quantidade")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao filtrar pedidos.");
            e.printStackTrace();
        }
    }
}
