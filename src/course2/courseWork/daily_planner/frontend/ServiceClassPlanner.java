package course2.courseWork.daily_planner.frontend;

import java.time.LocalDate;

//класс для создания и управления задачами
public class ServiceClassPlanner {

//    public static void addTaskByIDPersonal(int id, Map<Integer, Planner> map, Map<Integer, PersonalTasks> p) {
//        for (Integer key : map.keySet()) {
//            if (map.containsKey(id)) {
//                for (Map.Entry<Integer, Planner> copy : map.entrySet()) {
//                    p.put(copy.getKey(), (PersonalTasks) copy.getValue());
//                }
//            } else {
//                System.out.println("Задача по ID не найдена.");
//            }
//        }
//    }
//    public void addTaskByIDWork(int id) {
//        for (Integer key : w.keySet()) {
//            if (w.containsKey(id)) {
//                for (Map.Entry<Integer, Planner> copy : w.entrySet()) {
//                    plannerMap2.put(copy.getKey(),copy.getValue());
//                }
//            } else {
//                System.out.println("Задача по ID не найдена.");
//            }
//        }
//    }


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
//
//    private static void choosePersonalOrElse(Scanner scanner) {
//        System.out.print("Выберете тип задачи: ");
//        String taskName = scanner.next();
//        // todo
//    }
//
//    private static void printMenu() {
//        System.out.println(
//                "1. Добавить задачу \n2. Удалить задачу \n3. Получить задачу на указанный день \n0. Выход"
//
//        );
    }

    //методы сервиса:

    public void addTaskByName(String name) {

    }


    //getters

    public void getTasksForDate(LocalDate w) {

    }
}