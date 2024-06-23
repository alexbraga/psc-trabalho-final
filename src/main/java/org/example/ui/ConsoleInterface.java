package org.example.ui;

import org.example.controller.DonationController;
import org.example.entity.ClothesDonation;
import org.example.entity.Donation;
import org.example.entity.FoodDonation;
import org.example.entity.MoneyDonation;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner SCANNER;
    private final DonationController DONATION_CONTROLLER;

    public ConsoleInterface(Scanner scanner, DonationController donationController) {
        this.SCANNER = scanner;
        this.DONATION_CONTROLLER = donationController;
    }

    public void mainMenu() {
        int option;
        do {
            System.out.println("O que você gostaria de fazer:\n");
            System.out.println("\t1. Adicionar uma nova doação");
            System.out.println("\t2. Listar todas as doações");
            System.out.println("\t3. Buscar uma doação pelo ID");
            System.out.println("\t4. Editar uma doação");
            System.out.println("\t5. Deletar uma doação");
            System.out.println("\t6. Sair");
            System.out.print("\nPor favor, digite a opção desejada [1-6]: ");

            option = Integer.parseInt(SCANNER.nextLine());

            switch (option) {
                case 1:
                    addDonation();
                    break;
                case 2:
                    getAllDonations();
                    break;
                case 3:
                    getDonationById();
                    break;
                case 4:
                    updateDonation();
                    break;
                case 5:
                    deleteDonation();
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        } while (option != 6);
    }

    public void addDonation() {
        try {
            System.out.println("\nQue tipo de doação será realizada?");
            System.out.println("\t1. Alimento");
            System.out.println("\t2. Roupa");
            System.out.println("\t3. Dinheiro");
            System.out.print("\nInsira a opção desejada [1-3]: ");
            int donationType = Integer.parseInt(SCANNER.nextLine());

            switch (donationType) {
                case 1:
                    FoodDonation foodDonation = getFoodDonationInfo(null);

                    if (foodDonation != null) {
                        DONATION_CONTROLLER.addDonation(foodDonation);
                        System.out.println("Alimento doado com sucesso!\n");
                    }
                    break;
                case 2:
                    System.out.println("Quantas peças de roupa serão doadas?");
                    int quantity = Integer.parseInt(SCANNER.nextLine());
                    System.out.println();

                    for (int i = 1; i <= quantity; i++) {
                        System.out.println("PEÇA #" + i);
                        ClothesDonation clothesDonation = getClothesDonationInfo(null);

                        DONATION_CONTROLLER.addDonation(clothesDonation);
                        System.out.println("Roupa doada com sucesso!\n");
                    }

                    break;
                case 3:
                    MoneyDonation moneyDonation = getMoneyDonationInfo(null);

                    if (moneyDonation != null) {
                        DONATION_CONTROLLER.addDonation(moneyDonation);
                        System.out.println("Dinheiro doado com sucesso!\n");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar doação: " + e.getMessage());
        }
    }

    private FoodDonation getFoodDonationInfo(FoodDonation donation) {
        double amount;
        do {
            System.out.println("Quantos quilos/litros estão sendo doados? [somente números]");
            amount = Double.parseDouble(SCANNER.nextLine());

            if (amount > 0) {
                if (donation == null) {
                    return new FoodDonation(amount);
                } else {
                    donation.setAmount(amount);
                    return donation;
                }
            } else {
                System.out.println("A quantidade deve ser um número positivo. Por favor, tente novamente.\n");
            }
        } while (amount <= 0);

        return null;
    }

    private ClothesDonation getClothesDonationInfo(ClothesDonation donation) {
        System.out.println("Insira uma descrição curta do tipo de roupa:");
        String description = SCANNER.nextLine();

        System.out.println("Qual o tamanho da peça? [infantil/P/M/G...]");
        String size = SCANNER.nextLine();

        if (donation == null) {
            return new ClothesDonation(description, size);
        } else {
            donation.setDescription(description);
            donation.setSize(size);
            return donation;
        }
    }

    private MoneyDonation getMoneyDonationInfo(MoneyDonation donation) {
        double amount;
        do {
            System.out.println("Insira o valor da doação:");
            amount = Double.parseDouble(SCANNER.nextLine());

            if (amount > 0) {
                if (donation == null) {
                    return new MoneyDonation(amount);
                } else {
                    donation.setAmount(amount);
                    return donation;
                }
            } else {
                System.out.println("O valor da doação deve ser maior que R$ 0,00. Por favor, tente novamente.\n");
            }
        } while (amount <= 0);

        return null;
    }

    public void getAllDonations() {
        List<Donation> donations = DONATION_CONTROLLER.getAllDonations();
        Map<String, Double> totals = DONATION_CONTROLLER.getTotalDonationsByType();

        if (donations.isEmpty()) {
            System.out.println("Nenhuma doação foi encontrada.\n");
        } else {
            System.out.println();

            donations.forEach(donation -> System.out.println(donation));

            System.out.println();
            System.out.println("TOTAL DE DOAÇÕES: " + totals.size() + "\n");

            for (Map.Entry<String, Double> entry : totals.entrySet()) {
                switch (entry.getKey()) {
                    case "Alimentos" -> System.out.println(entry.getKey() + ": " + entry.getValue() + " Kg");
                    case "Roupas" -> System.out.println(entry.getKey() + ": " + entry.getValue() + " Und.");
                    case "Dinheiro" -> System.out.printf("%s: R$ %.2f%n", entry.getKey(), entry.getValue());
                }
            }

            System.out.println();
        }
    }

    public void getDonationById() {
        try {
            System.out.println("Insira o ID da doação:");
            Long id = Long.parseLong(SCANNER.nextLine());
            Donation donation = DONATION_CONTROLLER.getDonationById(id);

            if (donation != null) {
                System.out.println("\nResultado da busca:");
                System.out.println(donation);
                System.out.println();
            } else {
                System.out.println("\nNenhuma doação foi encontrada com o ID fornecido.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.\n");
        }
    }

    public void updateDonation() {
        try {
            System.out.println("Insira o ID da doação que você deseja atualizar:");
            Long id = Long.parseLong(SCANNER.nextLine());
            Donation donation = DONATION_CONTROLLER.getDonationById(id);

            if (donation != null) {
                System.out.println("\n" + donation);
                System.out.println("Deseja prosseguir? [S/n]");
                String answer = SCANNER.nextLine();

                if (answer.equalsIgnoreCase("s")) {
                    if (donation instanceof FoodDonation) {
                        donation = getFoodDonationInfo((FoodDonation) donation);
                    } else if (donation instanceof ClothesDonation) {
                        donation = getClothesDonationInfo((ClothesDonation) donation);
                    } else if (donation instanceof MoneyDonation) {
                        donation = getMoneyDonationInfo((MoneyDonation) donation);
                    }

                    if (donation != null) {
                        DONATION_CONTROLLER.updateDonation(donation);
                        System.out.println("Doação atualizada com sucesso.\n");
                    }
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println();
                    mainMenu();
                } else {
                    System.out.println("Opção inválida. Por favor, tente novamente.\n");
                }
            } else {
                System.out.println("Nenhuma doação foi encontrada com o ID fornecido.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao atualizar doação: " + e.getMessage());
        }
    }

    public void deleteDonation() {
        try {
            System.out.println("Insira o ID da doação que você deseja deletar:");
            Long id = Long.parseLong(SCANNER.nextLine());
            DONATION_CONTROLLER.deleteDonation(id);

            System.out.println("Doação deletada com sucesso.\n");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao deletar doação: " + e.getMessage());
        }
    }
}
