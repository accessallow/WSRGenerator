package com.wsrgen.utils;

import com.wsrgen.model.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonGenerator {
    public static List<JiraIssue> getTicketsFromFile(String fileName) throws Exception{
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        List<String> tickets = new BufferedReader(new InputStreamReader(is))
                .lines().map(l -> l.trim()).collect(Collectors.toList());
        List<JiraIssue> issues = new ArrayList<>();
        for(String t : tickets){
            issues.add(JIRAApi.getIssue(t,false));
        }
        return issues;
    }
    public static void main(String[] args) throws Exception {
        JIRAApi.jiraServerCookie = "_ga=GA1.2.1813892083.1605149583; _mkto_trk=id:847-FEI-694&token:_mch-ciena.com-1605149583778-18911; _gcl_au=1.1.1784444607.1632295855; at_check=true; AMCVS_B0F11CFE53308D250A490D45%40AdobeOrg=1; s_cc=true; s_sq=%5B%5BB%5D%5D; AMCV_B0F11CFE53308D250A490D45%40AdobeOrg=-1124106680%7CMCIDTS%7C18893%7CMCMID%7C24513013995008212880969688202104907394%7CMCAAMLH-1632986678%7C12%7CMCAAMB-1632986678%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1632389078s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C5.2.0; mbox=PC#f10ed3ed0d91472ba4c4a9900ac3da28.31_0#1695626675|session#5798680b5c0b43d388470d81f1d87eff#1632383743; wp43849=\"XWBXCDDDDDDBTVXMIKH-AMAB-XCWI-ICUX-TLVTUZIAVJZLDBMXUKLKY-IJXL-XLUH-HVTV-MKZXCUKHABMYDJpLgH_Jht\"; _uetvid=064120501b7711ec888809a566c8f6f7; JSESSIONID=F5AD78D2D4AE92B585DE3906FC573975; atlassian.xsrf.token=BPCB-NFQL-N1E2-TI7V_af6007a8d864e4ee0c629238e7550171f068b564_lin";

//        List<JiraIssue> dones = getTicketsFromFile("jira_done.txt");
//        DevUtils.printList("Done",dones);
//        DevUtils.printList("Values",generateValues(dones));

        List<JiraIssue> inProgress = getTicketsFromFile("jira_in_progress.txt");
        DevUtils.printList("In Progress",inProgress);

        BPIDevelopment da = generateDevActivities(inProgress,"Delayed","25-Oct");
        System.out.println("Dev Activity \n"+da);
        DevUtils.printList("Dev Epics",da.getEpics());

        JIRAApi.printEpicCache();

    }

    public static String getLink(String e){
        //https://agile-jira.ciena.com/browse/BPINV-9499
        return "<a href=\"https://agile-jira.ciena.com/browse/"+e+"\" target=\"_blank\">"+e+"</a>";
    }

    public static List<Value> generateValues(List<JiraIssue> doneIssues){
        //collect tags
        Set<String> epics = new HashSet<>();
        doneIssues.forEach(i -> epics.add(i.getEpicId()));
        List<Value> values = new ArrayList<>();
        epics.forEach(e -> {
            try{
                Value v = new Value("");
                v.setTag(JIRAApi.getEpic(e).getName()+" (Epic - "+getLink(e)+")");
                doneIssues.stream().filter(i -> i.getEpicId().equalsIgnoreCase(e)).forEach(i -> {
                    String ticketLine = getLink(i.getId())+" - "+i.getName();
                    v.getPoints().add(ticketLine);
                });
                values.add(v);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });
        return values;
    }

    public static BPIDevelopment generateDevActivities(List<JiraIssue> inProgressIssues, String defaultTracker, String defaultEta){
        BPIDevelopment devActivity = new BPIDevelopment("BPI Development",defaultTracker,defaultEta);

        //collect tags
        Set<String> epicIds = new HashSet<>();
        inProgressIssues.forEach(i -> epicIds.add(i.getEpicId()));
        List<Epic> epics = new ArrayList<>();
        epicIds.forEach(e -> {
            try{
                Epic v = new Epic("");
                v.setTag(JIRAApi.getEpic(e).getName()+" (Epic - "+getLink(e)+")");
                v.setTrack(defaultTracker);
                v.setEta(defaultEta);

                inProgressIssues.stream().filter(i -> i.getEpicId().equalsIgnoreCase(e)).forEach(i -> {
                    String ticketLine = getLink(i.getId())+" - "+i.getName();
                    Issue issue = new Issue(ticketLine,defaultTracker,defaultEta);
                    v.getIssues().add(issue);
                });
                epics.add(v);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        devActivity.setEpics(epics);

        return devActivity;
    }
}
