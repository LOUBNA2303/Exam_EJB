package com.example.exam_ejb.beanStateless;

import com.example.exam_ejb.CD;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CdServiceRMI extends Remote {
    List<CD> listAvailableCds() throws RemoteException;
    List<CD> listBorrowedCds() throws RemoteException;
    void borrowCd(int cdId) throws RemoteException;
    void returnCd(int cdId) throws RemoteException;
}