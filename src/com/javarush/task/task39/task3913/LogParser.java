package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    /*TreeMap<RecordEvent, String> data = new TreeMap<>(Comparator.comparing(o -> o.getDate()));
    Path path;

    public LogParser(Path logDir) {
        this.path = logDir;
        readLogFile();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
       return getUniqueIPs(after,before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Map<RecordEvent, String> submap;
        Set<String> values = new LinkedHashSet<>();
        if (after != null && before != null) {
            RecordEvent eventfirst = data.keySet().stream().filter(e -> e.getDate().after(after)).findFirst().get();
            RecordEvent eventlast = data.descendingKeySet().stream().filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.subMap(eventfirst, true, eventlast, true);
            values.addAll(submap.values());
        } else if (before!=null) {
            RecordEvent eventlast = data.descendingKeySet().stream().filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.headMap(eventlast,true);
            values.addAll(submap.values());
        } else if (after!=null) {
            RecordEvent eventfirst = data.keySet().stream().filter(e -> e.getDate().after(after)).findFirst().get();
            submap=data.tailMap(eventfirst,true);
            values.addAll(submap.values());
        }else {
            Collection<String> strings=data.values();
            values.addAll(data.values());
        }
        return values;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Map<RecordEvent, String> submap;
        Set<String> values = new HashSet<>();
        if (after != null && before != null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e -> e.getDate().after(after))
                    .filter(e->e.getUser().equals(user)).findFirst().get();
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e -> e.getDate().before(before))
                    .filter(e->e.getUser().equals(user))
                    .findFirst().get();
            submap = data.subMap(eventfirst, true, eventlast, true);
            values.addAll(submap.values());
        } else if (before!=null) {
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e->e.getUser().equals(user))
                    .filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.headMap(eventlast,true);
            values.addAll(submap.values());
        } else if (after!=null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e->e.getUser().equals(user))
                    .filter(e -> e.getDate().after(after)).findFirst().get();
            submap=data.tailMap(eventfirst,true);
            values.addAll(submap.values());
        }else {
            for (Map.Entry<RecordEvent,String> entry:data.entrySet()
                 ) {
                if (entry.getKey().getUser().equals(user)) {
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Map<RecordEvent, String> submap;
        Set<String> values = new HashSet<>();
        if (after != null && before != null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e->e.getEvent().equals(event))
                    .filter(e -> e.getDate().after(after)).findFirst().get();
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e->e.getEvent().equals(event))
                    .filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.subMap(eventfirst, true, eventlast, true);
            values.addAll(submap.values());
        } else if (before!=null) {
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e->e.getEvent().equals(event))
                    .filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.headMap(eventlast,true);
            values.addAll(submap.values());
        } else if (after!=null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e->e.getEvent().equals(event))
                    .filter(e -> e.getDate().after(after)).findFirst().get();
            submap=data.tailMap(eventfirst,true);
            values.addAll(submap.values());
        }else {
            for (Map.Entry<RecordEvent,String> entry:data.entrySet()
            ) {
                if (entry.getKey().getEvent().equals(event)) {
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Map<RecordEvent, String> submap;
        Set<String> values = new HashSet<>();
        if (after != null && before != null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e->e.getStatus().equals(status))
                    .filter(e -> e.getDate().after(after)).findFirst().get();
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e->e.getStatus().equals(status))
                    .filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.subMap(eventfirst, true, eventlast, true);
            values.addAll(submap.values());
        } else if (before!=null) {
            RecordEvent eventlast = data.descendingKeySet().stream()
                    .filter(e->e.getStatus().equals(status))
                    .filter(e -> e.getDate().before(before)).findFirst().get();
            submap = data.headMap(eventlast,true);
            values.addAll(submap.values());
        } else if (after!=null) {
            RecordEvent eventfirst = data.keySet().stream()
                    .filter(e->e.getStatus().equals(status))
                    .filter(e -> e.getDate().after(after)).findFirst().get();
            submap=data.tailMap(eventfirst,true);
            values.addAll(submap.values());
        }else {
            for (Map.Entry<RecordEvent,String> entry:data.entrySet()
            ) {
                if (entry.getKey().getStatus().equals(status)) {
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }

    private void readLogFile() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.log")) {
            for (Path entry : stream
            ) {
                List<String> list = Files.readAllLines(entry);
                for (String s : list
                ) {
                    RecordEvent recordEvent = getRecordEvent(s);
                    data.put(recordEvent, recordEvent.getIp());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RecordEvent getRecordEvent(String string) {
        RecordEvent result = new RecordEvent();
        String[] lines = string.split("\t");
        result.setIp(lines[0]);
        result.setUser(lines[1]);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            result.setDate(format.parse(lines[2]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (lines[3].contains("_TASK")) {
            String[] tasks = lines[3].split(" ");
            result.setEvent(Event.valueOf(tasks[0]));
            result.setTask(Integer.parseInt(tasks[1]));
        } else {
            result.setEvent(Event.valueOf(lines[3]));
        }
        result.setStatus(Status.valueOf(lines[4]));
        return result;
    }*/
    private Path logDir;
    private List<LogEntity> logEntities = new ArrayList<>();
    private DateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getIp());
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(event)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(status)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    private void readLogs() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path file : directoryStream) {
                if (file.toString().toLowerCase().endsWith(".log")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String[] params = line.split("\t");

                            if (params.length != 5) {
                                continue;
                            }

                            String ip = params[0];
                            String user = params[1];
                            Date date = readDate(params[2]);
                            Event event = readEvent(params[3]);
                            int eventAdditionalParameter = -1;
                            if (event.equals(Event.SOLVE_TASK) || event.equals(Event.DONE_TASK)) {
                                eventAdditionalParameter = readAdditionalParameter(params[3]);
                            }
                            Status status = readStatus(params[4]);

                            LogEntity logEntity = new LogEntity(ip, user, date, event, eventAdditionalParameter, status);
                            logEntities.add(logEntity);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date readDate(String lineToParse) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(lineToParse);
        } catch (ParseException e) {
        }
        return date;
    }

    private Event readEvent(String lineToParse) {
        Event event = null;
        if (lineToParse.contains("SOLVE_TASK")) {
            event = Event.SOLVE_TASK;
        } else if (lineToParse.contains("DONE_TASK")) {
            event = Event.DONE_TASK;
        } else {
            switch (lineToParse) {
                case "LOGIN": {
                    event = Event.LOGIN;
                    break;
                }
                case "DOWNLOAD_PLUGIN": {
                    event = Event.DOWNLOAD_PLUGIN;
                    break;
                }
                case "WRITE_MESSAGE": {
                    event = Event.WRITE_MESSAGE;
                    break;
                }
            }
        }
        return event;
    }

    private int readAdditionalParameter(String lineToParse) {
        if (lineToParse.contains("SOLVE_TASK")) {
            lineToParse = lineToParse.replace("SOLVE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        } else {
            lineToParse = lineToParse.replace("DONE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        }
    }

    private Status readStatus(String lineToParse) {
        Status status = null;
        switch (lineToParse) {
            case "OK": {
                status = Status.OK;
                break;
            }
            case "FAILED": {
                status = Status.FAILED;
                break;
            }
            case "ERROR": {
                status = Status.ERROR;
                break;
            }
        }
        return status;
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    private class LogEntity {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventAdditionalParameter;
        private Status status;

        public LogEntity(String ip, String user, Date date, Event event, int eventAdditionalParameter, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.eventAdditionalParameter = eventAdditionalParameter;
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getEventAdditionalParameter() {
            return eventAdditionalParameter;
        }

        public Status getStatus() {
            return status;
        }
    }
}