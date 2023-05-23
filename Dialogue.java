import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
/**
 * This is the general class for dialogue. It has four attributes, which represent the name of the character speaking, the 
 * image used for the character's icon (since there may be different emoticons), the background image, and the dialogue text. 
 * The Dialogue class is used in Level 1, Level 3 and Level 4.
 */
public class Dialogue {
  private String characterName;
  private BufferedImage characterIcon;
  private BufferedImage backgroundImage;
  private String dialogueText;
  
  /**
   * Note: When passing in the String for the path, just enter the name of the image - the file will automatically be searched for in ./assets/img/
   */
  public Dialogue(String name, String characterIconPath, String backgroundImagePath, String dialogue){
    characterName = name;
    dialogueText = dialogue;
    characterIcon = ImageIO.read(new File(characterIconPath));
    backgroundImage = ImageIO.read(new File(backgroundImagePath));
  }
  
  /**
   * Get methods for all the attributes.
   */
  public String getCharacterName(){
    return characterName;
  }
  
  public BufferedImage getCharacterIcon(){
    return characterIcon;
  }
  
  public BufferedImage getBackgroundImage(){
    return backgroundImage;
  }
  
  public String getDialogueText(){
    return dialogueText;
  }
}
