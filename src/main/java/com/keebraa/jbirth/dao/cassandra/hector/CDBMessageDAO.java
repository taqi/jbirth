package com.keebraa.jbirth.dao.cassandra.hector;

import com.keebraa.jbirth.dao.objects.DBUser;

public class CDBMessageDAO {
    private static CDBMessageDAO singletone;

    private CDBMessageDAO() {

    }

    public static CDBMessageDAO get() {
        if (singletone == null) {
            singletone = new CDBMessageDAO();
        }
        return singletone;
    }

    public DBUser getMessagesForUser(String login) {
        return null;
    }
}
