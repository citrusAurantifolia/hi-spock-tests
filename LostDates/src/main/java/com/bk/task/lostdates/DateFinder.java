package com.bk.task.lostdates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * What do we know about Professor Boolean's past? It's mostly rumor and
 * conjecture, but a few things are known to be true.
 * 
 * Mad Professor Boolean wasn't always a super villain. Early in his career, he
 * was an average paper pusher, working in an office with some very backwards
 * technology. One of his primary jobs was to carry date cards between
 * departments. One morning, he tripped over a unicycle and dropped his date
 * cards on the floor. He hit his head � and hit upon the idea of breeding an
 * army of zombie rabbits to do his bidding and manage simple tasks. But that
 * comes later. Before he could quit with an explosive YouTube video, the
 * professor had to get his cards back in order.
 * 
 * Aha! It seems he recorded the details of this life-changing event in his
 * diary. Let's try to reproduce his methods:
 * 
 * The goal is to get the date cards back in order. Each set of date cards
 * consists of 3 cards, each with a number written on it. When arranged in some
 * order, the numbers make up the representation of a date, in the form
 * month/day/year. However, sometimes multiple representations will be possible.
 * For example, if the date cards read 1, 1, 99 it could only mean 01/01/99, but
 * if the date cards read 2, 30, 3, it could mean any one of 02/03/30, 03/02/30,
 * or 03/30/02.
 * 
 * Write a function called answer(x, y, z) that takes as input the 3 numbers on
 * the date cards. You may assume that at least one valid representation of a
 * date can be constructed from the cards.
 * 
 * If there is only one valid representation, the function should return it as a
 * string, in the form MM/DD/YY. If there are multiple valid representations,
 * the function should return the string �Ambiguous.� Each of x, y, z will be
 * between 1 to 99 inclusive. You may also assume that there are no leap years.
 * 
 * 
 * Test cases ==========
 * 
 * Inputs: (int) x = 19 (int) y = 19 (int) z = 3 Output: (string) �03/19/19�
 * 
 * Inputs: (int) x = 2 (int) y = 30 (int) z = 3 Output: (string) �Ambiguous�
 * 
 * @author citrus_aurantifolia
 *
 */
public class DateFinder {

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    public static final String DELIMITER = "/";

    public String answer(int x, int y, int z) {
        Set<String> dates = new HashSet<String>();

        dates.add(generateDate(x, y, z));
        dates.add(generateDate(x, z, y));
        dates.add(generateDate(y, x, z));
        dates.add(generateDate(y, z, x));
        dates.add(generateDate(z, x, y));
        dates.add(generateDate(z, y, x));

        return validateDates(dates);
    }
    
    private String generateDate(int firstNumber, int secondNumber, int thirdNumber){

        return new StringBuilder()
                    .append(firstNumber)
                    .append(DELIMITER)
                    .append(secondNumber)
                    .append(DELIMITER)
                    .append(thirdNumber).toString();
    }

    private String validateDates(Set<String> dates) {
        int counter = 0;
        String validDate = "";
        for (String date : dates) {
            if (isDateValid(date)) {
                counter++;
                validDate = date;
                if (counter > 1) {
                    return "Ambiguous";
                }
            }
        }
        return validDate;
    }

    private boolean isDateValid(String dateToValidate) {
        if (dateToValidate == null) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}