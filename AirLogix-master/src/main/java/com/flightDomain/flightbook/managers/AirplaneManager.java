package com.flightDomain.flightbook.managers;

import com.flightDomain.flightbook.dao.AirplaneDAO;
import com.flightDomain.flightbook.models.Airplane;

import java.sql.Connection;
import java.util.List;

public class AirplaneManager {
    private AirplaneDAO airplaneDAO;

    public AirplaneManager(Connection conn) {
        this.airplaneDAO = new AirplaneDAO(conn);
    }

    public boolean addAirplane(Airplane airplane) {
        return airplaneDAO.insertAirplane(airplane);
    }

    public List<Airplane> getAirplanes() {
        return airplaneDAO.getAllAirplanes();
    }

    public boolean updateAirplane(Airplane airplane) {
        return airplaneDAO.updateAirplane(airplane);
    }

    public boolean deleteAirplane(int airplaneId) {
        return airplaneDAO.deleteAirplane(airplaneId);
    }

    public Airplane getAirplaneByType(String type) {
        return airplaneDAO.getAirplaneByType(type);
    }
}
