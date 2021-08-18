package com.springshell.eshop.commands;

import com.springshell.eshop.util.hibernate.HibernateUtil;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class CustomExitCommand implements Quit.Command {

    @ShellMethod(value = "Exit the shell.", key = {"quit", "exit", "terminate"})
    public void quit() {
        HibernateUtil.getSessionFactory().close();
        System.exit(0);

    }
}
