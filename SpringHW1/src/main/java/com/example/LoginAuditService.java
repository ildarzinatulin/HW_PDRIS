package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoginAuditService {
    private List<String> logs;

    public LoginAuditService() {
        logs = new ArrayList<>();
    }

    public void addLogs(Instant date, User user, boolean successfully, String error) {
        StringBuilder log = new StringBuilder();
        DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
        log.append(dateFormatDay.format(Date.from(date))).append(": ").append(user.getName());
        if (successfully) {
            log.append(" successfully login");
        } else {
            log.append(" unsuccessful login with error: ").append(error);
        }
        log.append("\n");
        logs.add(log.toString());
    }

    public String getLogs() {
        StringBuilder log = new StringBuilder();
        for (String s : logs) {
            log.append(s);
        }
        return log.toString();
    }
}
