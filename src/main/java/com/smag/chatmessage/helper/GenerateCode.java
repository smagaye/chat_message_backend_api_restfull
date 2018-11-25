/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smag.chatmessage.helper;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.StringTokenizer;

public class GenerateCode {

    public static String clefUTC(String prefixe) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        StringTokenizer dateSale = new StringTokenizer(now.toString(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:.,/!?\\-_");
        String dateUTC = prefixe;
        while (dateSale.hasMoreTokens()) {
            dateUTC += dateSale.nextToken();
        }
        return (dateUTC + "000").substring(0, 20);
    }

    public static int getRandomInteger(){
        Random rand = new Random();
        int n = rand.nextInt(Integer.MAX_VALUE) + 1;
        return n;
    }

}
