package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.command.Command;

public class FindAllMechanics implements Command<List<MechanicDto>>{

	private static String SQL = "select id, dni, name, surname from TMechanics";

	public FindAllMechanics() {

	}

	// TODO: FIX

	public List<MechanicDto> execute() throws BusinessException {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<MechanicDto> mechanics = new ArrayList<MechanicDto>();

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();
			while (rs.next()) {

				MechanicDto toAdd = new MechanicDto();

				toAdd.id = rs.getString("id");
				toAdd.dni = rs.getString("dni");
				toAdd.name = rs.getString("name");
				toAdd.surname = rs.getString("surname");

				mechanics.add(toAdd);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanics;
	}
}
