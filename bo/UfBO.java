package br.edu.devmedia.jdbc.bo;

import java.util.List;

import br.edu.devmedia.jdbc.dao.UfDAO;
import br.edu.devmedia.jdbc.dto.UfDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;

public class UfBO {
	
	public List<UfDTO> listaUfs() throws NegocioException{
		List<UfDTO> lista = null;
		try {
			UfDAO ufDAO = new UfDAO();
			lista = ufDAO.listaEstados();
		}catch(Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return lista;
	}

}
