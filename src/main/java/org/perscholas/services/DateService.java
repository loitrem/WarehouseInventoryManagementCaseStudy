package org.perscholas.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateService {

    //old code do not use


    //convert from string to local date to date
    public Date changeToDate(String string){

        LocalDate local = LocalDate.parse(string);
        Date date = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }
}
