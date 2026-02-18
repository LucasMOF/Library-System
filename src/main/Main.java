package main;

import model.*;
import repository.BibliotecaRepository;
import service.BibliotecaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static void exibirMenu() {
        System.out.println("===== SISTEMA BIBLIOTECA =====");
        System.out.println("1 - Cadastrar Pessoa\n" +
                "2 - Cadastrar Livro\n" +
                "3 - Realizar Empréstimo\n" +
                "4 - Finalizar Empréstimo\n" +
                "5 - Listar Livros Disponíveis\n" +
                "6 - Listar Livros Emprestados\n" +
                "7 - Buscar Livro por Título\n" +
                "8 - Ordenar Livros por Título\n" +
                "9 - Total de Empréstimos\n" +
                "0 - Sair");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BibliotecaRepository repository = new BibliotecaRepository();
        BibliotecaService service = new BibliotecaService(repository);

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Informe o nome: ");
                        String nome = scanner.nextLine();

                        System.out.println("Informe o CPF: (Apenas Números) ");
                        int cpf = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Informe (1) para aluno e (2) para professor: ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        if (tipo == 1) {
                            Aluno aluno = new Aluno(nome, cpf, 0);
                            service.cadastrarPessoa(aluno);

                        } else if (tipo == 2) {
                            Professor professor = new Professor(nome, cpf, 0);
                            service.cadastrarPessoa(professor);

                        } else {
                            System.out.println("tipo inválido!");
                            break;
                        }
                        System.out.println("Sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Informe o código do livro: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Informe o título do livro: ");
                        String titulo = scanner.nextLine();

                        System.out.println("Informe o autor do livro: ");
                        String autor = scanner.nextLine();

                        System.out.println("Informe a quantidade disponível: ");
                        int quantidadeDisponivel = scanner.nextInt();
                        scanner.nextLine();

                        Livro livro = new Livro(titulo, codigo, quantidadeDisponivel, autor);
                        service.cadastrarItem(livro);

                        System.out.println("Sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Informe o seu CPF: (Apenas Números) ");
                        int cpf = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Informe o código do livro: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();

                        service.realizarEmprestimo(cpf, codigo);

                        System.out.println("Sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Informe o seu CPF: (Apenas Números) ");
                        int cpf = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Informe o código do livro: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();

                        service.finalizarEmprestimo(cpf, codigo);

                        System.out.println("Sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 5:
                    List<ItemBiblioteca> lista = service.listarItensDisponiveis();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum livro disponivel!");

                    } else {
                        lista.forEach(item ->
                                System.out.println("Código: " + item.getCodigo() +
                                        " | Título: " + item.getTitulo() +
                                        " | Quantidade disponível: " + item.getQuantidadeDisponivel()));
                    }
                    break;

                case 6:
                    List<ItemBiblioteca> listaEmprestados = service.listarItensEmprestados();
                    if (listaEmprestados.isEmpty()) {
                        System.out.println("Nenhum livro emprestado no momento!");

                    } else {
                        listaEmprestados.forEach(emprestado ->
                                System.out.println("Código: " + emprestado.getCodigo() +
                                        " | Título: " + emprestado.getTitulo()));
                    }
                    break;

                case 7:
                    System.out.println("Informe o titulo do livro que deseja buscar: ");
                    String titulo = scanner.nextLine();

                    List<ItemBiblioteca> listagem = service.buscarLivroPorTitulo(titulo);

                    if (listagem.isEmpty()) {
                        System.out.println("Livro não encontrado!");
                    } else {
                        listagem.forEach(l ->
                                System.out.println("Código: " + l.getCodigo() +
                                        " | Título: " + l.getTitulo() +
                                        " | Quantidade dispnível: " + l.getQuantidadeDisponivel()));
                    }
                    break;

                case 8:
                    List<ItemBiblioteca> listar = service.listarItensOrdenadosPorTitulo();

                    if (listar.isEmpty()) {
                        System.out.println("Não há nada para ordenar");

                    } else {
                        listar.forEach(l -> System.out.println("Código: " + l.getCodigo() +
                                " | Título: " + l.getTitulo()));
                    }
                    break;

                case 9:
                    int qtd = service.totalEmprestimosRealizados();
                    System.out.println("Total de emprestimos: " + qtd);

                    long ativos = service.totalEmprestimosAtivos();
                    System.out.println("Total de emprestimos ativos: " + ativos);

                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");

            }
        }
    }
}
