package com.voidworks.lastfm;

import com.voidworks.lastfm.command.AbstractPersistCommand;
import com.voidworks.lastfm.command.SanitisedCsvPersistCommand;
import com.voidworks.lastfm.command.SanitisedJsonPersistCommand;
import com.voidworks.lastfm.command.invoker.ChainedCommandInvoker;
import com.voidworks.lastfm.prompt.DirectoryPrompt;
import com.voidworks.lastfm.prompt.DownloadOptionPrompt;
import com.voidworks.lastfm.prompt.PageSizePrompt;
import com.voidworks.lastfm.prompt.UserPrompt;
import com.voidworks.lastfm.prompt.invoker.PromptInvoker;
import com.voidworks.lastfm.command.VerbatimPersistCommand;

public class LastfmApplication {

    public static void main(String[] args) throws Exception {
        PromptInvoker promptInvoker = new PromptInvoker();

        UserPrompt userPrompt = new UserPrompt();
        promptInvoker.execute(userPrompt);
        String user = userPrompt.getData();

        DirectoryPrompt directoryPrompt = new DirectoryPrompt();
        promptInvoker.execute(directoryPrompt);
        String directory = directoryPrompt.getData();

        PageSizePrompt pageSizePrompt = new PageSizePrompt();
        promptInvoker.execute(pageSizePrompt);
        int pageSize = Integer.parseInt(pageSizePrompt.getData());

        DownloadOptionPrompt downloadOptionPrompt = new DownloadOptionPrompt();
        promptInvoker.execute(downloadOptionPrompt);
        int downloadOption = Integer.parseInt(downloadOptionPrompt.getData());

        ChainedCommandInvoker persistCommandInvoker = new ChainedCommandInvoker();

        AbstractPersistCommand persistCommand;
        if (downloadOption == 1) {
            persistCommand = new VerbatimPersistCommand(directory, user, 1, pageSize);
        } else if (downloadOption == 2) {
            persistCommand = new SanitisedJsonPersistCommand(directory, user, 1, pageSize);
        } else {
            persistCommand = new SanitisedCsvPersistCommand(directory, user, 1, pageSize);
        }
        persistCommandInvoker.execute(persistCommand);
    }

}
