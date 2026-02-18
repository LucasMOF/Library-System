package repository;

import model.Emprestimo;
import model.ItemBiblioteca;
import model.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BibliotecaRepository {

    private List<Pessoa> pessoas;
    private List<ItemBiblioteca> itens;
    private List<Emprestimo> emprestimos;

    public BibliotecaRepository() {
        this.pessoas = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public Optional<Pessoa> buscarPessoaPorCpf(int cpf) {
        return pessoas.stream()
                .filter(p -> p.getCpf() == cpf)
                .findFirst();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void adicionarItem(ItemBiblioteca item) {
        itens.add(item);
    }

    public Optional<ItemBiblioteca> buscarItemPorCodigo(int codigo) {
        return itens.stream()
                .filter(i -> i.getCodigo() == codigo)
                .findFirst();
    }

    public List<ItemBiblioteca> getItens() {
        return itens;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
