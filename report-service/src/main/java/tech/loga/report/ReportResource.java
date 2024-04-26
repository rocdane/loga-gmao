package tech.loga.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportResource implements ReportManagement {

    public void report(String report, Object data, OutputStream outputStream){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("data",data);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }catch (JRException e){
            throw new ReportErrorException(e.getMessage());
        }
    }

    public void report(String report, Collection<Object> data, OutputStream outputStream){
        JRBeanCollectionDataSource items = new JRBeanCollectionDataSource(data);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("items",items);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }catch (JRException e){
            throw new ReportErrorException(e.getMessage());
        }
    }
}
