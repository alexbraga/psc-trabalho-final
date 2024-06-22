package org.example;

import org.example.controller.DonationController;
import org.example.dao.DonationDAO;
import org.example.dao.DonationDAOImpl;
import org.example.service.DonationService;
import org.example.ui.ConsoleInterface;
import org.example.util.EntityManagerFactoryProvider;
import org.example.util.MariaDBEntityManagerFactoryProvider;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactoryProvider emf = new MariaDBEntityManagerFactoryProvider("mariadbPU");
        DonationDAO dao = new DonationDAOImpl(emf);
        DonationService service = new DonationService(dao);
        DonationController controller = new DonationController(service);
        ConsoleInterface ui = new ConsoleInterface(scanner, controller);

        ui.mainMenu();
        emf.close();
        scanner.close();
    }
}