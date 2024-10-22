import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEventos {

    private static List<SistemaEventos.Evento> listaEventos = new ArrayList<>();
    private static List<SistemaEventos.Participante> listaParticipante = new ArrayList<>();
    private static List<SistemaEventos.Local> listaLocal = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    //=================== CLASSES ===================//

    public static class Evento {
        private String nome;
        private Local local;
        private List<Participante> participantes;
        private int numeroParticipantes;
        private double valor;

        public Evento(String nome, Local local, double valor) {
            this.nome = nome;
            this.local = local;
            this.participantes = new ArrayList<>();
            this.numeroParticipantes = 0;
            this.valor = valor;
        }

        public int getNumeroParticipantes() {
            return numeroParticipantes;
        }

        public void setNumeroParticipantes(int numeroParticipantes) {
            this.numeroParticipantes = numeroParticipantes;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Local getLocal() {
            return local;
        }

        public void setLocal(Local local) {
            this.local = local;
        }

        public List<Participante> getParticipantes() {
            return participantes;
        }

        public void setParticipantes(List<Participante> participantes) {
            this.participantes = participantes;
        }

        public double getValor(){
            return valor;
        }

        public void setValor(double valor){
            this.valor = valor;
        }
    }

    public static class Participante {
        private String nome;
        private String email;

        public Participante(String nome, String email) {
            this.nome = nome;
            this.email = email;
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
    }

    public static class Local {
        private String nome;
        private String endereco;
        private int capacidadeMaxima;

        public Local(String nome, String endereco, int capacidadeMaxima) {
            this.nome = nome;
            this.capacidadeMaxima = capacidadeMaxima;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setCapacidadeMaxima(int capacidadeMaxima) {
            this.capacidadeMaxima = capacidadeMaxima;
        }
    }

    //======================== MAIN ======================//

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Cadastrar um Evento");
            System.out.println("2. Cadastrar um Participante");
            System.out.println("3. Verificar Lotacao");
            System.out.println("4. Gerar Lista de Incricoes");
            System.out.println("5. Gerar Relatorio");
            System.out.println("6. Remover Evento");
            System.out.println("7. Remover Participante");
            System.out.println("8. Listar Eventos e Lotacao");
            System.out.println("9. Listar o Valor arrecadado pelo Evento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEventos();
                    break;
                case 2:
                    registrarParticipante();
                    break;
                case 3:
                    verificarLotacao();
                    break;
                case 4:
                    gerarlistaIncricoes();
                    break;
                case 5:
                    gerarRelatorioParticipante();
                    break;
                case 6:
                    removerEvento();
                    break;
                case 7:
                    removerParticipante();
                    break;
                case 8:
                    listarEventosPopularidade();
                    break;
                case 9:
                    valorArrecadado();
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


    public static void cadastrarEventos() {
        System.out.println("Digite o nome do Evento: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                System.out.println("Esse evento ja esta Cadastrado!");
                return;
            }
        }
        System.out.println("Digite o Local do Evento: ");
        String nomeLocal = scanner.nextLine();
        System.out.println("Digite o Endereco: ");
        String enderecoLocal = scanner.nextLine();
        System.out.println("Digite a Capacidade Maxima do Evento: ");
        int capacidadeMaximaLocal = scanner.nextInt();
        System.out.println("Digite o valor do ingresso: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Local novoLocal = new Local(nomeLocal, enderecoLocal, capacidadeMaximaLocal);
        Evento novoEvento = new Evento(nomeEvento, novoLocal, valor);
        listaEventos.add(novoEvento);
        System.out.println("Evento adicionado com sucesso!");
    }

    public static void registrarParticipante() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o nome do Evento no qual deseja adicionar o Participante: ");
        String nomeEvento = scanner.nextLine();

        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                if (evento.numeroParticipantes < evento.local.capacidadeMaxima) {
                    System.out.println("Digite o nome do Participante: ");
                    String nomeParticipante = scanner.nextLine();
                    System.out.println("Digite o email: ");
                    String emailParticipante = scanner.nextLine();
                    Participante novoParticipante = new Participante(nomeParticipante, emailParticipante);
                    evento.getParticipantes().add(novoParticipante);
                    System.out.println("Participante adicionado com sucesso!");
                    evento.numeroParticipantes++;
                    return;
                } else {
                    System.out.println("Evento Lotado!");
                    return;
                }
            }


        }
        System.out.println("O evento nao existe!");
    }


    public static void verificarLotacao() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento que deseja Consultar: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                System.out.println("A lotacao atual eh: " + evento.numeroParticipantes + "/" + evento.local.capacidadeMaxima);
                return;
            }
        }
        while (true) {
            System.out.println("Evento nao encontrado!");
            return;
        }
    }

    public static void gerarlistaIncricoes() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento que deseja Consultar a Lista de Participantes: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                for (Participante participante : evento.getParticipantes()) {
                    System.out.println(participante.getNome());
                }
                return;
            }
        }
        System.out.println("Evento não encontrado!");
    }


    public static void gerarRelatorioParticipante() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento: ");
        String nomeEvento = scanner.nextLine();
        System.out.println("Digite o Participante que deseja Gerar Relatorio:  ");
        String nomeParticipante = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                for (Participante participante : evento.getParticipantes()) {
                    if (participante.getNome().equalsIgnoreCase(nomeParticipante)) {
                        System.out.println("Participante: " + participante.getNome() +
                                "\nEvento: " + evento.getNome() +
                                "\nEmail: " + participante.getEmail());
                        return;
                    }
                }
                System.out.println("Participante nao encontrado no evento!");
                return;
            }
        }
    }

    public static void removerParticipante() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                System.out.println("Digite o nome do Participante que deseja remover: ");
                String nome = scanner.nextLine();
                Participante toRemove = null;
                for (Participante participante : evento.getParticipantes()) {
                    if (participante.getNome().equalsIgnoreCase(nome)) {
                        toRemove = participante;
                        break;
                    }
                }
                if (toRemove != null) {
                    evento.getParticipantes().remove(toRemove);
                    evento.numeroParticipantes--;
                    System.out.println("Participante removido com sucesso!");
                } else {
                    System.out.println("Participante não encontrado!");
                }
                return;
            }
        }
        System.out.println("Evento não encontrado!");
    }

    public static void removerEvento() {
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                listaEventos.remove(evento);
                System.out.println("Evento removido com sucesso!");
                return ;
            }
        }
        System.out.println("Evento nao encontrado!");
    }


    public static void listarEventosPopularidade(){
        for(Evento evento : listaEventos){
            double ocupacao = (double) evento.getNumeroParticipantes() / evento.getLocal().capacidadeMaxima * 100;
            System.out.println("\nEvento: "+ evento.getNome() + "\nLotacao: " + evento.getNumeroParticipantes() +"/" + evento.getLocal().capacidadeMaxima
            + " - " + ocupacao+ "%");
        }
    }

    public static void valorArrecadado(){
        for (Evento evento : listaEventos) {
            System.out.println("Evento: " + evento.getNome());
        }
        System.out.println("Digite o Evento: ");
        String nomeEvento = scanner.nextLine();
        for (Evento evento : listaEventos) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)){
                System.out.println("O valor arrecadado eh: R$" + evento.numeroParticipantes* evento.valor );
            }
        }

    }
}
