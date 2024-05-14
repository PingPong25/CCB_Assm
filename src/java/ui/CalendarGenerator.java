/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.time.LocalDate;

public class CalendarGenerator {
    public static String generateCalendarHTML(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();
        int firstDayOfWeek = date.getDayOfWeek().getValue();
        
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr><th colspan='7'>" + date.getMonth() + " " + year + "</th></tr>");
        htmlBuilder.append("<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>");
        
        htmlBuilder.append("<tr>");
        int day = 1;
        // Fill the first row with empty cells before the first day
        for (int i = 1; i < firstDayOfWeek; i++) {
            htmlBuilder.append("<td></td>");
        }
        // Fill the rest of the row with the days of the month
        for (int i = firstDayOfWeek; i <= 7; i++) {
            if (day == LocalDate.now().getDayOfMonth() && month == LocalDate.now().getMonthValue() && year == LocalDate.now().getYear()) {
                htmlBuilder.append("<td class='current-day'>" + day + "</td>");
            } else {
                htmlBuilder.append("<td>" + day + "</td>");
            }
            day++;
        }
        htmlBuilder.append("</tr>");
        
        // Fill the remaining rows
        while (day <= daysInMonth) {
            htmlBuilder.append("<tr>");
            for (int i = 1; i <= 7 && day <= daysInMonth; i++) {
                if (day == LocalDate.now().getDayOfMonth() && month == LocalDate.now().getMonthValue() && year == LocalDate.now().getYear()) {
                    htmlBuilder.append("<td class='current-day'>" + day + "</td>");
                } else {
                    htmlBuilder.append("<td>" + day + "</td>");
                }
                day++;
            }
            htmlBuilder.append("</tr>");
        }
        
        htmlBuilder.append("</table>");
        
        return htmlBuilder.toString();
    }
}


