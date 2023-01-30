package course2.courseWork.daily_planner.backend.exception;

public class IncorrectArgumentException  extends Exception {
    private final String argument;

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return "Параметр '" + argument + "' некорректный.";
    }
}
