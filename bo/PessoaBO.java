package br.edu.devmedia.jdbc.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import br.edu.devmedia.jdbc.dao.PessoaDAO;
import br.edu.devmedia.jdbc.dto.EnderecoDTO;
import br.edu.devmedia.jdbc.dto.PessoaDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;
import br.edu.devmedia.jdbc.exception.ValidacaoException;

public class PessoaBO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public void cadastrar(PessoaDTO pessoaDTO) throws NegocioException {

		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.inserir(pessoaDTO);

		} catch (Exception exception) {
			throw new NegocioException(exception.getMessage());
		}

	}

	public List<PessoaDTO> listagem() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			return pessoaDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	
	@SuppressWarnings("static-access")
	public String[][] listagem(List<Integer> idsPessoas) throws NegocioException {
		int numCols = 10;
		String[][] listaRetorno = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<PessoaDTO> lista = pessoaDAO.listarTodos();
			listaRetorno = new String[lista.size()][numCols];

			for (int i = 0; i < lista.size(); i++) {
				PessoaDTO pessoa = lista.get(i);
				EnderecoDTO enderecoDTO = pessoa.getEnderecoDTO();
				
				listaRetorno[i][0] = pessoa.getIdPessoa().toString();
				idsPessoas.add(pessoa.getIdPessoa());
				listaRetorno[i][1] = pessoa.getNome();
				listaRetorno[i][2] = pessoa.getCpf().toString();
				listaRetorno[i][3] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
				listaRetorno[i][4] = dateFormat.format(pessoa.getDtNascimento());
				listaRetorno[i][5] = enderecoDTO.getLogradouro();
				listaRetorno[i][6] = enderecoDTO.getCep().toString();
				listaRetorno[i][7] = enderecoDTO.getUfDTO().getDescricao();
				listaRetorno[i][8] = "Editar";
				listaRetorno[i][9] = "Deletar";
			}

		} catch (Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
		return listaRetorno;
	}

	public boolean validaNome(String nome) throws ValidacaoException {
		boolean ehValido = true;
		if (nome == null || nome.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo nome é obrigatório!");
		} else if (nome.length() > 30) {
			ehValido = false;
			throw new ValidacaoException("Campo nome Comporta no maximo 30 caracteres!");
		}
		return ehValido;
	}

	public boolean validaCpf(String cpf) throws ValidacaoException {
		boolean ehValido = true;
		if (cpf == null || cpf.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo cpf é obrigatório!");
		} else {
			char[] digitos = cpf.toCharArray();
			for (char digito : digitos) {
				if (!Character.isDigit(digito)) {
					ehValido = false;
					throw new ValidacaoException("Campo cpf deve ter somente digitos");
				} else if (cpf.length() != 11) {
					ehValido = false;
					throw new ValidacaoException("Campo cpf deve ter 11 digitos");
				}
			}
		}
		return ehValido;
	}

	@SuppressWarnings("static-access")
	public boolean validaEndereco(EnderecoDTO enderecoDTO) throws ValidacaoException {
		boolean ehValido = true;
		if (enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo endereco é obrigatório!");
		} else if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo Bairro é Obrigatório");
		} else if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero().equals(0)) {
			ehValido = false;
			throw new ValidacaoException("Campo Número é Obrigatório");
		}else if (enderecoDTO.getCep() == null || enderecoDTO.getCep().equals(0)) {
			ehValido = false;
			throw new ValidacaoException("Campo CEP é Obrigatório");
		}
		return ehValido;
	}

	public boolean validaDtNasc(String dtNasc) throws ValidacaoException {
		boolean ehValido = true;
		if (dtNasc == null || dtNasc.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo Data de Nascimento é obrigatório!");
		} else {
			try {
				dateFormat.parse(dtNasc);
			} catch (ParseException e) {
				ehValido = false;
				throw new ValidacaoException("Formato Data de Nascimento é inválido!");
			}

		}
		return ehValido;
	}
	
	public String[][] listaConsulta(String nome, Long cpf, char sexo, String orderBy) throws NegocioException {
		int numCols = 6;
		String[][] listaRetorno = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<PessoaDTO> lista = pessoaDAO.filtraPessoa(nome, cpf, String.valueOf(sexo), orderBy);
			listaRetorno = new String[lista.size()][numCols];

			for (int i = 0; i < lista.size(); i++) {
				PessoaDTO pessoa = lista.get(i);
				listaRetorno[i][0] = pessoa.getIdPessoa().toString();
				listaRetorno[i][1] = pessoa.getNome();
				listaRetorno[i][2] = pessoa.getCpf().toString();
				listaRetorno[i][3] = pessoa.getEndereco();
				listaRetorno[i][4] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
				listaRetorno[i][5] = dateFormat.format(pessoa.getDtNascimento());
			}

		} catch (Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
		return listaRetorno;
	}
	
	public void removePessoa(Integer idPessoa, Integer idEndereco) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletar(idPessoa);
			pessoaDAO.deletarEndereco(idEndereco);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public void removeTudo() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletarTudo();
			
		}catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public PessoaDTO buscaPorId(Integer idPessoa) throws NegocioException {
		PessoaDTO pessoaDTO = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDTO = pessoaDAO.buscarPorId(idPessoa);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return pessoaDTO;
	}

}
