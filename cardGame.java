package firstJava;

import java.util.Random;
import java.util.Scanner;

public class cardGame {
    private static final String[] choices = {"Plante", "Eaux", "Feux"};
    private static int userScore = 0; // Score de l'utilisateur
    private static int computerScore = 0; // Score de l'IA
    private static int rounds = 3; // Nombre de manches pendant le jeu
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * function compChoice
     * returns String
     */
    public static String compChoice() {
        Random random = new Random();
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }

    /**
     * function determineWinner
     * param userChoice
     * param computerChoice
     * returns String
     */
    public static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "Égalité entre vous deux";
        } else if ((userChoice.equals("Eaux") && computerChoice.equals("Feux"))
                || (userChoice.equals("Plante") && computerChoice.equals("Eaux"))
                || (userChoice.equals("Feux") && computerChoice.equals("Plante"))) {
            userScore++;
            return "Vous avez gagné cette manche";
        } else {
            computerScore++;
            return "Votre adversaire a gagné cette manche";
        }
    }

    /**
     * function gameMenu
     */
    public static void gameMenu() {
        System.out.println(" _______________________________________________");
        System.out.println("|                   CARD GAME                   |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("|                  Menu du Jeu                  |");
        System.out.println("|///////////////////////////////////////////////|");
        System.out.println("|                   1. JOUER                    |");
        System.out.println("|                   2. Guide du jeu             |");
        System.out.println("|                   3. Quitter                  |");
        System.out.println("|///////////////////////////////////////////////|");
        System.out.println("|_______________________________________________|");
    }

    /**
     * function nameOfPlayer
     */
    public static boolean nameOfPlayer() {
        gameMenu();
        System.out.print("Veuillez entrer votre choix : ");
        int user = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        return switch (user) {
            case 1 -> {
                System.out.println("Bienvenue dans Card Game");
                System.out.print("Avant de commencer, veuillez entrer les noms d'utilisateurs que vous préférez : ");
                String askUserName = scanner.nextLine();
                System.out.println("Heureux de vous connaître " + askUserName + ", débutons la partie maintenant!");
                yield true;
            }
            case 2 -> {
                System.out.println("Bienvenue dans le guide du jeu de cartes");
                System.out.println("Vous avez 3 manches dans une partie et vous disposez de 3 cartes différentes entre vos mains,");
                System.out.println("À chaque manche, vous avez le droit de choisir votre carte parmi les trois existantes que ce soit vous ou");
                System.out.println("votre adversaire; votre score s'affichera à chaque fin de manche ainsi que la carte utilisée par vous et ");
                System.out.println("votre adversaire. Si vous avez 2 manches au moins vous avez gagné mais si l'adversaire a gagné au moins 2");
                System.out.println("victoires, vous avez perdu. Si pendant la partie, il y a 1 victoire et 2 nulles, le joueur qui a une victoire gagnera.");
                System.out.println("Quand vous avez choisi votre première carte, votre adversaire a choisi automatiquement aussi.");
                System.out.println("Il suffit donc de bien se concentrer et de mieux choisir votre carte pour détruire votre adversaire.");
                System.out.println("Choisissez bien votre carte afin de battre votre adversaire. Amusez-vous bien!");
                yield nameOfPlayer();
            }
            case 3 -> {
                System.out.println("Merci d'avoir visité notre jeu, à la prochaine!");
                yield false;
            }
            default -> {
                System.out.println("Choix invalide. Veuillez réessayer.");
                yield nameOfPlayer();
            }
        };
    }

    /**
     * function MenuOfTheGame
     */
    public static void MenuOfTheGame() {
        System.out.println("_________________________________________________________________");
        System.out.println("|                         Règle du jeu                           |");
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
        System.out.println("|                  Vous avez 3 cartes disponibles :              |");
        System.out.println("|                         1. Plante                              |");
        System.out.println("|                         2. Eaux                                |");
        System.out.println("|                         3. Feux                                |");
        System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx |");
        System.out.println("|                         4. Quitter le jeu                      |");
        System.out.println("|________________________________________________________________|");
    }

    /**
     * function playRound
     */
    public static boolean playRound() {
        MenuOfTheGame();

        System.out.print("Faites votre choix : ");
        int userChoiceIndex = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        if (userChoiceIndex >= 1 && userChoiceIndex <= 3) {
            String userChoice = choices[userChoiceIndex - 1];
            String computerChoice = compChoice();
            String result = determineWinner(userChoice, computerChoice);

            System.out.println("Vous avez choisi " + userChoice + ", l'adversaire a choisi " + computerChoice + ", " + result);
            System.out.println("Score actuel - Vous: " + userScore + ", Votre adversaire: " + computerScore);
            rounds--;

            if (rounds > 0) {
                return true;
            } else {
                System.out.println("Fin du jeu");
                if (userScore > computerScore) {
                    System.out.println("Vous avez gagné la partie!");
                } else if (computerScore > userScore) {
                    System.out.println("Votre adversaire a gagné la partie.");
                } else {
                    System.out.println("La partie est nulle");
                }
                return false;
            }
        } else if (userChoiceIndex == 4) {
            System.out.println("Partie interrompue.");
            System.out.println("Merci d'avoir joué et à la prochaine!");
            return false;
        } else {
            System.out.println("Choix invalide. Veuillez choisir entre 1 à 3, ou 4 pour quitter");
            return playRound();
        }
    }

    public static void main(String[] args) {
        System.out.println("Vous avez 3 manches dans une partie et vous disposez de 3 cartes différentes entre vos mains,");
        System.out.println("choisissez bien votre carte afin de battre votre adversaire. Amusez-vous bien!");
        if (nameOfPlayer()) {
            while (true) {
                if (rounds <= 0 || !playRound()) break;
                /** Continuer à jouer les rounds jusqu'à ce qu'ils soient épuisés ou que l'utilisateur choisisse de quitter */
            }
        }
    }
}