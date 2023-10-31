package com.ank.codeviewer.util;

public class ConvertNumber {
    public static double toAvgGrade(Double value){
        //Формат: 1 знак после точки
        return Math.ceil(value * 10) / 10;
    }
}
