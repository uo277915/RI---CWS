package uo.ri.cws.application.persistence.recommendation.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class RecommendationGatewayImpl implements RecommendationGateway {

	@Override
	public void add(RecommendationRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("RECOMMENDATION_REMOVE"));
			pst.setString(1, id);
			pst.setString(2, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(RecommendationRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public List<RecommendationRecord> findBySponsorId(String id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RecommendationRecord> rr;

		try {

			ps = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("RECOMMENDATION_FINDBYSPONSORID"));
			ps.setString(1, id);
			rs = ps.executeQuery();

			rr = RecordAssembler.toRecommendationRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(rs, ps); // c
		}
		return rr;
	}

	@Override
	public List<RecommendationRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RecommendationRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

}
