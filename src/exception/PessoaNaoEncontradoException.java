package exception;

public class PessoaNaoEncontradoException extends RuntimeException {

    public PessoaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
