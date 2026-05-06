package com.banco.chain.transacao;


public class TipoTransacaoInvestimento implements TipoTransacao {

    @Override
    public String getNome() {
        return "INVESTIMENTO";
    }

    @Override
    public double getValorMaximo() {
        return Double.MAX_VALUE;
    }
}