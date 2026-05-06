package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacaoPix;

public class FuncionarioAgente extends Funcionario {

    public FuncionarioAgente() {
        super(new TipoTransacaoPix());
    }

    @Override
    public String getNome() {
        return "Agente de Atendimento";
    }
}
