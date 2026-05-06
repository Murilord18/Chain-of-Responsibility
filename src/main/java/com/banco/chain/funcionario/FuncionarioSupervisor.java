package com.banco.chain.funcionario;

import com.banco.chain.transacao.TipoTransacaoEmprestimo;

public class FuncionarioSupervisor extends Funcionario {

    public FuncionarioSupervisor() {
        super(new TipoTransacaoEmprestimo());
    }

    @Override
    public String getNome() {
        return "Supervisor Bancário";
    }
}

