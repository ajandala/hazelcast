package com.ajandala.hazelcast.cluster;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.Map;


public class PrintRunnable implements Runnable, Serializable, HazelcastInstanceAware {

    private transient HazelcastInstance hazelcastInstance;


    @Override public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override public void run() {
        IMap<Long, String> map = hazelcastInstance.getMap("data");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(String.format("Key: %d, Value: %s", entry.getKey(), entry.getValue()));
        }
    }


}
