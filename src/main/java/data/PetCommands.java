package data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class PetCommands {
    /**
     * список команд животного
     */
    List<Command> commands;

    public PetCommands() {
        this.commands = new ArrayList<>();
    }

    /**
     * добавить команду
     *
     * @param command - команда для добавления
     */
    public void addCommand(Command command) {
        for (Command comm :
                commands) {
            if (comm.equals(command)) {
                return;
            }
        }
        commands.add(command);
    }

    /**
     * добавить список команд(команды которые уже есть у животного пропускаются)
     *
     * @param command - список команд
     */
    public void addCommandList(List<Command> command) {
        for (Command current :
                command) {
            boolean commandIsFound = false;
            for (Command comm :
                    commands) {
                if (current.equals(comm)) {
                    commandIsFound = true;
                }
            }
            if (!commandIsFound) {
                commands.add(current);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Command command :
                commands) {
            result.append(command);
        }
        return result.toString();
    }
}
