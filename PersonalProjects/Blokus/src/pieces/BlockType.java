

package pieces;

/**
 *
 * @author Administrator
 */
public enum BlockType {
    NULLED("src/res/stage.png"),
    STAGE("src/res/stage.png"),
    BLANK("src/res/blank.png"),
    RED_BLOCK("src/res/redBlock.png"),
    BLUE_BLOCK("src/res/blueBlock.png"),
    YELLOW_BLOCK("src/res/yellowBlock.png"),
    GREEN_BLOCK("src/res/greenBlock.png"),
    GRID_BLOCK("src/res/gridBlock.png"),
    ACCEPT("src/res/accept.png"),
    FLIP_HORIZONTALLY("src/res/horizontalFlip.png"),
    FLIP_VERTICALLY("src/res/verticalFlip.png"),
    UNDO("src/res/undo.png");
    
    public final String location;
    BlockType(String location){
        this.location=location;
    }
}
