package com.wsrgen.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wsrgen.model.JiraIssue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JIRAApi {
    private static Map<String, JiraIssue> epicCache = new HashMap<>();


    private static String jiraURL = "https://agile-jira.ciena.com/rest/api/2/issue/";
    public static String jiraServerCookie = "";

    public static JiraIssue getEpic(String epicNumber) throws Exception {
        if(epicCache.containsKey(epicNumber)){
            return epicCache.get(epicNumber);
        }else{
            JiraIssue epic = getIssue(epicNumber,true);
            epicCache.put(epicNumber,epic);
            return epic;
        }
    }

    public static void printEpicCache(){
        System.out.println("-------- Epic Cache ------- ");
        epicCache.entrySet().forEach( k -> {
            System.out.println("Key = "+k.getKey());
            System.out.println("Value = "+k.getValue());
        });
    }

    public static JiraIssue getIssue(String issueNumber, boolean isEpic) throws Exception{
        URL url = new URL(jiraURL+issueNumber);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("cookie",jiraServerCookie);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        String output = br.lines().collect(Collectors.joining("\n"));

        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();
        JsonObject fields = jsonObject.getAsJsonObject("fields");

        String summary = fields.get("summary").toString();
        String epic = fields.get("customfield_11370").toString();
        String issueType = fields.getAsJsonObject("issuetype").get("name").toString();
        String status = fields.getAsJsonObject("status").get("name").toString();
        String priority = fields.getAsJsonObject("priority").get("name").toString();

        JiraIssue issue = new JiraIssue();
        issue.setId(issueNumber);
        issue.setName(nq(summary));
        issue.setIssueType(nq(issueType));
        issue.setEpicId(nq(epic));
        issue.setStatus(nq(status));
        if(!isEpic){
            issue.setEpicName(nq(getEpic(issue.getEpicId()).getName()));
        }
        issue.setPriority(nq(priority));

        conn.disconnect();
        return issue;
    }

    public static String nq(String s){
        return s.replace("\"","");
    }

    public static void mainExp(String[] args) throws Exception{
        URL url = new URL(jiraURL+"BPINV-8281");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("cookie","_ga=GA1.2.1813892083.1605149583; _mkto_trk=id:847-FEI-694&token:_mch-ciena.com-1605149583778-18911; _gcl_au=1.1.1784444607.1632295855; at_check=true; AMCVS_B0F11CFE53308D250A490D45%40AdobeOrg=1; s_cc=true; s_sq=%5B%5BB%5D%5D; AMCV_B0F11CFE53308D250A490D45%40AdobeOrg=-1124106680%7CMCIDTS%7C18893%7CMCMID%7C24513013995008212880969688202104907394%7CMCAAMLH-1632986678%7C12%7CMCAAMB-1632986678%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1632389078s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C5.2.0; mbox=PC#f10ed3ed0d91472ba4c4a9900ac3da28.31_0#1695626675|session#5798680b5c0b43d388470d81f1d87eff#1632383743; wp43849=\"XWBXCDDDDDDBTVXMIKH-AMAB-XCWI-ICUX-TLVTUZIAVJZLDBMXUKLKY-IJXL-XLUH-HVTV-MKZXCUKHABMYDJpLgH_Jht\"; _uetvid=064120501b7711ec888809a566c8f6f7; JSESSIONID=F5AD78D2D4AE92B585DE3906FC573975; atlassian.xsrf.token=BPCB-NFQL-N1E2-TI7V_af6007a8d864e4ee0c629238e7550171f068b564_lin");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));



        String output = br.lines().collect(Collectors.joining("\n"));

//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//        }

        JsonObject jsonObject = new JsonParser().parse(output).getAsJsonObject();

//        System.out.println("Output = \n"+output);

        JsonObject fields = jsonObject.getAsJsonObject("fields");

        String summary = fields.get("summary").toString();
        String epic = fields.get("customfield_11370").toString();
        String issueType = fields.getAsJsonObject("issuetype").get("name").toString();
        String status = fields.getAsJsonObject("status").get("name").toString();
        String priority = fields.getAsJsonObject("priority").get("name").toString();

        System.out.println("summary = "+summary);
        System.out.println("epic = "+epic);
        System.out.println("issueType = "+issueType);
        System.out.println("status = "+status);

        conn.disconnect();
    }

    public static void main(String[] args) throws Exception{
        JIRAApi.jiraServerCookie = "_ga=GA1.2.1813892083.1605149583; _mkto_trk=id:847-FEI-694&token:_mch-ciena.com-1605149583778-18911; _gcl_au=1.1.1784444607.1632295855; at_check=true; AMCVS_B0F11CFE53308D250A490D45%40AdobeOrg=1; s_cc=true; s_sq=%5B%5BB%5D%5D; AMCV_B0F11CFE53308D250A490D45%40AdobeOrg=-1124106680%7CMCIDTS%7C18893%7CMCMID%7C24513013995008212880969688202104907394%7CMCAAMLH-1632986678%7C12%7CMCAAMB-1632986678%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1632389078s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C5.2.0; mbox=PC#f10ed3ed0d91472ba4c4a9900ac3da28.31_0#1695626675|session#5798680b5c0b43d388470d81f1d87eff#1632383743; wp43849=\"XWBXCDDDDDDBTVXMIKH-AMAB-XCWI-ICUX-TLVTUZIAVJZLDBMXUKLKY-IJXL-XLUH-HVTV-MKZXCUKHABMYDJpLgH_Jht\"; _uetvid=064120501b7711ec888809a566c8f6f7; JSESSIONID=F5AD78D2D4AE92B585DE3906FC573975; atlassian.xsrf.token=BPCB-NFQL-N1E2-TI7V_af6007a8d864e4ee0c629238e7550171f068b564_lin";

        String[] issues = {"BPINV-9381","BPINV-8281","BPINV-9177","BPINV-9017","BPINV-9204","BPINV-8904","BPINV-9338"};

        for(String i: issues){
            JiraIssue j = JIRAApi.getIssue(i,false);
            System.out.println(j);
        }

        JIRAApi.printEpicCache();
    }
}
