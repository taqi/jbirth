package com.keebraa.jbirth.dao.helpers;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.service.template.ColumnFamilyResult;
import me.prettyprint.cassandra.service.template.ColumnFamilyUpdater;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.keebraa.jbirth.dao.objects.DBUser;
import com.keebraa.jbirth.rest.objects.RSUser;

public class UserHelper {
    public static final String ROLE_USER = "ROLE_USER";

    public static DBUser mapUserFromResult(ColumnFamilyResult<String, String> result, ColumnFamilyResult<String, String> groupsResult) {
        if (!result.hasResults()) {
            return null;
        }
        DBUser user = new DBUser();
        user.setId(result.getKey());
        user.setLogin(result.getString("login"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));
        user.setAge(result.getInteger("age"));
        // optional field
        if (result.getColumnNames().contains("email"))
            user.setEmail(result.getString("email"));
        user.setGroups(new ArrayList<String>());
        for (String group : groupsResult.getColumnNames()) {
            user.getGroups().add(group);
        }
        return user;
    }

    public static RSUser mapFromDBToRS(DBUser dbUser) {
        RSUser user = new RSUser();
        user.setAge(dbUser.getAge());
        user.setEmail(dbUser.getEmail());
        user.setLogin(dbUser.getLogin());
        user.setUserName(dbUser.getName());
        return user;
    }

    public static void setDBUserToUpdater(DBUser dbUser, ColumnFamilyUpdater<String, String> updater) {
        updater.setString("login", dbUser.getLogin());
        updater.setString("password", dbUser.getPassword());
        updater.setString("email", dbUser.getEmail());
        updater.setString("name", dbUser.getName());
        updater.setInteger("age", dbUser.getAge());
    }

    public static void setDBUserGroupsToUpdater(List<String> groups, ColumnFamilyUpdater<String, String> updater) {
        for (String group : groups) {
            updater.setString(group, "");
        }
    }

    public static List<GrantedAuthority> getAuthorities(DBUser user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<String> groups = user.getGroups();
        if (groups == null) {
            return authorities;
        }
        for (String group : groups) {
            authorities.add(new SimpleGrantedAuthority(group));
        }
        return authorities;
    }
}