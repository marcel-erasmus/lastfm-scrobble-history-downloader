package com.voidworks.lastfm;

import com.voidworks.lastfm.command.module.persist.ChainedPersistCommand;
import com.voidworks.lastfm.command.module.persist.bean.PersistCommandBean;
import com.voidworks.lastfm.command.module.persist.factory.PersistCommandFactory;
import com.voidworks.lastfm.command.module.persist.invoker.ChainedPersistCommandInvoker;
import com.voidworks.lastfm.command.module.prompt.DirectoryPrompt;
import com.voidworks.lastfm.command.module.prompt.DownloadOptionPrompt;
import com.voidworks.lastfm.command.module.prompt.PageSizePrompt;
import com.voidworks.lastfm.command.module.prompt.UserPrompt;
import com.voidworks.lastfm.command.module.prompt.enumerator.DownloadOptionType;
import com.voidworks.lastfm.command.module.prompt.invoker.PromptInvoker;

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

        PersistCommandBean persistCommandBean = PersistCommandBean.builder()
                .directory(directory)
                .user(user)
                .page(1)
                .pageSize(pageSize)
                .build();

        ChainedPersistCommand persistCommand = PersistCommandFactory.getPersistCommand(downloadOption, persistCommandBean);

        if (persistCommand != null) {
            ChainedPersistCommandInvoker persistCommandInvoker = new ChainedPersistCommandInvoker();
            persistCommandInvoker.execute(persistCommand);
        }
    }

}
