package exception;

import model.Emprestimo;

public class EmprestimoNaoEncontradoException extends RuntimeException {

    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
