package br.com.pvprojects.loja.util;

public class ConventionsHelper {

    public static final String DATA_PATTERN = "dd-MM-yyyy HH:mm:ss";
    public static final String INVALID_REQUEST = "Algo em sua requisição esta incorreto ou faltando.";
    public static final String COMPRA_REALIZADA = "A compra foi realizada com sucesso.";
    public static final String CUSTOMER_NOT_FOUND = "Customer não encontrado.";
    public static final String CREDENTIAL_NOT_FOUND = "Credential não encontrada.";
    public static final String CHANGE_EMAIL_LOGIN_NOT_FOUND = "O e-mail informado não pertence a um cliente.";
    public static final String PASSWORD_PATTERN = "O campo deve conter 6 caracteres numéricos.";
    public static final String AUTH_ERROR = "Não foi possível autenticar o usuário.";
    public static final String INVALID_PERFIL = "Perfil inválido.";
    public static final String LOGIN_UNIQUE = "O login informado já esta cadastrado.";
    public static final String BUREAU_ERROR = "O documento informado não foi encontrado.";
    public static final String INVALID_CPF_ERROR_MESSAGE = "CPF inválido.";
    public static final String CPF_NOT_PROVIDED_ERROR_MESSAGE = "CPF não informado.";


    public static final String EMPTY_NULL_LOGIN = "O login não pode ser vazio ou nulo.";
    public static final String INVALID_LOGIN = "O login informado é inválido.";
    public static final String EMPTY_NULL_PASSWORD = "O password não pode ser vazio ou nulo.";
    public static final String INVALID_PASSWORD = "O password não pode ser vazio ou nulo.";
    public static final String INVALID_PASSWORD_SIZE = "Password inválido. O password deve conter 6 dígitos.";
    public static final String INVALID_NUMBER = "O número não pode ser vazio ou nulo.";
    public static final String INVALID_DOCUMENT_TYPE = "O  tipo do documento não pode ser vazio ou nulo.";

    //API
    public static final String INTEGRATION_ERROR = "Erro inesperado conectando-se ao serviço do subsistema Mock.";

    //Customer
    public static final String CAMPO_CUSTOMERID_INVALIDO = "O customerId é inválido.";
    public static final String CAMPO_CUSTOMERID_NAO_INFORMADO = "O customerId não foi informado.";
    public static final String CAMPO_LOGIN_CUSTOMERID_INVALIDO = "O customerId ou o login é inválido.";

    //Credential
    public static final String CAMPO_NOME = "O nome do usuário não foi informado.";
    public static final String CAMPO_NOME_SIZE = "O nome do usuário não pode ter menos de 3 caracteres.";
    public static final String CAMPO_NICK_NAME = "O nickName do usuário não foi informado.";
    public static final String CAMPO_NICK_NAME_SIZE = "O nickName do usuário não pode ter menos de 3 caracteres.";
    public static final String CAMPO_EMAIL_INVALIDO = "O e-mail é inválido.";
    public static final String CAMPO_NOVO_EMAIL_INVALIDO = "O novo e-mail é inválido";
    public static final String CAMPO_NOVO_PASSWORD_INVALIDO = "A nova senha é inválida";
    public static final String CAMPO_EMAIL_NAO_INFORMADO = "O e-mail não foi informado.";
    public static final String CAMPO_DATA_ANIVERSARIO_NAO_INFORMADO = "A data de aniversario não foi informada.";
    public static final String CAMPO_NOME_MAE_NAO_INFORMADO = "O nome da mãe não foi informado.";

    //Document
    public static final String CAMPO_TIPO_DOCUMENTO = "O tipo do documento não foi informado.";
    public static final String CAMPO_TIPO_INVALIDO = "O tipo do documento é inválido.";
    public static final String CAMPO_NUMERO_DOCUMENTO = "O número do documento não foi informado.";
    public static final String CAMPO_NUMERO_DOC_INVALIDO = "O número do documento é inválido.";
    public static final String CAMPO_NUMERO_DOCUMENTO_SIZE = "O número do documento não pode ter menos de 5 " +
            "caracteres.";

