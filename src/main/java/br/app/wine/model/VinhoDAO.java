package br.app.wine.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.app.wine.utils.ConnectionFactory;

public class VinhoDAO {
	public void create(Vinho vinho) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "INSERT INTO vinho VALUES (?, ?, ?, ?, ?)";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, null);
			cmd.setString(2, vinho.getNome());
			cmd.setInt(3, vinho.getAno());
			cmd.setString(4, vinho.getTipo());
			cmd.setDouble(5, vinho.getValor());
			cmd.executeUpdate();
			cmd.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Vinho vinho) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "UPDATE vinho SET nome = ?, ano = ?, tipo = ?, valor = ?  WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, vinho.getNome());
			cmd.setInt(2, vinho.getAno());
			cmd.setString(3, vinho.getTipo());
			cmd.setDouble(4, vinho.getValor());
			cmd.setInt(5, vinho.getId());
			cmd.executeUpdate();
			cmd.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int id) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "DELETE FROM vinho WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			cmd.executeUpdate();
			cmd.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	public Vinho findById(int id) {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM vinho WHERE id = ?";
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();
			Vinho vinho = null;
			if (rs.next()) {
				vinho = new Vinho();
				vinho.setId(rs.getInt("id"));
				vinho.setNome(rs.getString("nome"));
				vinho.setAno(rs.getInt("ano"));
				vinho.setTipo(rs.getString("tipo"));
				vinho.setValor(rs.getDouble("valor"));
			}
			rs.close();
			cmd.close();
			con.close();
			return vinho;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Vinho> findAll() {
		try {
			Connection con = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM vinho";
			PreparedStatement cmd = con.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			List<Vinho> vinhos = new ArrayList<Vinho>();
			while (rs.next()) { 
				Vinho vinho = new Vinho();
				vinho.setId(rs.getInt("id"));
				vinho.setNome(rs.getString("nome"));
				vinho.setAno(rs.getInt("ano"));
				vinho.setTipo(rs.getString("tipo"));
				vinho.setValor(rs.getDouble("valor"));
				vinhos.add(vinho);
			}
			rs.close();
			cmd.close();
			con.close();
			return vinhos;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}