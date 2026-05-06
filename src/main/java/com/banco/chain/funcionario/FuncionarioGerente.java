package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacaoTed;

public class FuncionarioGerente extends Funcionario {

    public FuncionarioGerente() {
        super(new TipoTransacaoTed());
    }

    @Override
    public String getNome() {
        return "Gerente de Conta";
    }
}
