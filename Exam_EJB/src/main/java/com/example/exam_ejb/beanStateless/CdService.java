package com.example.exam_ejb.beanStateless;

import com.example.exam_ejb.CD;
import jakarta.ejb.Stateless;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class CdService extends UnicastRemoteObject implements CdServiceRMI {
    private List<CD> cdList = new ArrayList<>();

    protected void CdServiceImpl() throws RemoteException {

        // Ajouter quelques CDs par défaut
        cdList.add(new CD(1, "CD1", "Artiste1", true));
        cdList.add(new CD(2, "CD2", "Artiste2", true));
    }

    public CdService() throws RemoteException {
    }

    @Override
    public List<CD> listAvailableCds() throws RemoteException {
        List<CD> availableCds = new ArrayList<>();
        for (CD cd : cdList) {
            if (cd.isAvailable()) {
                availableCds.add(cd);
            }
        }
        return availableCds;
    }

    @Override
    public List<CD> listBorrowedCds() throws RemoteException {
        List<CD> borrowedCds = new ArrayList<>();
        for (CD cd : cdList) {
            if (!cd.isAvailable()) {
                borrowedCds.add(cd);
            }
        }
        return borrowedCds;
    }

    @Override
    public void borrowCd(int cdId) throws RemoteException {
        for (CD cd : cdList) {
            if (cd.getId() == cdId && cd.isAvailable()) {
                cd.setAvailable(false);
                break;
            }
        }
    }

    @Override
    public void returnCd(int cdId) throws RemoteException {
        for (CD cd : cdList) {
            if (cd.getId() == cdId && !cd.isAvailable()) {
                cd.setAvailable(true);
                break;
            }
        }
    }
}