    //Address
    public static final String CAMPO_NOME_NAO_INFORMADO = "O nome do endereço não foi informado.";
    public static final String CAMPO_ESTADO_INVALIDO = "O nome do estado é inválido.";
    public static final String CAMPO_ESTADO_NAO_INFORMADO = "O nome do estado não foi informado.";
    public static final String CAMPO_CEP_INVALIDO = "O nome do cep é inválido.";
    public static final String CAMPO_CEP_NAO_INFORMADO = "O nome do cep não foi informado.";
    public static final String CAMPO_CEP_SIZE = "O nome do cep não pode ter menos de 8 caracteres.";
    public static final String CAMPO_LOGRADOURO_INVALIDO = "O logradouro do estado é inválido.";
    public static final String CAMPO_LOGRADOURO_NAO_INFORMADO = "O logradouro do estado não foi informado.";
    public static final String CAMPO_BAIRRO_NAO_INFORMADO = "O bairro do endereço não foi informado.";
    public static final String CAMPO_BAIRRO_NAO_INVALIDO = "O bairro do endereço é inválido.";
    public static final String CAMPO_NUMERO_NAO_INFORMADO = "O número do endereço não foi informado.";
    public static final String CAMPO_NUMERO_INVALIDO = "O número do endereço é inválido.";
    public static final String CAMPO_CIDADE_NAO_INFORMADO = "A cidade do endereço não foi informada.";
    public static final String CAMPO_CIDADE_INVALIDO = "A cidade do endereço é inválida.";

    //Exception
    public static final String ERRO = "Erro";
    public static final String ERRO_REQUISICAO = "Erro de requisição.";
    public static final String ERRO_VALIDACAO = "Erro de validação.";
    public static final String ERRO_ENVIAR_EMAIL = "Erro ao enviar e-mail.";
    public static final String ERRO_HEADER_NOT_FOUND = "Header não encontrado.";
    public static final String ERRO_INVALID_NUMBER_EXCEPTION = "Numero inválido.";
    public static final String ERRO_SALVAR_ENDERECO = "Erro ao salvar o endereço.";
    public static final String ERRO_SALVAR_HISTORICO = "Erro ao salvar o historico.";
    public static final String ERRO_USUARIO_SEM_HISTORICO = "O usuário não possui nenhuma compra salva.";
    public static final String ERRO_CRIAR_CREDENTIAL = "Erro ao criar credential.";
    public static final String ERRO_CREDENTIAL_NAO_ENCONTRADA = "Credential não encontrada.";
    public static final String ERRO_ATUALIZAR_CREDENTIAL = "Erro ao atualizar a credential.";
    public static final String ERRO_ATUALIZAR_LOGIN = "Erro ao atualizar o login.";
    public static final String ERRO_ATUALIZAR_PASSWORD = "Erro ao atualizar a senha.";
    public static final String ERRO_RECUPERAR_PASSWORD = "Erro ao recuperar a senha.";
    public static final String ERRO_PARSE_RESPONSE = "Erro ao parsear response.";
    public static final String ERRO_CUSTOMER_ENUM = "Genero ou PersonType inválido.";
    public static final String ERRO_CRIAR_CUSTOMER ="Erro ao criar customer.";
    public static final String ERRO_ATUALIZAR_CUSTOMER = "Erro ao atualizar o customer.";
    public static final String ERRO_CRIAR_DOCUMENTS ="Erro ao criar o documento.";
    public static final String ERRO_ATUALIZAR_DOCUMENTS ="Erro ao atualizar o documento.";
    public static final String ERRO_DOCUMENTS_NOT_FOUND = "O usuário não possui nenhum documento salvo.";
    public static final String ERRO_DOCUMENTS_ALREADY_EXIST = " já cadastrado para o usuário.";
}