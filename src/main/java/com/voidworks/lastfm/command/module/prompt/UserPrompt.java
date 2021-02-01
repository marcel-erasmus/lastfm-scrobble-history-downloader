package com.voidworks.lastfm.command.module.prompt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Scanner;

@NoArgsConstructor
@Getter
@Setter
public class UserPrompt extends AbstractPrompt<String> {
    @Override
    public void execute() {
        printMessage("Please enter your Last.fm username:");
        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        return getData() != null;
    }
}
