package br.com.consultemed.utils;

public class Constantes {

//  PERSISTENCE_UNIT
	public static final String PERSISTENCE_UNIT_NAME = "CONSULTEMED";
	
	public static final String LOGOUT = "/j_spring_security_check";
	
	
//	REDIRECIONAMENTO DE PAGINAS
	public static final String PAGINA_LOGIN = "/login?faces-redirect=true";
	public static final String PAGINA_INDEX = "/index?faces-redirect=true";
	public static final String PAGINA_CADASTRO_PACIENTE = "/pacientes/cadastrar-paciente?faces-redirect=true";
	
	public static final String USUARIO_LOGADO = "usuarioLogado";
	
//	MENSAGENS DE USUÁRIO
	public static final String ERRO_EMAIL_JA_CADASTRADO = "Já existe um usuário cadastrado com esse email.";
	public static final String USUARIO_CADASTRADO = "Usuário salvo com sucesso.";
	public static final String ERRO_AO_EXCLUIR = "Não foi possível excluir.";
	public static final String USUARIO_REMOVIDO = "Usuário removido com sucesso.";
	public static final String ERRO_AO_ATUALIZAR = "Não foi possível atualizar o usuário.";
	public static final String PREENCHER_CAMPOS = "Você deve preencher os campos Login e Senha.";

	//	MENSAGENS DE PACIENTE
	public static final String CPF_JA_CADASTRADO = "Já existe um paciente cadastrado com esse cpf.";
	public static final String PACIENTE_CADASTRADO = "Paciente salvo com sucesso.";
	public static final String PACIENTE_REMOVIDO = "Paciente removido com sucesso.";
	public static final String CONTATO_CADASTRADO = "Contato adicionado com sucesso.";
	public static final String ERRO_ADICIONAR_CONTATO = "Não foi possível adicionar o contato.";
	public static final String ERRO_ALERTA_CONTATOS_VAZIOS = "Você deve adicionar pelo menos 1 contato.";
	
	//	MENSAGENS DE MEDICO
	public static final String ERRO_CRM_JA_CADASTRADO = "Já existe um médico cadastrado com esse CRM.";
	public static final String MEDICO_CADASTRADO = "Médico salvo com sucesso.";
	public static final String MEDICO_REMOVIDO = "Médico removido com sucesso.";
	public static final String ERRO_INSERIR_DIAS = "É necessário definir os dias de atendimento para o médico.";
	
//	MENSAGENS AGENDAMENTO
	public static final String AGENDAMENTO_CADASTRADO = "Agendamento salvo com sucesso.";
	public static final String AGENDAMENTO_REMOVIDO = "Agendamento excluido com sucesso.";
	public static final String AGENDAMENTO_CANCELADO = "Agendamento cancelado com sucesso.";
	public static final String ERRO_AGENDAMENTO_JA_CADASTRADO = "Já existe uma consulta agendada nesse horário para esta data.";
	public static final String ERRO_AO_CANCELAR = "O prazo para o cancelamento desta consulta já expirou.";
	
//	MENSAGENS DO SISTEMA
	public static final String ERRO_USUARIO_NAO_ENCONTRADO = "Usuário ou senha inválidos";
	
	
}
