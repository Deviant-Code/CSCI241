import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

/*
  Jesse Ericksen - W01173602
  CSCI 241 Hearne, Winter 2018

  The purpose of this lab is to create a basic text editor using java and 
  swing package. Functions will be cut, copy, paste, open, save.

*/
public class Lab6 extends JFrame implements ActionListener{

   private JButton cutButton;
   private JButton copyButton;
   private JButton pasteButton;
   private JScrollPane scroller;
   private JTextArea text;
   private JButton openButton;
   private JButton saveButton;
  
   //Constructor for TextEditor
   public Lab6(){
      super("CSCI 241 Lab6");
      setSize(500, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      initialize();
      setVisible(true); 
   }
   
   //Intializes our textEditor adding buttons to top bar.
   private void initialize(){
      JPanel toolbar = new JPanel();
      add(toolbar, BorderLayout.NORTH);
      addButton(toolbar, cutButton, "Cut");
      addButton(toolbar, copyButton, "Copy");
      addButton(toolbar, pasteButton, "Paste");
      addButton(toolbar, openButton, "Open");
      addButton(toolbar, saveButton, "save");
      text = new JTextArea();
      scroller = new JScrollPane(text);
      add(scroller, BorderLayout.CENTER);
   
   }
   
   //Adds a new button and creates listener
   private void addButton(JPanel panel, JButton button, String label){
      button = new JButton(label);
      panel.add(button);
      button.addActionListener(this);
   
   }
   
   //checks if button is pressed and launches feature
   public void actionPerformed(ActionEvent e){
      System.out.println(e.getActionCommand() + " pressed");
      if(e.getActionCommand().equals("Open")){
         readFile();
      } else if(e.getActionCommand() == "Cut"){
         text.cut();
      
      } else if(e.getActionCommand() == "Copy"){
         text.copy();
      
      } else if(e.getActionCommand() == "Paste"){
         text.paste();
      
      } else if(e.getActionCommand().equals("Save")){
         writeFile();
      }
   }
   
   //Asks user for existing file to import
   private void readFile(){
      JFileChooser chooser = new JFileChooser();
      
      int option = chooser.showOpenDialog(this);
      if(option == JFileChooser.APPROVE_OPTION){
         try{
            String filename = chooser.getName(chooser.getSelectedFile());
            text.setText(new String(Files.readAllBytes(Paths.get(filename))));
         }
         catch(IOException e){
            System.out.println("Cannot read the file" + e);
         }
      
      }
   
   }
   
   //allows user to save file to custom path
   private void writeFile(){
      JFileChooser chooser = new JFileChooser();
      int option = chooser.showSaveDialog(this);
      
      if(option == JFileChooser.APPROVE_OPTION) {
         try{
            String filename = chooser.getName(chooser.getSelectedFile());
            Files.write(Paths.get(filename), text.getText().getBytes());
         } catch(IOException e){
            System.out.println("Cannot write to file " + e);
         }
      }
      
   }
   
   public static void main(String[] args){
      Lab6 textEd = new Lab6();   
   }
   
   





}