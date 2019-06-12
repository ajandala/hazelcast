package com.ajandala.hazelcast.cluster;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;

public class NativeClient2 {


    public static void main(String[] args) throws InterruptedException {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        IdGenerator idGenerator = hazelcastInstanceClient.getIdGenerator("newid");
        IMap<Integer, Person> map = hazelcastInstanceClient.getMap("data");

        Person person1 = map.get(1);
        person1.setName("Modified Person Name");
        map.put(1, person1);

        //            for (Map.Entry<Long, String> entry : map.entrySet()) {
        //                System.out.println(String.format("Key: %d, Value: %s", entry.getKey(), entry.getValue()));
        //            }
    }
}
