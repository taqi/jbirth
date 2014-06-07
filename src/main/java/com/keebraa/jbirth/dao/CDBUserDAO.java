package com.keebraa.jbirth.dao;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ColumnFamilyUpdater;
import me.prettyprint.cassandra.service.template.IndexedSlicesPredicate;

import org.apache.cassandra.thrift.IndexOperator;

import com.keebraa.jbirth.dao.helpers.UserHelper;
import com.keebraa.jbirth.dao.objects.DBUser;
import com.keebraa.jbirth.helpers.StringHelper;

public class CDBUserDAO {
    public static final String USERS_COLUMN_FAMILY = "users";
    public static final String USERS_ID_PREFIX = "USR";
    public static final String USER_GROUPS_COLUMN_FAMILY = "users_groups";

    private ColumnFamilyTemplate<String, String> usersTemplate;
    private ColumnFamilyTemplate<String, String> userGroupsTemplate;

    private static CDBUserDAO singletone;

    private CDBUserDAO() {
        usersTemplate = BasicCassandraDAO.get().constructTemplate(USERS_COLUMN_FAMILY, StringSerializer.get(), StringSerializer.get());
        userGroupsTemplate = BasicCassandraDAO.get().constructTemplate(USER_GROUPS_COLUMN_FAMILY, StringSerializer.get(), StringSerializer.get());
    }

    public static CDBUserDAO get() {
        if (singletone == null) {
            singletone = new CDBUserDAO();
        }
        return singletone;
    }

    public DBUser getUserById(String id) {
        ColumnFamilyResult<String, String> result = usersTemplate.queryColumns(id);
        ColumnFamilyResult<String, String> groupsResult = userGroupsTemplate.queryColumns(id);
        return UserHelper.mapUserFromResult(result, groupsResult);
    }

    public void delete(String id) {
        usersTemplate.deleteRow(id);
        userGroupsTemplate.deleteRow(id);
    }

    public String store(DBUser dbUser) {
        if (StringHelper.isEmpty(dbUser.getId())) {
            String id = StringHelper.createNewId(USERS_ID_PREFIX);
            dbUser.setId(id);
        }
        ColumnFamilyUpdater<String, String> userUpdater = usersTemplate.createUpdater(dbUser.getId());
        ColumnFamilyUpdater<String, String> groupsUpdater = userGroupsTemplate.createUpdater(dbUser.getId());
        UserHelper.setDBUserToUpdater(dbUser, userUpdater);
        UserHelper.setDBUserGroupsToUpdater(dbUser.getGroups(), groupsUpdater);

        usersTemplate.update(userUpdater);
        userGroupsTemplate.update(groupsUpdater);
        return dbUser.getId();
    }

    public DBUser getUserByLogin(String login) {
        IndexedSlicesPredicate<String, String, String> predicate = new IndexedSlicesPredicate<String, String, String>(StringSerializer.get(),
                StringSerializer.get(), StringSerializer.get());
        predicate.addExpression("login", IndexOperator.EQ, login);
        ColumnFamilyResult<String, String> result = usersTemplate.queryColumns(predicate);
        ColumnFamilyResult<String, String> groupsResult = userGroupsTemplate.queryColumns(result.getKey());
        DBUser user = UserHelper.mapUserFromResult(result, groupsResult);
        return user;
    }
}
