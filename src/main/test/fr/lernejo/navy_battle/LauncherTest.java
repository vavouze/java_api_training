package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;


class LauncherTest {

    @Test
    public void LaunchGame(){
        Launcher.main(new String[]{"1234"});
        Launcher.main(new String[]{"5678","http://localhost:1234"});
    }

}
