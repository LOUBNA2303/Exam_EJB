package com.example.exam_ejb;

import com.example.exam_ejb.beanStateless.CdService;
import com.example.exam_ejb.beanStateless.CdServiceRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class RmiServer {
    public static void main(String[] args) {
        try {
            // Créer une instance du service CD
            CdService cdService = new CdService();

            // Créer le registre RMI (utilisez un port différent si nécessaire)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Enregistrer le service avec un nom
            Naming.rebind("rmi://localhost:1099/CdService", cdService);

            System.out.println("Service RMI CdService est en cours d'exécution...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

