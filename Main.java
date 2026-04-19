import java.util.Scanner;

public class Main {

    public static boolean login(Scanner sc) {
        System.out.print("Usuário: ");
        String usuario = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        return usuario.equals("admin") && senha.equals("1234"); 
        //quando rodar o codigo esses sao as infomaçoes q vai por no terminal
    }

    public static void menu() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Atualizar cliente");
        System.out.println("4 - Excluir cliente");
        System.out.println("5 - Filtrar cliente por nome");
        System.out.println("6 - Ordenar clientes por nome");

        System.out.println("7 - Cadastrar produto");
        System.out.println("8 - Listar produtos");
        System.out.println("9 - Atualizar produto");
        System.out.println("10 - Excluir produto");
        System.out.println("11 - Filtrar produto por nome");
        System.out.println("12 - Ordenar produtos por preço");

        System.out.println("13 - Cadastrar pedido");
        System.out.println("14 - Atualizar pedido");
        System.out.println("15 - Excluir pedido");
        System.out.println("16 - Listar pedidos com JOIN");
        System.out.println("17 - Filtrar pedidos por cliente");

        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        System.out.println("===== SISTEMA LOJA =====");

        if (!login(sc)) {
            System.out.println("Login inválido. Encerrando sistema.");
            sc.close();
            return;
        }

        int opcao;

        do {
            menu();
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = sc.nextLine();
                    System.out.print("Email do cliente: ");
                    String emailCliente = sc.nextLine();
                    clienteDAO.inserir(nomeCliente, emailCliente);
                    break;

                case 2:
                    clienteDAO.listarTodos();
                    break;

                case 3:
                    System.out.print("ID do cliente: ");
                    int idClienteAtualizar = Integer.parseInt(sc.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNomeCliente = sc.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmailCliente = sc.nextLine();
                    clienteDAO.atualizar(idClienteAtualizar, novoNomeCliente, novoEmailCliente);
                    break;

                case 4:
                    System.out.print("ID do cliente para excluir: ");
                    int idClienteExcluir = Integer.parseInt(sc.nextLine());
                    clienteDAO.excluir(idClienteExcluir);
                    break;

                case 5:
                    System.out.print("Digite o nome para buscar: ");
                    String buscaCliente = sc.nextLine();
                    clienteDAO.filtrarPorNome(buscaCliente);
                    break;

                case 6:
                    clienteDAO.ordenarPorNome();
                    break;

                case 7:
                    System.out.print("Nome do produto: ");
                    String nomeProduto = sc.nextLine();
                    System.out.print("Preço do produto: ");
                    double precoProduto = Double.parseDouble(sc.nextLine());
                    produtoDAO.inserir(nomeProduto, precoProduto);
                    break;

                case 8:
                    produtoDAO.listarTodos();
                    break;

                case 9:
                    System.out.print("ID do produto: ");
                    int idProdutoAtualizar = Integer.parseInt(sc.nextLine());
                    System.out.print("Novo nome do produto: ");
                    String novoNomeProduto = sc.nextLine();
                    System.out.print("Novo preço: ");
                    double novoPrecoProduto = Double.parseDouble(sc.nextLine());
                    produtoDAO.atualizar(idProdutoAtualizar, novoNomeProduto, novoPrecoProduto);
                    break;

                case 10:
                    System.out.print("ID do produto para excluir: ");
                    int idProdutoExcluir = Integer.parseInt(sc.nextLine());
                    produtoDAO.excluir(idProdutoExcluir);
                    break;

                case 11:
                    System.out.print("Digite o nome do produto para buscar: ");
                    String buscaProduto = sc.nextLine();
                    produtoDAO.filtrarPorNome(buscaProduto);
                    break;

                case 12:
                    produtoDAO.ordenarPorPreco();
                    break;

                case 13:
                    System.out.print("ID do cliente: ");
                    int clienteId = Integer.parseInt(sc.nextLine());
                    System.out.print("ID do produto: ");
                    int produtoId = Integer.parseInt(sc.nextLine());
                    System.out.print("Quantidade: ");
                    int quantidade = Integer.parseInt(sc.nextLine());
                    pedidoDAO.inserir(clienteId, produtoId, quantidade);
                    break;

                case 14:
                    System.out.print("ID do pedido: ");
                    int idPedidoAtualizar = Integer.parseInt(sc.nextLine());
                    System.out.print("Novo ID do cliente: ");
                    int novoClienteId = Integer.parseInt(sc.nextLine());
                    System.out.print("Novo ID do produto: ");
                    int novoProdutoId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = Integer.parseInt(sc.nextLine());
                    pedidoDAO.atualizar(idPedidoAtualizar, novoClienteId, novoProdutoId, novaQuantidade);
                    break;

                case 15:
                    System.out.print("ID do pedido para excluir: ");
                    int idPedidoExcluir = Integer.parseInt(sc.nextLine());
                    pedidoDAO.excluir(idPedidoExcluir);
                    break;

                case 16:
                    pedidoDAO.listarComInnerJoinELeftJoin();
                    break;

                case 17:
                    System.out.print("Digite o nome do cliente para buscar pedidos: ");
                    String buscaPedidoCliente = sc.nextLine();
                    pedidoDAO.filtrarPorCliente(buscaPedidoCliente);
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
}
