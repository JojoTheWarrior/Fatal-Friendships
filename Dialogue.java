import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

/**
 * This is the general class for dialogue. It has five attributes, which represent the name of the character speaking, the 
 * image used for the character's icon (since there may be different emoticons), the background image, the dialogue text, and the choices (if any) that the user can pick from. 
 * The Dialogue class is used in Level 1, Level 3 and Level 4.
 */
public class Dialogue {
  private String characterName;
  private BufferedImage characterIcon;
  private BufferedImage backgroundImage;
  private String dialogueText;
  private ArrayList<String> choices;
  
  /**
   * Note: When passing in the String for the path, just enter the name of the image - the file will automatically be searched for in ./assets/img/
   */
  public Dialogue(String name, String dialogue, String[] c, String characterIconPath, String backgroundImagePath){
    characterName = name;
    dialogueText = dialogue;
    try {
      characterIcon = ImageIO.read(new File(characterIconPath));
      backgroundImage = ImageIO.read(new File(backgroundImagePath));
    } catch (Exception e){

    }
    
    choices = new ArrayList<String>();
    for (String s : c){
      choices.add(s);
    }
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
  
  public ArrayList<String> getChoices(){
    return choices;
  }
}
