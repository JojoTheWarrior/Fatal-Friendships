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
  /** Character speaking. */
  private String characterName;
  /** Emoticon of the person speaking. */
  private BufferedImage characterIcon;
  /** Background image. */
  private BufferedImage backgroundImage;
  /** Dialogue text. */
  private String dialogueText;
  /** List of choices. */
  private ArrayList<String> choices;
  /** List of where each choice leads to. */
  private ArrayList<Integer> lead;
  
  /**
   * Note: When passing in the String for the path, just enter the name of the image - the file will automatically be searched for in ./assets/img/ and the .png extension will be added.
   * 
   * @param name Character speaking.
   * @param dialogue Dialogue text.
   * @param c List of choices.
   * @param l List of where each choice leads to.
   * @param characterIconPath Emoticon of the person speaking.
   * @param backgroundImagePath Background image.
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
   * @param name Character speaking.
   * @param dialogue Dialogue text.
   * @param c List of choices.
   * @param l Where choosing any option leads to.
   * @param characterIconPath Emoticon of the person speaking.
   * @param backgroundImagePath Background image.
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
   * @return Character's name.
   */
  public String getCharacterName(){
    return characterName;
  }
  
  /**
   * @return Character's icon.
   */
  public BufferedImage getCharacterIcon(){
    return characterIcon;
  }
  
  /**
   * @return Background image.
   */
  public BufferedImage getBackgroundImage(){
    return backgroundImage;
  }
  
  /**
   * @return Dialogue text.
   */
  public String getDialogueText(){
    return dialogueText;
  }
  
  /**
   * @return List of choices.
   */
  public ArrayList<String> getChoices(){
    return choices;
  }

  /**
   * @return Where each choice leads to.
   */
  public ArrayList<Integer> getLead(){
    return lead;
  }
}
  