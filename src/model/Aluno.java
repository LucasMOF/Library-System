package model;

public class Aluno extends Pessoa {

    public Aluno(String nome, String cpf, int quantidadeEmprestimosAtivos) {
        super(nome, cpf, quantidadeEmprestimosAtivos);
    }

    @Override
    public int getLimiteDeEmprestimos() {
        return 3;
    }
}
