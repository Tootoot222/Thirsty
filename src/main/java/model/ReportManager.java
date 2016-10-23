package model;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Manager for the Report classes
 */
public class ReportManager {
    private static LinkedList<WaterReport> waterReportList = new LinkedList<>(); 
    private static int reportNumber = 1;

    /**
     * Creates a water report
     * @param latitude GPS latitude coordinate of the report
     * @param longitude GPS longitude coordinate of the report
     * @param type of the report
     * @param condition of the report
     * @param author Author of the report
     * @return the status of the operation
     */
    public static WaterReport createWaterReport(double latitude, double longitude, WaterType type,
                WaterCondition condition, User author) {
        WaterReport r = new WaterReport(reportNumber++, latitude, longitude, type, condition, author);
        if (waterReportList.add(r)) {
            return (r);
        } else {
            return (null);
        }

    }

    /**
     * Creates a water report
     * @param dateTime of the report
     * @param latitude GPS latitude coordinate of the report
     * @param longitude GPS longitude coordinate of the report
     * @param type of the report
     * @param condition of the report
     * @param author Author of the report
     * @return The WaterReport added, or null if the report already exists
     */
    public static WaterReport createWaterReport(LocalDateTime dateTime, double latitude, double longitude,
                WaterType type, WaterCondition condition, User author) {
        WaterReport r = new WaterReport(reportNumber++, dateTime, latitude, longitude, type, condition, author);
        if (waterReportList.add(r)) {
            return (r);
        } else {
            return (null);
        }
    }

    /**
     * Deletes specified report from the list - all instances
     * @param waterReport to be deleted
     */
    public static void deleteReport(WaterReport waterReport) {
        ListIterator<WaterReport> iter = waterReportList.listIterator(0);
        while (iter.hasNext()) {
            if (iter.next().equals(waterReport)) {
                iter.remove();
            }
        }
    }

    /**
     * Returns the list of water reports in order they were created
     * @return water report list
     */
    public static List<WaterReport> getWaterReportlist() {
        List<WaterReport> list = new ArrayList<>(waterReportList.size());
        list.addAll(waterReportList);
        return list;
    }

    /**
     * Sorts the list of water reports
     * @return sorts water report list by data (natural ordering)
     */
    public static List<WaterReport> sortWaterReportByDate() {
        List<WaterReport> list = new ArrayList<>(waterReportList.size());
        list.addAll(waterReportList);
        Collections.sort(list);
        return list;
    }

    /**
     * Filters the report list by the author
     * @param user that authored reports
     * @return list containing only reports authored by the user
     */
    public static List<WaterReport> filterWaterReportByUser(User user) {
        List<WaterReport> list = new ArrayList<>(waterReportList.size());
        for (WaterReport report: waterReportList) {
            if (user.equals(report.getAuthor())) {
                list.add(report);
            }
        }
        return list;
    }

    /**
     * Filters the report list by the report number
     * @param num the number of the report
     * @return either the report, or null if not found
     */
    public static WaterReport filterWaterReportByNumber(int num) {
        if (num > 0 && num < reportNumber) {
            for (WaterReport report: waterReportList) {
                if (report.getReportNum() == num) {
                    return (report);
                }
            }
        }
        return null;
    }

    /**
     * Sorts the water reports by alphabetical order of the name of the author
     * @return the sorted report list
     */
    public static List<WaterReport> sortWaterReportByName() {
        List<WaterReport> list = new ArrayList<>(waterReportList.size());
        list.addAll(waterReportList);
        Collections.sort(list, (WaterReport a, WaterReport b) ->
            a.getAuthor().getName().compareTo(b.getAuthor().getName())
        );
        return list;
    }
}
