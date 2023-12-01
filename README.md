# My Personal Project

## A Cookbook (A Recipe Hub)

The web application is a cookbook which will contain recipes allowing users 
to view the recipes or to add their own recipes. This application conveniently 
organizes the recipes in different cuisines and different cooking methods. 
Also, the application will allow users to enter the ingredients they wish 
to cook with, and they will be matched  with recipe(s) that requires the 
least amount of additional ingredients. Moreover, the application will 
allow users to determine the popularity of a recipe through ratings 
which will be provided with each. 

There are two main categories of users for this application:

**1. Those looking for a new recipe**

**2. Those wanting to share a recipe**

The application will particularly appeal to those wanting to try out new recipes with their commonly used ingredients but also appeals
to those just looking for a recipe or cooking enthusiasts wanting to share their 
recipes with others. This application is of particular interest to me, as a student,
who is living independently away from home and looking for ways to creatively 
utilize the ingredients and to break the food monotony. 


## User Stories

- *As a user, I want to be able to add a recipe to the cookbook with the name, 
 category, preparation time, list of ingredients and 
step-by-step instructions*
- *As a user, I want to be able to view the list of recipes in the cookbook*
- *As a user, I want to be able to delete a recipe that I have added to the cookbook*
- *As a user, I want to be able to get a list of recipes that I can make with a given list of ingredients*
- *As a user, I want to be able to mark a recipe as saved for later*
- *As a user, I want to be able to mark a recipe as saved for later*
- *As a user, I want to be able to filter recipes by difficulty*
- *As a user, I want to be able to save the recipe list to a file*
- *As a user, I want to be able to load the recipe list from a file*

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by pressing the "Add recipe" button 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by pressing the "Show Easy Recipes" button
- You can locate my visual component by pressing the "Add recipe" button
- You can save the state of my application by pressing the "Save Recipe" button 
- You can reload the state of my application by pressing the "Load Recipe" button 

## Phase 4: Task 2

- Fri Dec 01 01:24:56 PST 2023
- Added a new recipe to the list of recipes
- Fri Dec 01 01:25:04 PST 2023
- Added a new recipe to the list of recipes
- Fri Dec 01 01:25:05 PST 2023
- Found easy recipes from the list of recipes
- Fri Dec 01 01:25:14 PST 2023
- Added a new recipe to the list of recipes
- Fri Dec 01 01:25:15 PST 2023
- Found easy recipes from the list of recipes
- Fri Dec 01 01:25:23 PST 2023
- Added a new recipe to the list of recipes
- Fri Dec 01 01:25:28 PST 2023
- Found easy recipes from the list of recipes


## Phase 4: Task 3

RecipeList should have been designed using the Singleton design pattern. 
To do so, I would implement the getInstance method. This will allow the creation of
only one object of the class. This is essential because RecipeList serves as the local
application data store, storing all data. Therefore, we aim to have only one object responsible
for storing all the data to prevent any potential data discrepancies.

Pertaining to the graphical user interface of the application, I 
would create separate classes for each part of the interface, that
is the two different panels. Instead of making objects directly from
the default class, I would extend the JFrame class for each panel class.
This will allow me to keep all the code for a specific part organized in 
its own class. This will allow for improved cohesion.
