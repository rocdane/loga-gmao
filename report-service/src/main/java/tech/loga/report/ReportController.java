package tech.loga.report;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/report-service")
public class ReportController {

    private final ReportManagement REPORT_SERVICE;
    private final StorageManagement STORAGE_MANAGEMENT;
    private final SimpleDateFormat DATE_FORMATTER;

    @Autowired
    public ReportController(ReportManagement REPORT_SERVICE,
                            StorageManagement STORAGE_MANAGEMENT) {
        this.REPORT_SERVICE = REPORT_SERVICE;
        this.STORAGE_MANAGEMENT = STORAGE_MANAGEMENT;
        this.DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @GetMapping(value = "/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@RequestBody Object data,
                       @PathVariable String file,
                       HttpServletResponse response) throws ReportErrorException {

        String path = "report_"+file+"_"+this.DATE_FORMATTER.format(new Date(System.currentTimeMillis()));
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "inline; filename="+path+".pdf");

        try {
            OutputStream outputStream = response.getOutputStream();
            REPORT_SERVICE.report(file,data,outputStream);
        } catch (IOException e) {
            throw new ReportErrorException(e.getMessage());
        }
    }

    @GetMapping(value = "/report/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@RequestBody Collection<Object> data,
                       @PathVariable String file,
                       HttpServletResponse response) throws ReportErrorException {

        String path = "report_"+file+"_"+this.DATE_FORMATTER.format(new Date(System.currentTimeMillis()));
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "inline; filename="+path+".pdf");

        try {
            OutputStream outputStream = response.getOutputStream();
            REPORT_SERVICE.report(file,data,outputStream);
        } catch (IOException e) {
            throw new ReportErrorException(e.getMessage());
        }
    }

    @GetMapping("/downloadFile/{file}")
    public ResponseEntity<Resource> download(@PathVariable String file, HttpServletRequest request) {

        Resource resource = STORAGE_MANAGEMENT.loadFileAsResource(file);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/uploadFile")
    public Response upload(@RequestParam("file") MultipartFile file) {
        String fileName = STORAGE_MANAGEMENT.storeFile(file);

        String fileDownloadUri =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path(fileName)
                        .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadFiles")
    public List< Response > upload(@RequestParam("files") MultipartFile[] files) {
        return Arrays
                .stream(files)
                .map(this::upload)
                .collect(Collectors.toList());
    }
}
