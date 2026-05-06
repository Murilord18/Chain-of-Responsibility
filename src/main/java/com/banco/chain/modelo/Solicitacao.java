package com.banco.chain.modelo;

import com.banco.chain.transacao.TipoTransacao;

public class Solicitacao {

    private final String cliente;
    private final TipoTransacao tipoTransacao;
    private final double valor;
    private String aprovadoPor;

    public Solicitacao(String cliente, TipoTransacao tipoTransacao, double valor) {
        this.cliente = cliente;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.aprovadoPor = null;
    }

    public String getCliente() {
        return cliente;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public String getAprovadoPor() {
        return aprovadoPor;
    }

    public void setAprovadoPor(String aprovadoPor) {
        this.aprovadoPor = aprovadoPor;
    }

    public boolean foiAprovada() {
        return aprovadoPor != null;
    }

    @Override
    public String toString() {
        return String.format("Solicitacao{cliente='%s', tipo='%s', valor=%.2f, aprovadoPor='%s'}",
                cliente, tipoTransacao.getNome(), valor,
                foiAprovada() ? aprovadoPor : "PENDENTE");
    }
}