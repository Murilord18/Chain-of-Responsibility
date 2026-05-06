package com.banco.chain.transacao;


public class TipoTransacaoFinanciamento implements TipoTransacao {

    @Override
    public String getNome() {
        return "FINANCIAMENTO";
    }

    @Override
    public double getValorMaximo() {
        return 500_000.00;
    }
}