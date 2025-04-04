package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    private int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (versionHistoryMap.containsKey(rollbackVersion)) {
            Iterator<Map.Entry<Integer, String>> i=versionHistoryMap.entrySet().iterator();
            while (i.hasNext()){
                Map.Entry<Integer,String> entry=i.next();
                if (entry.getKey()>rollbackVersion) {
                    i.remove();
                }
            }
            currentVersion=rollbackVersion;
            return true;
        }else {
            return false;
        }
    }
}
