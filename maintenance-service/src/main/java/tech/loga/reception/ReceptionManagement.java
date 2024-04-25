package tech.loga.reception;


import java.util.List;

public interface IReceptionService {

    Reception create(Reception reception);

    Reception read(Long id);

    List<Reception> readAll();
}
