package br.edu.devmedia.jdbc.bo;

import br.edu.devmedia.jdbc.dao.LoginDAO;
import br.edu.devmedia.jdbc.dto.LoginDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;

public class LoginBO { //Classe
	
	public boolean logar(LoginDTO loginDTO) throws NegocioException {  //Método
		boolean resultado = false;
		try {
			if (loginDTO.getNome() == null || "".equals(loginDTO.getNome())) {
				throw new NegocioException("Login Obrigatório!");
			} else if (loginDTO.getSenha() == null || "".equals(loginDTO.getSenha())) {
				throw new NegocioException("Senha Obrigatório!");
			}else {
				LoginDAO loginDAO = new LoginDAO();

					resultado = loginDAO.logar(loginDTO);

			}
			
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
			
		}
		return resultado;
		
	}

}
