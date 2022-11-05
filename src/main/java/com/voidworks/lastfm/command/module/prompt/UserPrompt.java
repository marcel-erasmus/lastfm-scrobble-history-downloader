package com.voidworks.lastfm.command.module.prompt;

import com.voidworks.lastfm.communication.Communicator;
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
        Communicator.printMessage("Please enter your Last.fm username:");
        setData(new Scanner(System.in).next());
    }

    @Override
    public boolean isValid() {
        return getData() != null;
    }

}
