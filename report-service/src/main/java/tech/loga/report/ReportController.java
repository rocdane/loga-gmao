package tech.loga.report;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/report-service")
public class ReportController {

    private final ReportManagement REPORT_SERVICE;
    private final SimpleDateFormat DATE_FORMATTER;
    private final String SERVICE = "report-service";

    @Autowired
    public ReportController(ReportManagement REPORT_SERVICE) {
        this.REPORT_SERVICE = REPORT_SERVICE;
        this.DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String reportFallBack(){
        return "Report service unreached";
    }

    @Retry(name = SERVICE, fallbackMethod = "reportFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "reportFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "reportFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "reportFallback")
    @GetMapping(value = "/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@RequestBody Object data,
                       @PathVariable String file,
                       HttpServletResponse response) throws ReportErrorException {

        String path = "report_"+file+"_"+this.DATE_FORMATTER.format(new Date(System.currentTimeMillis()));
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "inline; filename="+path+".pdf");

        try {
            REPORT_SERVICE.report(file,data,response.getOutputStream());
        } catch (IOException e) {
            throw new ReportErrorException(e.getMessage());
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "reportFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "reportFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "reportFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "reportFallback")
    @GetMapping(value = "/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@RequestBody Collection<Object> data,
                       @PathVariable String file,
                       HttpServletResponse response) throws ReportErrorException {

        String path = "report_"+file+"_"+this.DATE_FORMATTER.format(new Date(System.currentTimeMillis()));
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "inline; filename="+path+".pdf");

        try {
            REPORT_SERVICE.report(file,data,response.getOutputStream());
        } catch (IOException e) {
            throw new ReportErrorException(e.getMessage());
        }
    }
}
