package refuelTheCar.dbModule.interfacees;

import java.util.List;

import refuelTheCar.exception.GetDataByHttpException;

public interface ExternalRespositoryInterface<T> {
    List<T> findAll() throws GetDataByHttpException;

}
