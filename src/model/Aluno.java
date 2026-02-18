package model;

public class Aluno extends Pessoa {

    public Aluno(String nome, int cpf, int quantidadeEmprestimosAtivos) {
        super(nome, cpf, quantidadeEmprestimosAtivos);
    }

    @Override
    public int getLimiteDeEmprestimos() {
        return 3;
    }
}
