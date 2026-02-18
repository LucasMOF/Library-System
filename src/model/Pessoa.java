package model;

public abstract class Pessoa {
    private String nome;
    private int cpf;
    private int quantidadeEmprestimosAtivos;

    public Pessoa(String nome, int cpf, int quantidadeEmprestimosAtivos) {
        this.cpf = cpf;
        this.nome = nome;
        this.quantidadeEmprestimosAtivos = quantidadeEmprestimosAtivos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getQuantidadeEmprestimosAtivos() {
        return quantidadeEmprestimosAtivos;
    }

    public void setQuantidadeEmprestimosAtivos(int quantidadeEmprestimosAtivos) {
        this.quantidadeEmprestimosAtivos = quantidadeEmprestimosAtivos;
    }

    public void exibirdados() {
        System.out.println("Nome: " + this.nome + " | CPF: " + this.cpf);
    }

    public abstract int getLimiteDeEmprestimos();

    public boolean podeEmprestar() {
        return quantidadeEmprestimosAtivos < getLimiteDeEmprestimos();
    }

    public void incrementarEmprestimos() {
        quantidadeEmprestimosAtivos++;
    }

    public void decrementarEmprestimos() {
        if (quantidadeEmprestimosAtivos > 0) {
            quantidadeEmprestimosAtivos--;
        }
    }

}
