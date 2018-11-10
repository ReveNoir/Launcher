package fr.sebastien.criquet.model.launcher.gametweak;

import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;

public class CustomGameTweak {

    public static final GameTweak FORGE = new GameTweak() {
        @Override
        public String getName() {
            return "FML Tweaker";
        }

        @Override
        public String getTweakClass(GameInfos infos) {
            return "net.minecraftforge.fml.common.launcher.FMLTweaker";
        }
    };

}
