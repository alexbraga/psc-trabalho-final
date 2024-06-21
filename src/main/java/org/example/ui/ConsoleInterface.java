package org.example.ui;

import org.example.controller.DonationController;
import org.example.entity.ClothesDonation;
import org.example.entity.Donation;
import org.example.entity.FoodDonation;
import org.example.entity.MoneyDonation;

import java.util.List;
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
                    System.out.println("Opção inválida. Por favor, tente novamente");
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
                    ClothesDonation clothesDonation = getClothesDonationInfo(null);
                    if (clothesDonation != null) {
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
                    System.out.println("Opção inválida. Por favor, tente novamente");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar doação: " + e.getMessage());
        }
    }

    private FoodDonation getFoodDonationInfo(FoodDonation donation) {
        System.out.println("Quantos quilos/litros estão sendo doados? [somente números]");
        double amount = Double.parseDouble(SCANNER.nextLine());

        if (amount <= 0) {
            System.out.println("A quantidade deve ser um número positivo.");
            return null;
        }

        if (donation == null) {
            return new FoodDonation(amount);
        } else {
            donation.setAmount(amount);
            return donation;
        }
    }

    private ClothesDonation getClothesDonationInfo(ClothesDonation donation) {
        System.out.println("Insira uma descrição curta do tipo de roupa:");
        String description = SCANNER.nextLine();

        System.out.println("Qual o tamanho da peça? [infantil/P/M/G...]");
        String size = SCANNER.nextLine();

        System.out.println("Quantas unidades estão sendo doadas?");
        double amount = Double.parseDouble(SCANNER.nextLine());

        if (amount <= 0) {
            System.out.println("A quantidade deve ser um número positivo.");
            return null;
        }

        if (donation == null) {
            return new ClothesDonation(description, size, amount);
        } else {
            donation.setDescription(description);
            donation.setSize(size);
            donation.setAmount(amount);
            return donation;
        }
    }

    private MoneyDonation getMoneyDonationInfo(MoneyDonation donation) {
        System.out.println("Insira o valor da doação:");
        double amount = Double.parseDouble(SCANNER.nextLine());

        if (amount <= 0) {
            System.out.println("O valor da doação deve ser um número positivo.\n");
            return null;
        }

        if (donation == null) {
            return new MoneyDonation(amount);
        } else {
            donation.setAmount(amount);
            return donation;
        }
    }

    public void getAllDonations() {
        List<Donation> donations = DONATION_CONTROLLER.getAllDonations();

        if (donations.isEmpty()) {
            System.out.println("Nenhuma doação foi encontrada.\n");
        } else {
            System.out.println();

            donations.forEach(donation -> {
                System.out.println(donation);
            });

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
                    System.out.println("Opção inválida. Por favor, tente novamente\n");
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
