package fr.sebastien.criquet.model.launcher;

import fr.sebastien.criquet.thread.ProgressbarThread;
import fr.sebastien.criquet.view.ConfigApplication;
import fr.sebastien.criquet.view.MainApplication;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import java.io.File;
import java.util.Collections;
import java.util.UUID;

public class Launcher {

    private final String URL = "http://67.205.179.165/edenia/";

    private static final GameVersion GAME_VERSION = new GameVersion("1.12", GameType.V1_8_HIGHER);
    private static final GameInfos GAME_INFOS = new GameInfos("edeniacraft", GAME_VERSION, new GameTweak[]{ GameTweak.FORGE });
    public static final File DIR = GAME_INFOS.getGameDir();

    private AuthInfos authInfos;
    private Thread update;

    private MainApplication main;

    public static int SPINNER_VALUE = 1024;

    public Launcher(MainApplication main) {
        this.main = main;
    }

    public void auth(String username) {
        authInfos = new AuthInfos(username, "", UUID.randomUUID().toString());
    }

    public void update() throws Exception {
        SUpdate sUpdate = new SUpdate(URL, DIR);
        sUpdate.getServerRequester().setRewriteEnabled(true);
        sUpdate.addApplication(new FileDeleter());

        update = new ProgressbarThread(main.getProgress(), main.getIndicator());
        update.start();

        sUpdate.start();
    }

    public void launch() throws LaunchException {
        ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(GAME_INFOS, GameFolder.BASIC, authInfos);


        System.out.println(ConfigApplication.getArgs().toString());

        Collections.addAll(profile.getVmArgs(), ConfigApplication.getArgs());


        ExternalLauncher launcher = new ExternalLauncher(profile);
        Process process = launcher.launch();

        try{
            Thread.sleep(5000L);
            process.waitFor();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.exit(0);
    }

    public File getDir() {
        return DIR;
    }
}
