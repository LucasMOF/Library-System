package service;

import model.Emprestimo;
import model.ItemBiblioteca;
import model.Pessoa;
import repository.BibliotecaRepository;

import java.util.Optional;

public class BibliotecaService {
    private BibliotecaRepository repository;

    public BibliotecaService(BibliotecaRepository repository) {
        this.repository = repository;
    }

    public void realizarEmprestimo(String cpf, int codigoItem) {
        Pessoa pessoa = repository.buscarPessoaPorCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        ItemBiblioteca item = repository.buscarItemPorCodigo(codigoItem)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (!pessoa.podeEmprestar()) {
            throw new RuntimeException("Limite de empréstimos atingido");
        }

        if (!item.estaDisponivel()) {
            throw new RuntimeException("Item indisponível");
        }

        Emprestimo emprestimo = new Emprestimo(pessoa, item);

        repository.adicionarEmprestimo(emprestimo);

    }

    public void finalizarEmprestimo(String cpf, int codigoItem) {
        Pessoa pessoa = repository.buscarPessoaPorCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Emprestimo emprestimo = repository.getEmprestimos().stream()
                .filter(e -> e.getPessoa().getCpf().equals(pessoa))
                .filter(e -> e.getItem().getCodigo() == codigoItem)
                .filter(Emprestimo::isAtivo)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empréstimo ativo não encontrado"));

        emprestimo.finalizarEmprestimo();
    }
}