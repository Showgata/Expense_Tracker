package com.kanuma.useyourtime.Utils;

import com.kanuma.useyourtime.DB.Job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GroupUtils {


    public static HashMap<String, List<Job>> groupDataIntoHashMap(List<Job> jobs) {

        HashMap<String, List<Job>> groupedHashMap = new HashMap<>();

        for(Job job : jobs) {

            String hashMapKey = formatDate(job.getCreatedDate());

            if(groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(job);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<Job> list = new ArrayList<>();
                list.add(job);
                groupedHashMap.put(hashMapKey, list);
            }
        }

        return groupedHashMap;
    }


    public static List<ListItem> GroupToArray(HashMap<String, List<Job>> groupedHashMap){
        // We linearly add every item into the consolidatedList.
        List<ListItem> consolidatedList = new ArrayList<>();
        Integer sum =0;

        for (String date : groupedHashMap.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);

            for (Job job : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem(job);
                sum += job.getExpenseAmount();
                consolidatedList.add(generalItem);
            }

            if (sum != 0){
                ExpenseItem expenseItem = new ExpenseItem();
                expenseItem.setTotalExpense(sum);
                consolidatedList.add(expenseItem);
                sum=0;
            }
        }

        return consolidatedList;
    }


    public static Set<String> listOfFormattedDates(List<Date> dates){
        Set<String> linkedHashSet = new LinkedHashSet<>();

        for(Date date : dates) {
            linkedHashSet.add(formatDate(date));
        }
        return linkedHashSet;
    }

    private static String formatDate(Date date){

        String pattern = "dd-MMM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }


}
