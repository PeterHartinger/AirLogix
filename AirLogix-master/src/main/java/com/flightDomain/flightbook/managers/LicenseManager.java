package com.flightDomain.flightbook.managers;

import com.flightDomain.flightbook.dao.LicenseDAO;
import com.flightDomain.flightbook.models.License;

import java.sql.Connection;
import java.util.List;

public class LicenseManager {
    private LicenseDAO licenseDAO;

    public LicenseManager(Connection conn) {
        this.licenseDAO = new LicenseDAO(conn);
    }

    public boolean addLicense(License license) {
        return licenseDAO.insertLicense(license);
    }

    public List<License> getLicenses() {
        return licenseDAO.getAllLicenses();
    }

    public boolean updateLicense(License license) {
        return licenseDAO.updateLicense(license);
    }

    public boolean deleteLicense(int licenseId) {
        return licenseDAO.deleteLicense(licenseId);
    }
}
