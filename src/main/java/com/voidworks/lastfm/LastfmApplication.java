package com.voidworks.lastfm;

import com.voidworks.lastfm.command.AbstractPersistCommand;
import com.voidworks.lastfm.command.factory.PersistCommandFactory;
import com.voidworks.lastfm.command.invoker.ChainedCommandInvoker;
import com.voidworks.lastfm.prompt.DirectoryPrompt;
import com.voidworks.lastfm.prompt.DownloadOptionPrompt;
import com.voidworks.lastfm.prompt.PageSizePrompt;
import com.voidworks.lastfm.prompt.UserPrompt;
import com.voidworks.lastfm.prompt.enumerator.DownloadOptionType;
import com.voidworks.lastfm.prompt.invoker.PromptInvoker;

import java.util.Arrays;

public class LastfmApplication {

    public static void main(String[] args) {
        PromptInvoker promptInvoker = new PromptInvoker();

        UserPrompt userPrompt = new UserPrompt();
        promptInvoker.execute(userPrompt);
        String user = userPrompt.getData();

        DirectoryPrompt directoryPrompt = new DirectoryPrompt();
        promptInvoker.execute(directoryPrompt);
        String directory = directoryPrompt.getData();

        DownloadOptionPrompt downloadOptionPrompt = new DownloadOptionPrompt();
        promptInvoker.execute(downloadOptionPrompt);
        DownloadOptionType downloadOption = DownloadOptionType.of(Integer.parseInt(downloadOptionPrompt.getData()));

        if (downloadOption == null) {
            return;
        }

        int pageSize = 1000;
        if (Arrays.asList(
                DownloadOptionType.SANITISED_CSV_PERSIST,
                DownloadOptionType.SANITISED_JSON_PERSIST,
                DownloadOptionType.VERBATIM_PERSIST)
                .contains(downloadOption)) {
            PageSizePrompt pageSizePrompt = new PageSizePrompt();
            promptInvoker.execute(pageSizePrompt);
            pageSize = Integer.parseInt(pageSizePrompt.getData());
        }

        AbstractPersistCommand persistCommand = PersistCommandFactory.getPersistCommand(
                downloadOption,
                directory,
                user,
                1,
                pageSize);

        if (persistCommand != null) {
            ChainedCommandInvoker persistCommandInvoker = new ChainedCommandInvoker();
            persistCommandInvoker.execute(persistCommand);
        }
    }

}
