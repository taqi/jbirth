package com.keebraa.jbirth.dao.cassandra.hector;

import me.prettyprint.cassandra.model.thrift.ThriftCounterColumnQuery;
import me.prettyprint.cassandra.serializers.AbstractSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.Serializer;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.CounterQuery;
import me.prettyprint.hector.api.query.SliceQuery;

public class BasicCassandraDAO {
    private static BasicCassandraDAO singleton;
    private static final String DEFAULT_KEYSPACE_NAME = "jbirth";

    private static Cluster myCluster = HFactory.getOrCreateCluster("test-cluster", "localhost:9160");
    private String currentKeyspaceName;
    private Keyspace currentKeyspace;

    private BasicCassandraDAO(String keyspace) {
        currentKeyspaceName = keyspace;
    }

    private BasicCassandraDAO() {
        this(DEFAULT_KEYSPACE_NAME);
    }

    public static BasicCassandraDAO init(String keyspace) {
        singleton = new BasicCassandraDAO(keyspace);
        return singleton;
    }

    public static BasicCassandraDAO get() {
        if (singleton == null) {
            singleton = new BasicCassandraDAO();
        }
        return singleton;
    }

    public void truncate(String columnFamily) {
        myCluster.truncate(currentKeyspaceName, columnFamily);
    }

    public <T1, T2> ColumnFamilyTemplate<T1, T2> constructTemplate(String columnFamily, AbstractSerializer<T1> keySerializer,
            AbstractSerializer<T2> topSerializer) {
        currentKeyspace = HFactory.createKeyspace(currentKeyspaceName, myCluster);
        ColumnFamilyTemplate<T1, T2> template = new ThriftColumnFamilyTemplate<T1, T2>(currentKeyspace, columnFamily, keySerializer, topSerializer);
        return template;
    }

    public <K, N, V> SliceQuery<K, N, V> constructSliceQuery(Serializer<K> keyspaceSerializer, Serializer<N> nameSerializer, Serializer<V> valueSerializer) {
        SliceQuery<K, N, V> sliceQuery = HFactory.createSliceQuery(currentKeyspace, keyspaceSerializer, nameSerializer, valueSerializer);
        return sliceQuery;
    }

    public <K, N> CounterQuery<K, N> constructCounterQuery(Serializer<K> keySerializer, Serializer<N> nameSerializer) {
        return new ThriftCounterColumnQuery<K, N>(currentKeyspace, keySerializer, nameSerializer);
    }
}
