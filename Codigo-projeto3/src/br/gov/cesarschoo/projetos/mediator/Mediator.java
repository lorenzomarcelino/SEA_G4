package br.gov.cesarschoo.projetos.mediator;


import br.gov.cesarschool.projetos.DAO.*;
import br.gov.cesarschool.projetos.doador.Doador;
import br.gov.cesarschool.projetos.doador.ItemDoacao;
import br.gov.cesarschool.projetos.ong.ONG;
import br.gov.cesarschool.projetos.util.ValidadorCNPJ;
import br.gov.cesarschool.projetos.util.ValidadorCPF;

public class Mediator {
    private static Mediator instance;
    private DAO arquivoDAO;

    private Mediator() {
        arquivoDAO = new DAO("diretorio/");
    }

    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    public boolean incluirDoador(Doador doador) {
        if (validarInformacoesDoador(doador)) {
            return arquivoDAO.incluirDoador(doador);
        }
        return false;
    }

    public boolean incluirONG(ONG ong) {
        if (validarInformacoesONG(ong)) {
            return arquivoDAO.incluirONG(ong);
        }
        return false;
    }

    private boolean validarInformacoesDoador(Doador doador) {
        if (doador.getNome() == null || doador.getNome().isEmpty()) {
            System.out.println("Nome do doador inválido.");
            return false;
        }

        if (doador.getCPF() == null || doador.getCPF().isEmpty()) {
            System.out.println("CPF do doador inválido.");
            return false;
        }

        if (!ValidadorCPF.ehCpfValido(doador.getCPF())) {
            System.out.println("CPF do doador inválido.");
            return false;
        }

        if (doador.getMetodoDePagamento() == null || doador.getMetodoDePagamento().isEmpty()) {
            System.out.println("Método de pagamento do doador inválido.");
            return false;
        }

        // Outras validações do doador
        // ...

        return true;
    }

    private boolean validarInformacoesONG(ONG ong) {
        if (ong.getNome() == null || ong.getNome().isEmpty()) {
            System.out.println("Nome da ONG inválido.");
            return false;
        }

        if (ong.getCNPJ() == null || ong.getCNPJ().isEmpty()) {
            System.out.println("CNPJ da ONG inválido.");
            return false;
        }
        if (!ValidadorCNPJ.validarCNPJ(ong.getCNPJ())) {
            System.out.println("CPF do doador inválido.");
            return false;
        }

        if (ong.getDescricao() == null || ong.getDescricao().isEmpty()) {
            System.out.println("Descrição da ONG inválida.");
            return false;
        }

        if (ong.getNumeroVoluntarios() <= 0) {
            System.out.println("Número de voluntários da ONG inválido.");
            return false;
        }

        // Outras validações da ONG
        // ...

        return true;
    }
    
    
    
    // em andamento
    private boolean validarInformacoesDoacao(ItemDoacao doacao) {
    	if(doacao.getItem() == null) {
    		return false;
    	}
    	if(doacao.getQuantidade() <= 0) {
    		return false;
    	}

        return true;
    }
    
    
    
    

    public boolean inserirDoador(Doador doador) {
        if (validarInformacoesDoador(doador) != false) {
        	arquivoDAO.incluirDoador(doador);
        	return true;
        }
        else {
        	System.out.println("Erro ao incluir doador");
        	return false;
        }
    }
    
    public boolean inserirONG(ONG ong) {
        if (validarInformacoesONG(ong) != false) {
        	arquivoDAO.incluirONG(ong);
        	return true;
        }
        else {
        	System.out.println("Erro ao incluir ONG");
        	return false;
        }
    }
    
    


}
