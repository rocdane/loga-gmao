package tech.loga.report;

import java.io.OutputStream;
import java.util.Collection;

public interface ReportManagement {
    void report(String report, Object data, OutputStream outputStream) throws ReportErrorException;
    void report(String report, Collection<Object> data, OutputStream outputStream) throws ReportErrorException;
}
