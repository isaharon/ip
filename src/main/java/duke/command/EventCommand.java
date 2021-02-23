package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

public class EventCommand extends Command {
    public EventCommand(String commandArgs) {
        super(CommandType.EVENT, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] eventArgs = commandArgs.split("\\s+/at\\s+", 2);
        Task task = new Event(eventArgs[0], eventArgs[1]);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.printAddedTask(task);
        ui.printTotalTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
