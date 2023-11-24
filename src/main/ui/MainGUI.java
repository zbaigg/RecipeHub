package ui;

import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MainGUI {
    private JFrame frame;
    private JPanel inputPanel;
    private JScrollPane displayScrollPane;
    private JButton addRecipeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton easyRecipesButton;
    private JLabel addRecipeLabel;
    private CookBookApp app;
    private JLabel recipeNameLabel;
    private JTextArea recipeNameText;
    private JLabel recipeStepsLabel;
    private JTextArea recipeStepsText;
    private JLabel recipeDurationLabel;
    private JTextArea recipeDurationText;
    private JLabel recipeTypeLabel;
    private JTextArea recipeTypeText;
    private JLabel recipeIngredientsLabel;
    private JTextArea recipeIngredientsText;
    private JLabel displayLabel;
    private JTextArea displayTextArea;
    private JLabel imageLabel;

    public MainGUI() {
        frame = new JFrame();
        inputPanel = new JPanel();
        imageLabel = new JLabel();

        addRecipeLabel = new JLabel("Fill the information below to add a recipe:");
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputPanel.setLayout(new GridLayout(0, 1));
        inputPanel.add(addRecipeLabel);
        inputPanel.add(imageLabel);
        app = new CookBookApp(true);
        addRecipeForm();
        addButtonPanel();
        addDisplayPanel();
        addRecipeButtonListener();
        addButtonListeners();
        setFrameProperties();
    }

    private void addRecipeForm() {
        recipeNameLabel = new JLabel("What is the name of the recipe?");
        recipeStepsLabel = new JLabel("Enter the steps for the recipe (separated by comma): ");
        recipeDurationLabel = new JLabel("What is the duration of the recipe?");
        recipeTypeLabel = new JLabel("What is the type of the recipe?");
        recipeIngredientsLabel = new JLabel("Enter the ingredients for the recipe (separated by comma): ");

        recipeNameText = new JTextArea();
        recipeStepsText = new JTextArea();
        recipeDurationText = new JTextArea();
        recipeTypeText = new JTextArea();
        recipeIngredientsText = new JTextArea();
        inputPanel.add(recipeNameLabel);
        inputPanel.add(recipeNameText);
        inputPanel.add(recipeStepsLabel);
        inputPanel.add(recipeStepsText);
        inputPanel.add(recipeDurationLabel);
        inputPanel.add(recipeDurationText);
        inputPanel.add(recipeTypeLabel);
        inputPanel.add(recipeTypeText);
        inputPanel.add(recipeIngredientsLabel);
        inputPanel.add(recipeIngredientsText);

    }
    
    private void addRecipeButtonListener() {
        addRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecipeToApp(
                        recipeNameText.getText(),
                        Arrays.asList(recipeStepsText.getText().split(",")),
                        recipeDurationText.getText(),
                        Arrays.asList(recipeIngredientsText.getText().split(",")),
                        recipeTypeText.getText()
                );

                List<Recipe> recipes = app.getRecipes();
                displayRecipes(recipes);
                clearInputFields();
                ImageIcon icon = createImageIcon("Checkmark.png");
                    imageLabel.setIcon(icon);
                    Timer timer = new Timer(5000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            imageLabel.setIcon(null);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
            }
        });
        
    }

    private void addButtonListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.saveRecipeList();
            }
        });


        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.loadRecipeList();
                List<Recipe> recipes = app.getRecipes();
                displayRecipes(recipes);
            }
        });

        easyRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Recipe> recipes = app.getEasyRecipes();
                displayRecipes(recipes);
            }
        });
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        addRecipeButton = new JButton("Add recipe");
        saveButton = new JButton("Save Recipe");
        loadButton = new JButton("Load Recipes");
        easyRecipesButton = new JButton("Show Easy Recipes");
        buttonPanel.add(addRecipeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(easyRecipesButton);
        inputPanel.add(buttonPanel);
        frame.add(inputPanel, BorderLayout.WEST);

    }

    private void addDisplayPanel() {
        displayLabel = new JLabel("Recipe Information:");
        displayTextArea = new JTextArea();
        displayTextArea.setEditable(false);

        JPanel displayPanel = new JPanel();
        displayScrollPane = new JScrollPane(displayPanel);
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel, BorderLayout.NORTH);
        displayPanel.add(displayTextArea, BorderLayout.CENTER);

        frame.add(displayScrollPane, BorderLayout.EAST);

        displayScrollPane.setPreferredSize(new Dimension(600, displayScrollPane.getHeight()));
    }

    private void setFrameProperties() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Recipe Cookbook App");
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }

    private void displayRecipes(List<Recipe> recipes) {
        displayTextArea.setText("");

        List<Recipe> nonDuplicateRecipes = new ArrayList<Recipe>();
        for (Recipe recipe: recipes) {
            if (!nonDuplicateRecipes.contains(recipe)) {
                nonDuplicateRecipes.add(recipe);
            }
        }

        for (Recipe recipe : nonDuplicateRecipes) {
            displayTextArea.append("Recipe ID: " + recipe.getRecipeId() + "\n");
            displayTextArea.append("Recipe Name: " + recipe.getName() + "\n");
            displayTextArea.append("Directions: " + String.join(", ", recipe.getDirections()) + "\n");
            displayTextArea.append("Time: " + recipe.getTime() + "\n");
            displayTextArea.append("Ingredients: " + String.join(", ", recipe.getIngredients()) + "\n");
            displayTextArea.append("Category: " + recipe.getCategory() + "\n\n");
        }
    }

    private void addRecipeToApp(String name, List<String> dir, String time, List<String> ingList, String category) {

        app.addRecipe(name, dir, time, ingList, category);
    }

    private void clearInputFields() {
        recipeNameText.setText("");
        recipeStepsText.setText("");
        recipeDurationText.setText("");
        recipeTypeText.setText("");
        recipeIngredientsText.setText("");
    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainGUI.class.getResource(path);

        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}
