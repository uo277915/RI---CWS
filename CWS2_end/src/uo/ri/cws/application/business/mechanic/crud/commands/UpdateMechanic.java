package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.command.Command;

public class UpdateMechanic implements Command<Void>{

	private static String SQL = "update TMechanics " + "set name = ?, surname = ? " + "where id = ?";

	private MechanicDto mechanic;

	public UpdateMechanic(MechanicDto arg) {

		this.mechanic = arg;
	}

	public Void execute() throws BusinessException {

		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.name);
			pst.setString(2, mechanic.surname);
			pst.setString(3, mechanic.id);

			if (pst.executeUpdate() == 0) {
				throw new BusinessException("Incorrect Mechanic ID! Nothing has been updated.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return null;
	}
}
