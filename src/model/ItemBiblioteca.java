package model;

public abstract class ItemBiblioteca {
    private String titulo;
    private int codigo;
    private int quantidadeDisponivel;
    private boolean disponivel;

    public ItemBiblioteca(String titulo, int codigo, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void reduzirQuantidade() {
        if (quantidadeDisponivel <= 0) {
            throw new IllegalStateException("Item indisponível para empréstimo.");
        } else {
            quantidadeDisponivel--;
        }
    }

    public void aumentarQuantidade() {
        quantidadeDisponivel++;
    }

    public boolean estaDisponivel() {
        return quantidadeDisponivel > 0;
    }
}


