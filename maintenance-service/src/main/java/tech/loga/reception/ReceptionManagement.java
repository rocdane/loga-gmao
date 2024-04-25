package tech.loga.reception;


import java.util.List;

public interface ReceptionManagement {
    Reception createReception(Reception reception);
    Reception getReceptionById(Long id);
    Reception getReceptionByReference(String reference);
    List<Reception> getAllReception();
}
