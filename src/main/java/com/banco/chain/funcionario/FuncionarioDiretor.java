package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacaoFinanciamento;

public class FuncionarioDiretor extends Funcionario {

    public FuncionarioDiretor() {
        super(new TipoTransacaoFinanciamento());
    }

    @Override
    public String getNome() {
        return "Diretor Regional";
    }
}
