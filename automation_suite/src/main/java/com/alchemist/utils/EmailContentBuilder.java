package com.alchemist.utils;

import org.testng.ITestContext;

public class EmailContentBuilder {

    public static String buildHtmlSummary(ITestContext context) {

        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        String statusColor = failed > 0 ? "red" : "green";
        String statusText  = failed > 0 ? "FAILED" : "PASSED";

        return "<html>"
             + "<body style='font-family: Arial;'>"
             + "<h2 style='color:" + statusColor + ";'>Automation Execution " + statusText + "</h2>"
             + "<table border='1' cellpadding='8' cellspacing='0'>"
             + "<tr><th align='left'>Suite Name</th><td>" + context.getName() + "</td></tr>"
             + "<tr><th align='left'>Passed</th><td style='color:green;'>" + passed + "</td></tr>"
             + "<tr><th align='left'>Failed</th><td style='color:red;'>" + failed + "</td></tr>"
             + "<tr><th align='left'>Skipped</th><td style='color:orange;'>" + skipped + "</td></tr>"
             + "</table>"
             + "<br/>"
             + "<p>Please find attached:</p>"
             + "<ul>"
             + "<li>Extent HTML Report</li>"
             + "<li>Execution Logs</li>"
             + "<li>Failure Screenshots</li>"
             + "</ul>"
             + "<br/>"
             + "<p><b>Automation Framework</b><br/>Selenium + TestNG + Jenkins</p>"
             + "</body>"
             + "</html>";
    }
}
