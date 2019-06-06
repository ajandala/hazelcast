package com.ajandala.hazelcast.cluster;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;


public class PrintCallable implements Callable<String>, Serializable, HazelcastInstanceAware {

    private transient HazelcastInstance hazelcastInstance;


    @Override public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override public String call() {
        System.out.println("Hola");

        StringBuilder stringBuilder = new StringBuilder();

        IMap<Long, String> map = hazelcastInstance.getMap("data");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getValue()).append(" ");
        }

        return stringBuilder.toString();
    }


}
