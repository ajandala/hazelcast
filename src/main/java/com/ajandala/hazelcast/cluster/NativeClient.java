package com.ajandala.hazelcast.cluster;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;

import java.util.Map;

public class NativeClient {


    public static void main(String[] args) throws InterruptedException {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        IdGenerator idGenerator = hazelcastInstanceClient.getIdGenerator("newid");
        IMap<Long, String> map = hazelcastInstanceClient.getMap("data");
        map.put(idGenerator.newId(), "one");
        map.put(idGenerator.newId(), "two");
        //            for (Map.Entry<Long, String> entry : map.entrySet()) {
        //                System.out.println(String.format("Key: %d, Value: %s", entry.getKey(), entry.getValue()));
        //            }
    }
}
