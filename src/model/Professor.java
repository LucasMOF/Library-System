package model;

public class Professor extends Pessoa{

    public Professor(String nome, int cpf, int quantidadeEmprestimosAtivos) {
        super(nome, cpf, quantidadeEmprestimosAtivos);
    }

    @Override
    public int getLimiteDeEmprestimos() {
        return 3;
    }
}
