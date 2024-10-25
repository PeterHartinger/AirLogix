package com.flightDomain.flightbook;

import com.flightDomain.flightbook.dao.LogbookEntryDAO;
import com.flightDomain.flightbook.dao.UserDAO;
import com.flightDomain.flightbook.db.DatabaseManager;
import com.flightDomain.flightbook.managers.LogbookEntryManager;
import com.flightDomain.flightbook.managers.UserManager;
import com.flightDomain.flightbook.models.Airplane;
import com.flightDomain.flightbook.models.LogbookEntry;
import com.flightDomain.flightbook.models.User;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       UserManager userManager = new UserManager(DatabaseManager.getConnection());
        List<User> users = userManager.getUsers();
        LocalTime time = LocalTime.now();

        Airplane airplane = new Airplane("wsef","wef","wef");
        LogbookEntryManager lbm = new LogbookEntryManager(DatabaseManager.getConnection());
        LogbookEntryDAO ldao = new LogbookEntryDAO(DatabaseManager.getConnection());
        ldao.clearLogbookEntryTable();
        for(User user : users){
            System.out.println(user);
        }
//        List<LogbookEntry> logbookentries = lbm.getLogbookEntries();
//        for (LogbookEntry entry : logbookentries) {System.out.println(entry);}
    }
}

