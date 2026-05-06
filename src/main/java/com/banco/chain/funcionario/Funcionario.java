package com.banco.chain.funcionario;

import com.banco.chain.modelo.Solicitacao;
import com.banco.chain.transacao.TipoTransacao;

/**
 * Classe abstrata que define o handler da cadeia.
 * Equivalente à classe «abstract» Funcionario do diagrama original.
 *
 * Cada funcionário conhece seu próximo na cadeia e o tipo de transação
 * pelo qual é responsável.
 */
public abstract class Funcionario {

    protected Funcionario proximo;
    protected TipoTransacao tipoTransacaoResponsavel;

    public Funcionario(TipoTransacao tipoTransacaoResponsavel) {
        this.tipoTransacaoResponsavel = tipoTransacaoResponsavel;
    }

    /**
     * Define o próximo funcionário na cadeia de responsabilidade.
     */
    public void setProximo(Funcionario proximo) {
        this.proximo = proximo;
    }

    /**
     * Processa a solicitação. Se este funcionário for responsável pelo
     * tipo de transação, aprova. Caso contrário, passa adiante na cadeia.
     */
    public void processar(Solicitacao solicitacao) {
        if (podeAssinar(solicitacao)) {
            assinar(solicitacao);
        } else if (proximo != null) {
            proximo.processar(solicitacao);
        } else {
            System.out.println("Nenhum funcionário pôde aprovar: " + solicitacao);
        }
    }

    /**
     * Verifica se este funcionário é responsável por este tipo de transação.
     */
    protected boolean podeAssinar(Solicitacao solicitacao) {
        return solicitacao.getTipoTransacao().getNome()
                .equals(tipoTransacaoResponsavel.getNome());
    }

    /**
     * Realiza a assinatura/aprovação da solicitação.
     */
    protected void assinar(Solicitacao solicitacao) {
        solicitacao.setAprovadoPor(getNome());
        System.out.println("[APROVADO] " + getNome() + " aprovou: " + solicitacao);
    }

    /**
     * Retorna o nome do cargo do funcionário.
     */
    public abstract String getNome();
}
