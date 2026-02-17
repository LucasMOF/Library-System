package model;

import java.time.LocalDate;

public class Emprestimo {
    private Pessoa pessoa;
    private ItemBiblioteca item;
    private LocalDate dataEmprestimo;
    private  boolean ativo;

    public Emprestimo(Pessoa pessoa, ItemBiblioteca item) {
        this.pessoa = pessoa;
        this.item = item;
        this.dataEmprestimo = LocalDate.now();
        this.ativo = true;
        item.reduzirQuantidade();
        pessoa.incrementarEmprestimos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void finalizarEmprestimo() {
        if (!ativo) {
            throw new IllegalStateException("Empréstimo já finalizado.");
        }
        ativo = false;
        item.aumentarQuantidade();
        pessoa.decrementarEmprestimos();
    }
}
