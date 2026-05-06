package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacaoInvestimento;


public class FuncionarioDiretorGeral extends Funcionario {

    public FuncionarioDiretorGeral() {
        super(new TipoTransacaoInvestimento());
    }

    @Override
    public String getNome() {
        return "Diretor Geral";
    }
}
