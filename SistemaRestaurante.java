import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//=================== CLASSES ===================//

public class SistemaRestaurante {
    private static Scanner scanner = new Scanner(System.in);

    public static class Mesa {
        private int id;
        private int capacidade;
        private List<Pedido> pedidos;  
        private boolean status;

        public Mesa(int id, int capacidade, boolean status) {
            this.id = id;
            this.capacidade = capacidade;
            this.status = status;
            this.pedidos = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public int getCapacidade() {
            return capacidade;
        }

        public List<Pedido> getPedidos() {
            return pedidos;
        }

        public void adicionarPedido(Pedido pedido) {
            pedidos.add(pedido);
        }

        public void resetarMesa() {
            pedidos.clear(); 
            status = true; 
        }

        public boolean verificarStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }

    public static class Pedido {
        private List<Pratos> pratos;
        private List<Bebidas> bebidas;
        private double valorTotal;

        public Pedido() {
            this.pratos = new ArrayList<>();
            this.bebidas = new ArrayList<>();
            this.valorTotal = 0.0;
        }

        public void adicionarPrato(Pratos prato) {
            pratos.add(prato);
            valorTotal += prato.getValor();
        }

        public void adicionarBebida(Bebidas bebida) {
            bebidas.add(bebida);
            valorTotal += bebida.getValor();
        }

        public void removerPrato(Pratos prato) {
            if (pratos.remove(prato)) {
                valorTotal -= prato.getValor();
            }
        }

        public void removerBebida(Bebidas bebida) {
            if (bebidas.remove(bebida)) {
                valorTotal -= bebida.getValor();
            }
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public List<Pratos> getPratos() {
            return pratos;
        }

        public List<Bebidas> getBebidas() {
            return bebidas;
        }

        @Override
        public String toString() {
            return "Pedido - Total: R$" + valorTotal;
        }
    }

    public static class Pratos {
        private String nome;
        private double valor;

        public Pratos(String nome, double valor) {
            this.nome = nome;
            this.valor = valor;
        }

        public String getNome() {
            return nome;
        }

        public double getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return nome + " - R$" + valor;
        }
    }

    public static class Bebidas {
        private String nome;
        private double valor;

        public Bebidas(String nome, double valor) {
            this.nome = nome;
            this.valor = valor;
        }

        public String getNome() {
            return nome;
        }

        public double getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return nome + " - R$" + valor;
        }
    }

    public static class Cardapio {
        private static final List<Pratos> pratos = Arrays.asList(
                new Pratos("Lasanha", 25.00),
                new Pratos("Pizza", 30.00),
                new Pratos("Hambúrguer", 20.00)
        );

        private static final List<Bebidas> bebidas = Arrays.asList(
                new Bebidas("Coca-Cola", 5.00),
                new Bebidas("Suco de Laranja", 8.00),
                new Bebidas("Água", 3.00)
        );

        public static List<Pratos> getPratos() {
            return pratos;
        }

        public static List<Bebidas> getBebidas() {
            return bebidas;
        }
    }

    
    public static class Restaurante {
        private static final List<Mesa> mesas = Arrays.asList(
                new Mesa(1, 4, true),
                new Mesa(2, 4, true),
                new Mesa(3, 4, true),
                new Mesa(4, 4, true),
                new Mesa(5, 4, true)
        );

        public static List<Mesa> getMesas() {
            return mesas;
        }
    }

