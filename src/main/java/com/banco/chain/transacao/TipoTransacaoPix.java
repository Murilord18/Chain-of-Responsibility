package com.banco.chain.transacao;


public class TipoTransacaoPix implements TipoTransacao {

    @Override
    public String getNome() {
        return "PIX";
    }

    @Override
    public double getValorMaximo() {
        return 5_000.00;
    }
}
