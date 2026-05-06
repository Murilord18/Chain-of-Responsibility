package com.banco.chain.transacao;


public class TipoTransacaoTed implements TipoTransacao {

    @Override
    public String getNome() {
        return "TED";
    }

    @Override
    public double getValorMaximo() {
        return 20_000.00;
    }
}
