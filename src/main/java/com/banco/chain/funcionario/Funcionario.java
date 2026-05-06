package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacao;


public abstract class Funcionario {

    protected Funcionario proximo;
    protected TipoTransacao tipoTransacaoResponsavel;

    public Funcionario(TipoTransacao tipoTransacaoResponsavel) {
        this.tipoTransacaoResponsavel = tipoTransacaoResponsavel;
    }

    public void setProximo(Funcionario proximo) {
        this.proximo = proximo;
    }


    public void processar(Solicitacao solicitacao) {
        if (podeAssinar(solicitacao)) {
            assinar(solicitacao);
        } else if (proximo != null) {
            proximo.processar(solicitacao);
        } else {
            System.out.println("Nenhum funcionário pôde aprovar: " + solicitacao);
        }
    }


    protected boolean podeAssinar(Solicitacao solicitacao) {
        return solicitacao.getTipoTransacao().getNome()
                .equals(tipoTransacaoResponsavel.getNome());
    }


    protected void assinar(Solicitacao solicitacao) {
        solicitacao.setAprovadoPor(getNome());
        System.out.println("[APROVADO] " + getNome() + " aprovou: " + solicitacao);
    }


    public abstract String getNome();
}