    //======================== MAIN ======================//

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Verificar Status da Mesa");
            System.out.println("2. Modificar Status da Mesa");
            System.out.println("3. Adicionar Pedido");
            System.out.println("4. Resetar Mesa");
            System.out.println("5. Adicionar Itens ao Pedido");
            System.out.println("6. Remover Itens do Pedido");
            System.out.println("7. Listar Cardapio");
            System.out.println("8. Listar Pedido da Mesa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    verificarStatus();
                    break;
                case 2:
                    modificarStatus();
                    break;
                case 3:
                    fazerPedido();
                    break;
                case 4:
                    resetarMesa();
                    break;
                case 5:
                    adicionarItensPedido();
                    break;
                case 6:
                    removerItensPedido();
                    break;
                case 7:
                    listarCardapio();
                    break;
                case 8:
                    listarPedidoMesa();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    //======================= FUNÇÕES =======================//

    private static void verificarStatus() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);
        System.out.println("Mesa ID: " + mesa.getId() + " - Status: " + (mesa.verificarStatus() ? "Disponível" : "Reservada"));
    }

    private static void modificarStatus() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);
        mesa.setStatus(!mesa.verificarStatus());
        System.out.println("Status da mesa " + mesa.getId() + " modificado.");
    }

    private static void fazerPedido() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);

        Pedido novoPedido = new Pedido();
        mesa.adicionarPedido(novoPedido);
        System.out.println("Pedido iniciado para a mesa " + mesa.getId());


        adicionarItensPedido(mesa);
    }

    private static void adicionarItensPedido(Mesa mesa) {
        if (!mesa.getPedidos().isEmpty()) {
            System.out.print("Digite o ID do pedido (1-" + mesa.getPedidos().size() + "): ");
            int idPedido = scanner.nextInt();
            Pedido pedido = mesa.getPedidos().get(idPedido - 1);

            while (true) {
                listarCardapio();


                System.out.print("Escolha um prato para adicionar ao pedido (1-" + Cardapio.getPratos().size() + ") ou 0 para sair: ");
                int escolhaPrato = scanner.nextInt();
                if (escolhaPrato == 0) {
                    break;
                } else if (escolhaPrato > 0 && escolhaPrato <= Cardapio.getPratos().size()) {
                    pedido.adicionarPrato(Cardapio.getPratos().get(escolhaPrato - 1));
                    System.out.println("Prato adicionado ao pedido da mesa " + mesa.getId());
                } else {
                    System.out.println("Escolha inválida.");
                }


                System.out.print("Escolha uma bebida para adicionar ao pedido (1-" + Cardapio.getBebidas().size() + ") ou 0 para sair: ");
                int escolhaBebida = scanner.nextInt();
                if (escolhaBebida == 0) {
                    break;
                } else if (escolhaBebida > 0 && escolhaBebida <= Cardapio.getBebidas().size()) {
                    pedido.adicionarBebida(Cardapio.getBebidas().get(escolhaBebida - 1));
                    System.out.println("Bebida adicionada ao pedido da mesa " + mesa.getId());
                } else {
                    System.out.println("Escolha inválida.");
                }
            }
        } else {
            System.out.println("Nenhum pedido na mesa " + mesa.getId());
        }
    }



    private static void resetarMesa() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);
        mesa.resetarMesa();
        System.out.println("Mesa " + mesa.getId() + " resetada.");
    }

    private static void adicionarItensPedido() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);

        if (!mesa.getPedidos().isEmpty()) {
            System.out.print("Digite o ID do pedido (1-" + mesa.getPedidos().size() + "): ");
            int idPedido = scanner.nextInt();
            Pedido pedido = mesa.getPedidos().get(idPedido - 1);

            listarCardapio();
            System.out.print("Escolha um prato (1-3): ");
            int escolhaPrato = scanner.nextInt();
            pedido.adicionarPrato(Cardapio.getPratos().get(escolhaPrato - 1));

            System.out.print("Escolha uma bebida (1-3): ");
            int escolhaBebida = scanner.nextInt();
            pedido.adicionarBebida(Cardapio.getBebidas().get(escolhaBebida - 1));

            System.out.println("Itens adicionados ao pedido da mesa " + mesa.getId());
        } else {
            System.out.println("Nenhum pedido na mesa " + mesa.getId());
        }
    }

    private static void removerItensPedido() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);

        if (!mesa.getPedidos().isEmpty()) {
            System.out.print("Digite o ID do pedido (1-" + mesa.getPedidos().size() + "): ");
            int idPedido = scanner.nextInt();
            Pedido pedido = mesa.getPedidos().get(idPedido - 1);

            System.out.println("Itens do pedido:");
            listarItensPedido(pedido);

            System.out.print("Escolha um prato para remover (1-" + pedido.getPratos().size() + "): ");
            int escolhaPrato = scanner.nextInt();
            if (escolhaPrato > 0 && escolhaPrato <= pedido.getPratos().size()) {
                pedido.removerPrato(pedido.getPratos().get(escolhaPrato - 1));
                System.out.println("Prato removido do pedido.");
            } else {
                System.out.println("Escolha inválida.");
            }

            System.out.print("Escolha uma bebida para remover (1-" + pedido.getBebidas().size() + "): ");
            int escolhaBebida = scanner.nextInt();
            if (escolhaBebida > 0 && escolhaBebida <= pedido.getBebidas().size()) {
                pedido.removerBebida(pedido.getBebidas().get(escolhaBebida - 1));
                System.out.println("Bebida removida do pedido.");
            } else {
                System.out.println("Escolha inválida.");
            }
        } else {
            System.out.println("Nenhum pedido na mesa " + mesa.getId());
        }
    }

    private static void listarItensPedido(Pedido pedido) {
        System.out.println("Pratos:");
        for (int i = 0; i < pedido.getPratos().size(); i++) {
            System.out.println((i + 1) + ". " + pedido.getPratos().get(i));
        }

        System.out.println("Bebidas:");
        for (int i = 0; i < pedido.getBebidas().size(); i++) {
            System.out.println((i + 1) + ". " + pedido.getBebidas().get(i));
        }
    }

    private static void listarCardapio() {
        System.out.println("\n=== Cardápio ===");
        System.out.println("Pratos:");
        for (int i = 0; i < Cardapio.getPratos().size(); i++) {
            System.out.println((i + 1) + ". " + Cardapio.getPratos().get(i));
        }

        System.out.println("Bebidas:");
        for (int i = 0; i < Cardapio.getBebidas().size(); i++) {
            System.out.println((i + 1) + ". " + Cardapio.getBebidas().get(i));
        }
    }

    private static void listarPedidoMesa() {
        System.out.print("Digite o ID da mesa(1-5): ");
        int idMesa = scanner.nextInt();
        Mesa mesa = Restaurante.getMesas().get(idMesa - 1);

        if (!mesa.getPedidos().isEmpty()) {
            System.out.print("Digite o ID do pedido (1-" + mesa.getPedidos().size() + "): ");
            int idPedido = scanner.nextInt();
            Pedido pedido = mesa.getPedidos().get(idPedido - 1);

            System.out.println("=== Pedido da Mesa " + mesa.getId() + " ===");
            listarItensPedido(pedido);
            System.out.println("Total do Pedido: R$" + pedido.getValorTotal());

        } else {
            System.out.println("Nenhum pedido na mesa " + mesa.getId());

        }

    }

}
