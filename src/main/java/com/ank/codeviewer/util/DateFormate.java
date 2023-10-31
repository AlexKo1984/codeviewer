package com.ank.codeviewer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormate {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy : HH.mm");
    public static String formatDatePost(LocalDateTime dateTime){
        return formatter.format(dateTime);
    }
}
