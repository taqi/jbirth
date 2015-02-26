package com.keebraa.jbirth.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.keebraa.jbirth.dao.cassandra.hector.BasicCassandraDAO;
import com.keebraa.jbirth.dao.cassandra.hector.CDBUserDAO;
import com.keebraa.jbirth.dao.helpers.UserHelper;
import com.keebraa.jbirth.dao.objects.DBUser;

public class CDBUserDAOTest extends BasicCassandraTest {

    private static ColumnFamilyTemplate<String, String> usersTemplate;
    private static ColumnFamilyTemplate<String, String> userGroupsTemplate;

    @BeforeClass
    public static void init() {
        BasicCassandraDAO.init(BasicCassandraTest.TEST_KEYSPACE);
        BasicCassandraDAO.get().truncate(CDBUserDAO.USERS_COLUMN_FAMILY);
        BasicCassandraDAO.get().truncate(CDBUserDAO.USER_GROUPS_COLUMN_FAMILY);
        usersTemplate = BasicCassandraDAO.get().constructTemplate(CDBUserDAO.USERS_COLUMN_FAMILY, StringSerializer.get(), StringSerializer.get());
        userGroupsTemplate = BasicCassandraDAO.get().constructTemplate(CDBUserDAO.USER_GROUPS_COLUMN_FAMILY, StringSerializer.get(), StringSerializer.get());
    }

    @Test
    public void store_normalCase() {
        DBUser dbUser = new DBUser();
        dbUser.setLogin("userlogin");
        dbUser.setPassword("dbpassword");
        dbUser.setAge(12);
        dbUser.setEmail("some@example.com");
        dbUser.setName("somename");
        List<String> groups = new ArrayList<String>();
        groups.add("ROLE_USER");
        dbUser.setGroups(groups);
        String id = CDBUserDAO.get().store(dbUser);
        assertNotNull(id);
        assertNotNull(dbUser.getId());
        assertThat(dbUser.getId(), equalTo(id));

        ColumnFamilyResult<String, String> usersResult = usersTemplate.queryColumns(id);
        ColumnFamilyResult<String, String> groupsResult = userGroupsTemplate.queryColumns(id);
        assertNotNull(usersResult);
        assertNotNull(groupsResult);
        assertTrue(usersResult.hasResults());
        assertTrue(groupsResult.hasResults());
        
        DBUser savedUser = UserHelper.mapUserFromResult(usersResult, groupsResult);
        assertThat(savedUser.getId(), equalTo(dbUser.getId()));
        assertThat(savedUser.getLogin(), equalTo(dbUser.getLogin()));
        assertThat(savedUser.getPassword(), equalTo(dbUser.getPassword()));
        assertThat(savedUser.getAge(), equalTo(dbUser.getAge()));
        assertThat(savedUser.getName(), equalTo(dbUser.getName()));
        assertThat(savedUser.getEmail(), equalTo(dbUser.getEmail()));
        assertNotNull(savedUser.getGroups());
        assertFalse(savedUser.getGroups().isEmpty());
        assertThat(savedUser.getGroups().size(), equalTo(1));
        assertThat(savedUser.getGroups().get(0), equalTo(dbUser.getGroups().get(0)));
    }
}
