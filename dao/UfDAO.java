package br.edu.devmedia.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.devmedia.jdbc.ConexaoUtil;
import br.edu.devmedia.jdbc.dto.UfDTO;

public class UfDAO {
	
	public List<UfDTO> listaEstados(){
		List<UfDTO> lista = new ArrayList<UfDTO>();

		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM TB_UF";
			
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			
			ResultSet resultado = prepareStatement.executeQuery();
			
			while(resultado.next()) {
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUf(resultado.getInt(1));
				ufDTO.setSiglaUf(resultado.getString(2));
				ufDTO.setDescricao(resultado.getString(3));
				
				lista.add(ufDTO);
			}
			connection.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}
