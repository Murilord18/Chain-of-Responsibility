package com.banco.chain.transacao;


public class TipoTransacaoEmprestimo implements TipoTransacao {

    @Override
    public String getNome() {
        return "EMPRESTIMO";
    }

    @Override
    public double getValorMaximo() {
        return 100_000.00;
    }
}
