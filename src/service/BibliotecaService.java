package service;

import exception.*;
import model.Emprestimo;
import model.ItemBiblioteca;
import model.Pessoa;
import repository.BibliotecaRepository;

import java.util.Comparator;
import java.util.List;

public class BibliotecaService {
    private BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public void realizarEmprestimo(String cpf, int codigoItem) {
        Pessoa pessoa = repository.buscarPessoaPorCpf(cpf)
                .orElseThrow(() -> new PessoaNaoEncontradoException("Pessoa não encontrada"));

        ItemBiblioteca item = repository.buscarItemPorCodigo(codigoItem)
                .orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado"));

        if (!pessoa.podeEmprestar()) {
            throw new LimiteEmprestimoException("Limite de empréstimos atingido");
        }

        if (!item.estaDisponivel()) {
            throw new LivroIndisponivelException("Item indisponível");
        }
        Emprestimo emprestimo = new Emprestimo(pessoa, item);
        repository.adicionarEmprestimo(emprestimo);
    }

    public void finalizarEmprestimo(String cpf, int codigoItem) {
        Pessoa pessoa = repository.buscarPessoaPorCpf(cpf)
                .orElseThrow(() -> new PessoaNaoEncontradoException("Pessoa não encontrada"));

        Emprestimo emprestimo = repository.getEmprestimos().stream()
                .filter(e -> e.getPessoa().getCpf().equals(cpf))
                .filter(e -> e.getItem().getCodigo() == codigoItem)
                .filter(Emprestimo::isAtivo)
                .findFirst()
                .orElseThrow(() -> new EmprestimoNaoEncontradoException("Empréstimo ativo não encontrado"));

        emprestimo.finalizarEmprestimo();
    }

    public void cadastrarItem(ItemBiblioteca item) {
        repository.adicionarItem(item);
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        repository.adicionarPessoa(pessoa);
    }

    public List<ItemBiblioteca> listarItensDisponiveis() {
        return repository.getItens()
                .stream()
                .filter(ItemBiblioteca::estaDisponivel)
                .toList();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return repository.getEmprestimos()
                .stream()
                .filter(Emprestimo::isAtivo)
                .toList();
    }

    public Pessoa buscarPessoa(String cpf) {
        return repository.buscarPessoaPorCpf(cpf)
                .orElseThrow(() -> new PessoaNaoEncontradoException("Pessoa não encontrada"));
    }

    public List<ItemBiblioteca> listarItensEmprestados() {
        return repository.getEmprestimos()
                .stream()
                .filter(Emprestimo::isAtivo)
                .map(Emprestimo::getItem)
                .distinct()
                .toList();
    }

    public List<ItemBiblioteca> buscarLivroPorTitulo(String titulo) {
        return repository.getItens()
                .stream()
                .filter(i -> i.getTitulo().equalsIgnoreCase(titulo))
                .toList();
    }

    public List<ItemBiblioteca> listarItensOrdenadosPorTitulo() {
        return repository.getItens()
                .stream()
                .sorted(Comparator.comparing(ItemBiblioteca::getTitulo))
                .toList();
    }

    public int totalEmprestimosRealizados() {
        return repository.getEmprestimos().size();
    }

    public long totalEmprestimosAtivos() {
        return repository.getEmprestimos()
                .stream()
                .filter(Emprestimo::isAtivo)
                .count();
    }


}