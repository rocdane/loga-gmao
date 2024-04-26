package tech.loga.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@FeignClient("gateway-server")
public interface ReportService {

    @GetMapping(value = "/report-service/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    void report(@RequestBody Object data, @PathVariable String file, HttpServletResponse response);

    @GetMapping(value = "/report-service/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    void report(@RequestBody Collection<Object> data, @PathVariable String file, HttpServletResponse response);
}
