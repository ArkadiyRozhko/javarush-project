package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

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

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
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

    @Override
    public Set<String> getAllUsers() {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
           result.add(entity.user);
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                result.add(entity.user);
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.user.equals(user)) {
                    result.add(entity.getEvent());
                }
            }
        }
        return result.size();

    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getIp().equals(ip)) {
                    result.add(entity.user);
                }

            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.LOGIN)) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(entity.user);
                }
            }
        }
        return result;

    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.SOLVE_TASK)) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.SOLVE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.DONE_TASK)) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.DONE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity.user);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(event)) {
                    result.add(entity.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getStatus().equals(Status.FAILED)) {
                    result.add(entity.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getStatus().equals(Status.ERROR)) {
                    result.add(entity.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(Event.LOGIN)) {
                    result.add(entity.getDate());
                }
            }
        }
        if (result.size() == 0) {
            return null;
        }
        Date date= result.iterator().next();
        for (Date d:result
             ) {
            if (d.getTime()<date.getTime()) {
                date=d;
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(Event.SOLVE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity.getDate());
                }
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        Date date= result.iterator().next();
        for (Date d:result
        ) {
            if (d.getTime()<date.getTime()) {
                date=d;
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(Event.DONE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity.getDate());
                }
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        Date date= result.iterator().next();
        for (Date d:result
        ) {
            if (d.getTime()<date.getTime()) {
                date=d;
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(entity.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getUser().equals(user)&&entity.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(entity.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after,before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                result.add(entity.getEvent());
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getIp().equals(ip)) {
                    result.add(entity.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getUser().equals(user)) {
                    result.add(entity.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getStatus().equals(Status.FAILED)) {
                    result.add(entity.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getStatus().equals(Status.ERROR)) {
                    result.add(entity.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Set<LogEntity>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getEvent().equals(Event.SOLVE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity);
                }
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Set<LogEntity>result=new HashSet<>();
        for (LogEntity entity : logEntities
        ){
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getEvent().equals(Event.DONE_TASK)&&entity.getEventAdditionalParameter()==task) {
                    result.add(entity);
                }
            }
        }
        return result.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer>map=new HashMap<>();
        for (LogEntity entity:logEntities
             ) {
            if (dateBetweenDates(entity.getDate(),after,before)) {
                if (entity.getEvent().equals(Event.SOLVE_TASK)) {
                    int task=entity.eventAdditionalParameter;
                    map.put(task,getNumberOfAttemptToSolveTask(task,after,before));
                }
            }


        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer>map=new HashMap<>();
        for (LogEntity entity:logEntities
        ) {
            if (dateBetweenDates(entity.getDate(),after,before)){
                if (entity.getEvent().equals(Event.DONE_TASK)) {
                    int task=entity.eventAdditionalParameter;
                    map.put(task,getNumberOfSuccessfulAttemptToSolveTask(task,after,before));
                }
            }

        }
        return map;
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