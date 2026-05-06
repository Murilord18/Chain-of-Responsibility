package com.banco.chain;

import com.banco.chain.funcionario.*;
import com.banco.chain.modelo.Solicitacao;
import com.banco.chain.transacao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testes - Chain of Responsibility Bancário")
class BancoChainTest {

    private FuncionarioAgente agente;
    private FuncionarioGerente gerente;
    private FuncionarioSupervisor supervisor;
    private FuncionarioDiretor diretor;
    private FuncionarioDiretorGeral diretorGeral;

    @BeforeEach
    void montarCadeia() {
        agente       = new FuncionarioAgente();
        gerente      = new FuncionarioGerente();
        supervisor   = new FuncionarioSupervisor();
        diretor      = new FuncionarioDiretor();
        diretorGeral = new FuncionarioDiretorGeral();

        agente.setProximo(gerente);
        gerente.setProximo(supervisor);
        supervisor.setProximo(diretor);
        diretor.setProximo(diretorGeral);
    }


    // Testes de aprovação correta por cada elo da cadeia


    @Test
    @DisplayName("PIX deve ser aprovado pelo Agente de Atendimento")
    void pixDeveSerAprovadoPeloAgente() {
        Solicitacao solicitacao = new Solicitacao("João Silva", new TipoTransacaoPix(), 3_000.00);

        agente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada(), "A solicitação deveria ter sido aprovada");
        assertEquals("Agente de Atendimento", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("TED deve ser aprovado pelo Gerente de Conta")
    void tedDeveSerAprovadoPeloGerente() {
        Solicitacao solicitacao = new Solicitacao("Maria Oliveira", new TipoTransacaoTed(), 15_000.00);

        agente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada());
        assertEquals("Gerente de Conta", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("Empréstimo deve ser aprovado pelo Supervisor Bancário")
    void emprestimoDeveSerAprovadoPeloSupervisor() {
        Solicitacao solicitacao = new Solicitacao("Carlos Souza", new TipoTransacaoEmprestimo(), 80_000.00);

        agente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada());
        assertEquals("Supervisor Bancário", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("Financiamento deve ser aprovado pelo Diretor Regional")
    void financiamentoDeveSerAprovadoPeloDiretor() {
        Solicitacao solicitacao = new Solicitacao("Ana Lima", new TipoTransacaoFinanciamento(), 400_000.00);

        agente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada());
        assertEquals("Diretor Regional", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("Investimento deve ser aprovado pelo Diretor Geral")
    void investimentoDeveSerAprovadoPeloDiretorGeral() {
        Solicitacao solicitacao = new Solicitacao("Empresa XYZ", new TipoTransacaoInvestimento(), 2_000_000.00);

        agente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada());
        assertEquals("Diretor Geral", solicitacao.getAprovadoPor());
    }

    // Testes de tipo de transação

    @Test
    @DisplayName("TipoTransacaoPix deve ter nome PIX")
    void tipoPixDeveRetornarNomeCorreto() {
        TipoTransacao pix = new TipoTransacaoPix();
        assertEquals("PIX", pix.getNome());
    }

    @Test
    @DisplayName("TipoTransacaoTed deve ter nome TED")
    void tipoTedDeveRetornarNomeCorreto() {
        TipoTransacao ted = new TipoTransacaoTed();
        assertEquals("TED", ted.getNome());
    }

    @Test
    @DisplayName("TipoTransacaoEmprestimo deve ter nome EMPRESTIMO")
    void tipoEmprestimoDeveRetornarNomeCorreto() {
        TipoTransacao emp = new TipoTransacaoEmprestimo();
        assertEquals("EMPRESTIMO", emp.getNome());
    }

    @Test
    @DisplayName("TipoTransacaoFinanciamento deve ter nome FINANCIAMENTO")
    void tipoFinanciamentoDeveRetornarNomeCorreto() {
        TipoTransacao fin = new TipoTransacaoFinanciamento();
        assertEquals("FINANCIAMENTO", fin.getNome());
    }

    @Test
    @DisplayName("TipoTransacaoInvestimento deve ter nome INVESTIMENTO")
    void tipoInvestimentoDeveRetornarNomeCorreto() {
        TipoTransacao inv = new TipoTransacaoInvestimento();
        assertEquals("INVESTIMENTO", inv.getNome());
    }

    // Testes de Solicitacao

    @Test
    @DisplayName("Solicitacao deve iniciar não aprovada")
    void solicitacaoDeveIniciarNaoAprovada() {
        Solicitacao solicitacao = new Solicitacao("Pedro Costa", new TipoTransacaoPix(), 100.00);

        assertFalse(solicitacao.foiAprovada());
        assertNull(solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("Solicitacao deve registrar quem aprovou")
    void solicitacaoDeveRegistrarAprovador() {
        Solicitacao solicitacao = new Solicitacao("Luiza Mendes", new TipoTransacaoTed(), 10_000.00);

        agente.processar(solicitacao);

        assertNotNull(solicitacao.getAprovadoPor());
        assertEquals("Gerente de Conta", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("PIX não deve ser aprovado pelo Gerente")
    void pixNaoDeveSerAprovadoPeloGerente() {
        Solicitacao solicitacao = new Solicitacao("Rafael Torres", new TipoTransacaoPix(), 1_000.00);

        gerente.processar(solicitacao);

        assertFalse(solicitacao.foiAprovada(),
                "PIX não deve ser aprovado quando iniciado a partir do Gerente");
    }

    @Test
    @DisplayName("Cadeia parcial: TED aprovado diretamente pelo Gerente sem Agente")
    void tedAprovadoDiretamentePeloGerente() {
        Solicitacao solicitacao = new Solicitacao("Beatriz Alves", new TipoTransacaoTed(), 18_000.00);

        gerente.processar(solicitacao);

        assertTrue(solicitacao.foiAprovada());
        assertEquals("Gerente de Conta", solicitacao.getAprovadoPor());
    }

    @Test
    @DisplayName("Nomes dos cargos devem estar corretos")
    void nomesDosCargosDevemEstarCorretos() {
        assertEquals("Agente de Atendimento", agente.getNome());
        assertEquals("Gerente de Conta",       gerente.getNome());
        assertEquals("Supervisor Bancário",    supervisor.getNome());
        assertEquals("Diretor Regional",       diretor.getNome());
        assertEquals("Diretor Geral",          diretorGeral.getNome());
    }
}
