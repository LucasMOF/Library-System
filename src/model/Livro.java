package model;

public class Livro extends ItemBiblioteca{
    private String autor;

    public Livro(String titulo, int codigo,  int quantidadeDisponivel, String autor) {
        super(titulo, codigo, quantidadeDisponivel);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


}
