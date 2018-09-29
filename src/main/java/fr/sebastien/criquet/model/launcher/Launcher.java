package fr.sebastien.criquet.model.launcher;

import fr.sebastien.criquet.thread.SupdateThread;
import fr.sebastien.criquet.view.MainApplication;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;

import java.io.File;
import java.util.UUID;

public class Launcher {

    private final String URL = "http://localhost/index.php/";

    private final GameVersion GAME_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    private final GameInfos GAME_INFOS = new GameInfos("Edenia", GAME_VERSION, new GameTweak[]{ GameTweak.FORGE });
    private final File DIR = GAME_INFOS.getGameDir();

    private AuthInfos authInfos;
    private Thread update;

    private MainApplication main;

    public Launcher(MainApplication main) {
        this.main = main;
    }

    public void auth(String username) {
        authInfos = new AuthInfos(username, "", UUID.randomUUID().toString());
    }

    public void update() throws Exception {
        SUpdate sUpdate = new SUpdate(URL, DIR);
        sUpdate.addApplication(new FileDeleter());

        update = new SupdateThread(main.getProgress(), main.getIndicator());
        update.start();

        sUpdate.start();
        update.interrupt();
    }

    public void launch() throws LaunchException {
        ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(GAME_INFOS, GameFolder.BASIC, authInfos);
        //profile.getVmArgs().addAll(Arrays.asList());
        ExternalLauncher launcher = new ExternalLauncher(profile);
        Process process = launcher.launch();

        System.exit(1);
    }

    public void interruptThread(){
        update.interrupt();
    }

    public File getDir() {
        return DIR;
    }

}
