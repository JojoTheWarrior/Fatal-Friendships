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
 * The Dialogue class is used in Level 1, Level 2, and Level 3.
 */
public class Dialogue {
  private String characterName;
  private BufferedImage characterIcon;
  private BufferedImage backgroundImage;
  private String dialogueText;
  private ArrayList<String> choices;
  private ArrayList<Integer> lead;
  
  /**
   * Note: When passing in the String for the path, just enter the name of the image - the file will automatically be searched for in ./assets/img/
   */
  public Dialogue(String name, String dialogue, String[] c, int[] l, String characterIconPath, String backgroundImagePath){
    characterName = name;
    dialogueText = dialogue;
    try {
      characterIcon = ImageIO.read(new File("./assets/img/" + characterIconPath + ".png"));
      backgroundImage = ImageIO.read(new File("./assets/img/" + backgroundImagePath + ".png"));
    } catch (Exception e){

    }
    
    choices = new ArrayList<String>();
    for (String s : c){
      choices.add(s);
    }

    lead = new ArrayList<Integer>();
    for (int i : l){
      lead.add(i);
    }
  }

  /**
   * Overloaded method for when there is only one lead option.
   */
  public Dialogue(String name, String dialogue, String[] c, int l, String characterIconPath, String backgroundImagePath){
    characterName = name;
    dialogueText = dialogue;
    try {
      characterIcon = ImageIO.read(new File("./assets/img/" + characterIconPath + ".png"));
      backgroundImage = ImageIO.read(new File("./assets/img/" + backgroundImagePath + ".png"));
    } catch (Exception e){

    }
    
    choices = new ArrayList<String>();
    for (String s : c){
      choices.add(s);
    }

    lead = new ArrayList<Integer>();
    lead.add(l);
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

  public ArrayList<Integer> getLead(){
    return lead;
  }
}
  