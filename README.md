# Cosc211-FinalProject-18.35
## Group Members
Mohammad Arjamand Ali
<br>
Hamza Malkawi
<br>
## Project Topic
#### Exercise 18.35 (H-Tree Fractal)
An H-tree (introduced at the beginning of this chapter in Figure 18.1) is a fractal defined as follows:
1. Begin with a letter H. The three lines of H are of the same length, as shown in Figure 18.1a.
2. The letter H(in its sans-serif form, H) has four endpoints. Drawan H centered at each of the four endpoints to an H-tree of order 1, as shown in Figure 18.1b. These Hs are half the size of the H that contains the four endpoints.
3. Repeat Step 2 to create an H-tree of order 2, 3, . . . , and so on, as shown in Figures 18.1c and d.
Write a program that draws an H-tree, as shown in Figure 18.1.

## Quick Links to Files
[Main Program File / H-Tree](Exercise-18.35/src/application/Main.java)
<br>
[H-Branch](Exercise-18.35/src/application/HBranch.java)
<br>
[Time Complexity Explanation of createHTree() method](Exercise-18.35/Time_Complexity_of_H-Tree.pdf)

## Extra Note on the Project
Type in the value of order you want in the box and press ENTER. Only inputs of 0 to 6 are allowed. Any order of 7 or above always ends up crashing the program (because of too much space usage).
## Running JavaFX Programs
The following code needs to be added to the Run configuration under the VM Arguments:
<br>
<code>--module-path "\path\to\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml</code>
<br>
And uncheck the box that says "Use the -XstartOnFirstThread argument when launching with SWT".

## Some Notes on Testing
There is no point in using JUnit here as this is a JavaFX project done mostly with GUI, and assertions dont really work with them. TestFX was suggested, but that involved way too many complications (its for Gradle & Maven projects, dependencies needed to be added .etc). Going with TextFX would have been a real waste of time as this project has just a few simple inputs and a few straightforward outputs. 
<br>
So the best way is to just test it manually by running the program and checking the output. Some of the tests worded out are:
- Enter all values from 0 to 6 to see if the correct H-Tree is formed.
- Enter a lower number than before to make sure that H-Trees, when printed out, dont overlap each other(for e.g first type 4, then 2).
- Enter an incorrect value like a number less than 0 or more than 6, or any non integer (like doubles or strings), to seen if error message shows up. Then enter a correct value to see if it disappears.

## Resources that were helpful
[Time complexity of for-loops in recursive methods](https://cs.stackexchange.com/questions/87713/recursion-call-inside-a-for-loop-time)


