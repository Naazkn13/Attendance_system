package utility;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BDUtility {
    
    // Method to set background image
    public static void setImage(JFrame frame, String imagePath, int newWidth, int newHeight) {
        try {
            // Load the image from resources
            BufferedImage originalImage = ImageIO.read(BDUtility.class.getResource(imagePath));
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.createGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIcon backgroundImage = new ImageIcon(resizedImage);
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, newWidth, newHeight);
           frame.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
            frame.validate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Map to hold forms
    private static HashMap<String, JFrame> formsMap = new HashMap<>();
    
    // Method to open form or bring it to front if already opened
    public static void openForm(String formName, JFrame formInstance) {
        JFrame existingForm = formsMap.get(formName);
        
        if (existingForm == null || !existingForm.isVisible()) {
            // If the form doesn't exist or is not visible, add it to map and show it
            formsMap.put(formName, formInstance);
            formInstance.setVisible(true);
        } else {
            // If form is already open, bring it to front
            existingForm.toFront();
            existingForm.repaint();
        }
    }
    
    // Utility method to get the full path of a resource (useful for loading images, files)
    public static String getPath(String finalPath) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "\\src\\" + finalPath;
    }
    
    // Method to get file extension (e.g., jpg, png)
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }
    
    // Method to scale image
    public static BufferedImage scaleImage(BufferedImage originalImage, BufferedImage selectedImage) {
        int width = selectedImage.getWidth();
        int height = selectedImage.getHeight();
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        scaledImage.createGraphics().drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return scaledImage;
    }
}
