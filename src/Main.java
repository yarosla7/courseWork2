import course2.courseWork.daily_planner.backend.Planner;

public class Main {
    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            label:
//            while (true) {
//                printMenu();
//                System.out.print("Выберите пункт меню: ");
//                if (scanner.hasNextInt()) {
//                    int menu = scanner.nextInt();
//                    switch (menu) {
//                        case 1:
//                            inputTask(scanner);
//                            break;
//                        case 2:
//                            // todo: обрабатываем пункт меню 2
//                            break;
//                        case 3:
//                            // todo: обрабатываем пункт меню 3
//                            break;
//                        case 0:
//                            break label;
//                    }
//                } else {
//                    scanner.next();
//                    System.out.println("Выберите пункт меню из списка!");
//                }
//            }
//        }
//    }
//
//    private static void inputTask(Scanner scanner) {
//        System.out.print("Введите ID задачи: ");
//        String taskName = scanner.next();
//        // todo
//    }
//    private static void choosePersonalOrElse(Scanner scanner) {
//        System.out.print("Выберете тип задачи: ");
//        String taskName = scanner.next();
//        // todo
//    }
//    private static void printMenu() {
//        System.out.println(
//                "1. Добавить задачу \n2. Удалить задачу \n3. Получить задачу на указанный день \n0. Выход"
//
//        );
        Planner planner = new Planner("task", "s", "Каждый месяц.");
        Planner planner1 = new Planner("task", "s", Planner.TypesOfRepetitions.EVERY_YEAR);
        Planner planner2 = new Planner("task", "s", Planner.TypesOfRepetitions.ONE_TIME);
        Planner planner3 = new Planner("task", "s", Planner.TypesOfRepetitions.EVERYDAY);
        Planner planner4 = new Planner("task", "s", Planner.TypesOfRepetitions.EVERY_WEEK);
        System.out.println(Planner.listOfTasks);


//        System.out.println("PersonalTasks.listOfTasks = " + PersonalTasks.listOfTasks);
    }
}
