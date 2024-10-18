import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaClinica {

    private static List<Medico> listaMedicos = new ArrayList<>();
    private static List<Paciente> listaPacientes = new ArrayList<>();
    private static List<Consulta> listaConsulta = new ArrayList<>();
    private static List<Avaliacao> listaAvaliacoes = new ArrayList<>();
    private static List<EspecialidadeConsulta> listaEspecialidades = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Gerenciar Medicos");
            System.out.println("2. Gerenciar Pacientes");
            System.out.println("3. Área da Administração");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerenciarMedicos();
                    break;
                case 2:
                    gerenciarPacientes();
                    break;
                case 3:
                    areaAdministracao();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarMedicos() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Gerenciar Medicos ===");
            System.out.println("1. Adicionar Medico");
            System.out.println("2. Remover Medico");
            System.out.println("3. Modificar Disponibilidade");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarMedico();
                    break;
                case 2:
                    removerMedico();
                    break;
                case 3:
                    modificarDisponibilidade();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarPacientes() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Gerenciar Pacientes ===");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Remover Paciente");
            System.out.println("3. Buscar Paciente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarPaciente();
                    break;
                case 2:
                    removerPaciente();
                    break;
                case 3:
                    buscarPaciente();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void areaAdministracao() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Área da Administração ===");
            System.out.println("1. Marcar Consulta");
            System.out.println("2. Lista de Consultas");
            System.out.println("3. Avaliação da Consulta");
            System.out.println("4. Listar Médicos por Consultas");
            System.out.println("5. Listar Especialidades por Consultas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    agendarConsulta();
                    break;
                case 2:
                    gerarRelatorio();
                    break;
                case 3:
                    avaliarConsulta();
                    break;
                case 4:
                    listarMedicosPorConsultas();
                    break;
                case 5:
                    listarEspecialidadesPorConsultas();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }



    public static void adicionarMedico() {
        System.out.println("Digite o nome do medico: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.println("O medico esta disponivel? (true/false): ");
        boolean disponivel = scanner.nextBoolean();
        scanner.nextLine();

        Medico novoMedico = new Medico(nome, especialidade, disponivel);
        listaMedicos.add(novoMedico);

       
        boolean found = false;
        for (EspecialidadeConsulta ec : listaEspecialidades) {
            if (ec.getEspecialidade().equals(especialidade)) {
                ec.setQuantidade(ec.getQuantidade() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            listaEspecialidades.add(new EspecialidadeConsulta(especialidade, 1));
        }

        System.out.println("Medico adicionado com sucesso!");
    }


    public static void removerMedico() {
        System.out.println("Digite o nome do medico que deseja remover: ");
        String nome = scanner.nextLine();
        for (Medico medico : listaMedicos) {
            if (medico.getNome().equalsIgnoreCase(nome)) {
                listaMedicos.remove(medico);
                System.out.println("Medico removido com sucesso!");
                return;
            }
        }
        System.out.println("Medico nao encontrado!");
    }

    public static void removerPaciente() {
        System.out.println("Digite o nome do paciente que deseja remover: ");
        String nome = scanner.nextLine();
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                listaPacientes.remove(paciente);
                System.out.println("Paciente removido com sucesso!");
                return;
            }
        }
        System.out.println("Paciente nao encontrado!");
    }

    public static void modificarDisponibilidade() {
        System.out.println("Digite o nome do medico: ");
        String nome = scanner.nextLine();
        for (Medico medico : listaMedicos) {
            if (medico.getNome().equalsIgnoreCase(nome)) {
                System.out.println("O medico esta disponivel agora? (true/false):");
                boolean disponivel = scanner.nextBoolean();
                medico.setDisponibilidade(disponivel);
                System.out.println("A disponibilidade foi alterada!");
                return;
            }
        }
        System.out.println("Medico nao encontrado!");
    }

    public static void agendarConsulta() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.nextLine();


        Paciente pacienteSelecionado = null;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePaciente)) {
                pacienteSelecionado = paciente;
                break;
            }
        }

        if (pacienteSelecionado == null) {
            System.out.println("Paciente não encontrado! Por favor, cadastre o paciente antes de agendar a consulta.");
            return;
        }

        System.out.println("Digite o nome do medico: ");
        String nomeMedico = scanner.nextLine();
        Medico medicoSelecionado = null;

        for (Medico medico : listaMedicos) {
            if (medico.getNome().equalsIgnoreCase(nomeMedico)) {
                medicoSelecionado = medico;
                break;
            }
        }

        if (medicoSelecionado == null || !medicoSelecionado.verificarDisponibilidade()) {
            System.out.println("Medico não está disponível!");
            return;
        }

        System.out.println("Digite a data da consulta:");
        String data = scanner.nextLine();
        System.out.println("Digite o horário da consulta:");
        String horario = scanner.nextLine();

        Avaliacao avaliacao = null;

        Consulta novaConsulta = new Consulta(pacienteSelecionado, medicoSelecionado, data, horario, avaliacao);
        novaConsulta.marcarConsulta();
        listaConsulta.add(novaConsulta);
    }

    public static void buscarPaciente() {
        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.nextLine().trim();

        boolean encontrado = false;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Paciente encontrado: " + paciente.getNome() + ", CPF: " + paciente.getCpf() + ", Endereço: " + paciente.getEndereco() + ", Idade: " + paciente.getIdade());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Paciente não encontrado com o nome: " + nome);
        }
    }


    public static void gerarRelatorio() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.nextLine();

        Paciente pacienteSelecionado = null;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePaciente)) {
                pacienteSelecionado = paciente;
                break;
            }
        }

        if (pacienteSelecionado == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.nextLine();

        System.out.println("Digite a data da consulta: ");
        String dataConsulta = scanner.nextLine();

        System.out.println("Digite o horário da consulta: ");
        String horarioConsulta = scanner.nextLine();

        Consulta consultaSelecionada = null;
        for (Consulta consulta : listaConsulta) {
            if (consulta.getPaciente().equals(pacienteSelecionado) &&
                    consulta.getMedico().getNome().equalsIgnoreCase(nomeMedico) &&
                    consulta.getData().equals(dataConsulta) &&
                    consulta.getHorario().equals(horarioConsulta)) {
                consultaSelecionada = consulta;
                break;
            }
        }

        if (consultaSelecionada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }


        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Paciente: ").append(consultaSelecionada.getPaciente().getNome())
                .append("\nMédico: ").append(consultaSelecionada.getMedico().getNome())
                .append("\nData: ").append(consultaSelecionada.getData())
                .append("\nHorário: ").append(consultaSelecionada.getHorario());


        Avaliacao avaliacao = consultaSelecionada.getAvaliacao();
        if (avaliacao != null) {
            relatorio.append("\nAvaliação: ").append(avaliacao.getNota())
                    .append("\nFeedback: ").append(avaliacao.getFeedback());
        } else {
            relatorio.append("\nAvaliação: Sem avaliação");
        }

        System.out.println(relatorio.toString());
    }


    public static void adicionarPaciente() {
        System.out.println("Digite o nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do paciente: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o endereço do paciente: ");
        String endereco = scanner.nextLine();
        System.out.println("Digite a idade do paciente: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        Paciente novoPaciente = new Paciente(nome, cpf, endereco, idade);
        listaPacientes.add(novoPaciente);
        System.out.println("Paciente adicionado com sucesso!");
    }


    public static class Paciente {

       
        private String nome;
        private String cpf;
        private String endereco;
        private int idade;

        public Paciente(String nome, String cpf, String endereco, int idade) {
            this.nome = nome;
            this.cpf = cpf;
            this.endereco = endereco;
            this.idade = idade;

        }

        
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }
    }

    public static class Medico {

        private String nome;
        private String especialidade;
        private boolean disponivel;

        public Medico(String nome, String especialidade, Boolean disponivel) {
            this.nome = nome;
            this.especialidade = especialidade;
            this.disponivel = disponivel;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEspecialidade() {
            return especialidade;
        }

        public void setEspecialidade(String especialidade) {
            this.especialidade = especialidade;
        }

        public boolean verificarDisponibilidade() {
            return disponivel;
        }

        public void setDisponibilidade(boolean disponivel) {
            this.disponivel = disponivel;
        }
    }

    public static class Avaliacao {

        private Consulta consulta;
        private int nota;
        private String feedback;

        public Avaliacao(Consulta consulta, int nota, String feedback) {
            this.consulta = consulta;
            this.nota = nota;
            this.feedback = feedback;
        }

        public Consulta getConsulta() {
            return consulta;
        }

        public void setConsulta(Consulta consulta) {
            this.consulta = consulta;
        }

        public int getNota() {
            return nota;
        }

        public void setNota(int nota) {
            this.nota = nota;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }
    }


    public static class Consulta {

        private Paciente paciente;
        private Medico medico;
        private String data;
        private String horario;
        private Avaliacao avaliacao;

        public Consulta(Paciente paciente, Medico medico, String data, String horario, Avaliacao avaliacao) {

            this.paciente = paciente;
            this.medico = medico;
            this.data = data;
            this.horario = horario;
            this.avaliacao = avaliacao;
        }

        public Paciente getPaciente(){
            return paciente;
        }

        public void setPaciente(Paciente paciente){
            this.paciente = paciente;
        }

        public Medico getMedico(){
            return medico;
        }

        public void setMedico(Medico medico){
            this.medico = medico;
        }

        public String getData(){
            return data;
        }

        public void setData(String data){
            this.data = data;
        }

        public String getHorario(){
            return horario;
        }

        public void setHorario(String horario){
            this.horario = horario;
        }

        public Avaliacao getAvaliacao(){
            return avaliacao;
        }

        public void setAvaliacao(Avaliacao avaliacao){
            this.avaliacao = avaliacao;
        }

        public void marcarConsulta() {
            if (medico.verificarDisponibilidade()) {
                System.out.println("Consulta marcada para o paciente " + paciente.getNome() + " com o medico " + medico.getNome() + " no dia " + data + " as " + horario);
                medico.setDisponibilidade(false);
            } else {
                System.out.println("O medico " + medico.getNome() + " nao esta disponivel.");
            }
        }
    }

    private static class MedicoConsulta {
        private Medico medico;
        private int quantidade;

        public MedicoConsulta(Medico medico, int quantidade) {
            this.medico = medico;
            this.quantidade = quantidade;
        }

        public Medico getMedico() {
            return medico;
        }

        public int getQuantidade() {
            return quantidade;
        }
    }

    private static class EspecialidadeConsulta {
        private String especialidade;
        private int quantidade;

        public EspecialidadeConsulta(String especialidade, int quantidade) {
            this.especialidade = especialidade;
            this.quantidade = quantidade;
        }

        public String getEspecialidade() {
            return especialidade;
        }

        public void setEspecialidade(String especialidade){
            this.especialidade = especialidade;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade){
            this.especialidade = especialidade;
        }
    }

    public static void avaliarConsulta() {
        System.out.println("Digite o nome do paciente: ");
        String nomePaciente = scanner.nextLine();

        Paciente pacienteSelecionado = null;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePaciente)) {
                pacienteSelecionado = paciente;
                break;
            }
        }

        if (pacienteSelecionado == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.println("Digite o nome do médico: ");
        String nomeMedico = scanner.nextLine();

        System.out.println("Digite a data da consulta: ");
        String dataConsulta = scanner.nextLine();

        System.out.println("Digite o horário da consulta: ");
        String horarioConsulta = scanner.nextLine();


        Consulta consultaSelecionada = null;
        for (Consulta consulta : listaConsulta) {
            if (consulta.getPaciente().equals(pacienteSelecionado) &&
                    consulta.getMedico().getNome().equalsIgnoreCase(nomeMedico) &&
                    consulta.getData().equals(dataConsulta) &&
                    consulta.getHorario().equals(horarioConsulta)) {
                consultaSelecionada = consulta;
                break;
            }
        }

        if (consultaSelecionada == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.println("Digite a nota (1 a 5): ");
        int nota = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite seu feedback (opcional): ");
        String feedback = scanner.nextLine();

        Avaliacao novaAvaliacao = new Avaliacao(consultaSelecionada, nota, feedback);

        consultaSelecionada.setAvaliacao(novaAvaliacao);

        listaAvaliacoes.add(novaAvaliacao);

        System.out.println("Avaliação registrada com sucesso!");
    }

    public static void listarMedicosPorConsultas() {
        List<MedicoConsulta> medicosConsultas = new ArrayList<>();


        for (Medico medico : listaMedicos) {
            int contagem = 0;
            for (Consulta consulta : listaConsulta) {
                if (consulta.getMedico().equals(medico)) {
                    contagem++;
                }
            }
            medicosConsultas.add(new MedicoConsulta(medico, contagem));
        }


        medicosConsultas.sort((mc1, mc2) -> Integer.compare(mc2.getQuantidade(), mc1.getQuantidade()));

        System.out.println("\n=== Médicos por quantidade de consultas ===");
        for (MedicoConsulta mc : medicosConsultas) {
            System.out.println("Médico: " + mc.getMedico().getNome() + ", Consultas: " + mc.getQuantidade());
        }
    }

    public static void listarEspecialidadesPorConsultas() {
        List<String> especialidades = new ArrayList<>();

        
        for (Medico medico : listaMedicos) {
            if (!especialidades.contains(medico.getEspecialidade())) {
                especialidades.add(medico.getEspecialidade());
            }
        }

        List<String> especialidadesComContagem = new ArrayList<>();


        for (String especialidade : especialidades) {
            int contagem = 0;
            for (Consulta consulta : listaConsulta) {
                if (consulta.getMedico().getEspecialidade().equals(especialidade)) {
                    contagem++;
                }
            }
            if (contagem > 0) {
                especialidadesComContagem.add(especialidade + " - " + contagem);
            }
        }


        especialidadesComContagem.sort((e1, e2) -> {
            int count1 = Integer.parseInt(e1.split(" - ")[1]);
            int count2 = Integer.parseInt(e2.split(" - ")[1]);
            return Integer.compare(count2, count1);
        });

        System.out.println("\n=== Especialidades por quantidade de consultas ===");
        for (String especialidade : especialidadesComContagem) {
            System.out.println("Especialidade: " + especialidade);
        }
    }


}











