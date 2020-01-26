package docker_management;


public enum EditorStyles {
    AMBIANCE (1), CHAOS (2), CHROME (3),
    CLOUDS (4), CLOUDS_MIDNIGHT (5), COBALT (6),
    CRIMSON_EDITOR(7), DAWN (8), DRACULA(9),
    DREAMWEAVER(10), ECLIPSE(11), GITHUB(12),
    GOB(13), GRUVBOX(14), IDLE_FINGER(15),
    IPLASTIC(16), KATZENMILCH(17), KR_THEME(18),
    KUROIR (19), MERBIVORE(20), MERBIVORE_SOFT(21),
    MONO_INDUSTRIAL(22),MONOKAI(23), PASTEL_ON_DARK(24),
    SOLARIZED_DARK(25), SOLARIZED_LIGHT(26), SQLSERVER(27),
    TERMINAL(28), TEXTMATE(29), TOMORROW(30), TOMORROW_NIGHT(31),
    TOMORROW_NIGHT_BLUE(32), TOMORROW_NIGHT_BRIGHT(33), TOMORROW_NIGHT_EIGHTIES(34),
    TWILIGHT(35), VIBRANT_INK(36), XCODE(37);


    EditorStyles(int enumIndex) {
        this.enumIndex = enumIndex;
    }

    public int enumIndex;

    public int getEnumIndex(){
        return enumIndex;
    }
}