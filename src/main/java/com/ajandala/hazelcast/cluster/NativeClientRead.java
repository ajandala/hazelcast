package com.ajandala.hazelcast.cluster;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;
import com.hazelcast.core.Member;
import com.hazelcast.core.MultiExecutionCallback;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NativeClientRead {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
//        IMap<Long, String> map = hazelcastInstanceClient.getMap("data");
//        for (Map.Entry<Long, String> entry : map.entrySet()) {
//            System.out.println(String.format("Key: %d, Value: %s", entry.getKey(), entry.getValue()));
//        }
//        hazelcastInstanceClient.getExecutorService("executorService").execute(new PrintRunnable());
        hazelcastInstanceClient.getExecutorService("executorService").submitToKeyOwner(new PrintCallable(), 1, new ExecutionCallback<String>() {
            @Override public void onResponse(String s) {
                System.out.println(s);
            }

            @Override public void onFailure(Throwable throwable) {

            }
        });
    }
}
