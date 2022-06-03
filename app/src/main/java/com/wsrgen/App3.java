package com.wsrgen;

import com.wsrgen.model.*;
import com.wsrgen.utils.DevUtils;
import com.wsrgen.utils.JIRAApi;
import com.wsrgen.utils.JsonGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App3
{
    public static void main( String[] args ) throws Exception {

        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(App3.class, "/");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        BPIDetails bpiDetails = new BPIDetails();
        bpiDetails.setStatus("green");
        bpiDetails.setTrack("BPI");

        //Risk
        Risk r1 = new Risk("BPI Development");
        r1.getPoints().add("21.10 Epic devlopment");
        r1.getPoints().add("Project Support Issues");
        r1.getPoints().add("DevOps Stories");

        bpiDetails.getRisks().add(r1);

        //Plans
        Plan p1 = new Plan("BPI Development");
        p1.getPoints().add("22.02 Epic devlopment");
        p1.getPoints().add("22.10 Bug fixes");
        p1.getPoints().add("Project Support Issues");
        p1.getPoints().add("DevOps Stories");

        Plan p2 = new Plan("BPI Testing");
        p2.getPoints().add("Automation of Fusion Core");
        p2.getPoints().add("21.10 Testing/Automation");


        bpiDetails.getPlans().add(p1);
        bpiDetails.getPlans().add(p2);


        bpiDetails.getTestingActivities().setEta("12th-Nov");


        JIRAApi.jiraServerCookie = "_ga=GA1.2.1813892083.1605149583; _mkto_trk=id:847-FEI-694&token:_mch-ciena.com-1605149583778-18911; _gcl_au=1.1.1784444607.1632295855; at_check=true; AMCVS_B0F11CFE53308D250A490D45%40AdobeOrg=1; s_cc=true; s_sq=%5B%5BB%5D%5D; AMCV_B0F11CFE53308D250A490D45%40AdobeOrg=-1124106680%7CMCIDTS%7C18893%7CMCMID%7C24513013995008212880969688202104907394%7CMCAAMLH-1632986678%7C12%7CMCAAMB-1632986678%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1632389078s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C5.2.0; mbox=PC#f10ed3ed0d91472ba4c4a9900ac3da28.31_0#1695626675|session#5798680b5c0b43d388470d81f1d87eff#1632383743; wp43849=\"XWBXCDDDDDDBTVXMIKH-AMAB-XCWI-ICUX-TLVTUZIAVJZLDBMXUKLKY-IJXL-XLUH-HVTV-MKZXCUKHABMYDJpLgH_Jht\"; _uetvid=064120501b7711ec888809a566c8f6f7; seraph.rememberme.cookie=168779%3Ac513acc1596e41e336a997b4bb18777702373aca; JSESSIONID=85D4AB51074972D29A8CB8B2BEB86180; atlassian.xsrf.token=BPCB-NFQL-N1E2-TI7V_cdabf088799b42d1120da5315ade043aae827fb0_lin";
        List<JiraIssue> dones = JsonGenerator.getTicketsFromFile("jira_done.txt");
        DevUtils.printList("Done",dones);
        List<Value> genValues = JsonGenerator.generateValues(dones);

        List<JiraIssue> inProgress = JsonGenerator.getTicketsFromFile("jira_in_progress.txt");
        DevUtils.printList("In Progress",inProgress);
        BPIDevelopment da = JsonGenerator.generateDevActivities(inProgress,"On-Track","12th-Nov");
        System.out.println("Dev Activity \n"+da);
        DevUtils.printList("Dev Epics",da.getEpics());

        bpiDetails.setValues(genValues);
        bpiDetails.setDevActivities(da);

        // 2.2. Get the template

        Template template = cfg.getTemplate("wsr.ftl");

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(bpiDetails, consoleWriter);

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(bpiDetails, fileWriter);
        } finally {
            fileWriter.close();
        }
    }
}
