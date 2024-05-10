package tech.loga.app.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface ReportService {

    @GetMapping(value = "/report-service/{src}/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void produceReportById(HttpServletResponse response, @PathVariable("src") String src, @PathVariable("id") Long id) ;
}