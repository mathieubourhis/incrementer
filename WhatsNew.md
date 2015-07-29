# WhatsComingNext #

---

  * Grouping the resources by Produced resource (with some nice CSS)
  * Hiding everything except what's buildable and what resource is produced (hide or change style, IDK)
  * Adding onHover on images to resize them (up), and onClick to resize them (back to normal), and them back up  (cause we're still onHover) but do the operation over time to allow the user to feel how the button is organic. :p
  * The TODOs in code.
  * Parsing this file in a web component to see the update notes.

# WhatsNew #

---

## Chapter 6. ##

---

| 0.063 | I need to make sure I can load an obsolete save file (older version). |
|:------|:----------------------------------------------------------------------|
| 0.062 |I need to know the project version at runtime.                         |
| 0.061 | I need to find a way to check loading is not corrupted with old save (tests) |
| 0.060 | I need a viable way to save and load progress.                        |
| 0.06x | **Goal :** I'll need to be able to save and load my progress.         |

## Chapter 5. ##

---

| 0.054 | I need to run the project without eclipse, using only maven. |
|:------|:-------------------------------------------------------------|
| 0.053 | I need to split my project in two halves, one for the game, one for the web impl. |
| 0.052 | I need a code formater. Eclipse needs hints on how I'd rather format it. |
| 0.051 | I need to simplify the debug component model handling, and making it easier to read. |
| 0.050 | I need to be able to build a Debugging bar without detailed markup coding in the page. |
| 0.05x | **Goal :** I'll need to be able to look at my front-end code without throwing up. |

## Chapter 4. ##

---

| 0.043 | I need to add debugging tools (auto clickers and a debugging mode ON/OFF), maybe play a little with models. |
|:------|:------------------------------------------------------------------------------------------------------------|
| 0.042 | I need to make it more fluid as time goes.                                                                  |
| 0.041 | The game starts to take some time to play, i need a comprehensive auto mode, for science.                   |
| 0.040 | Let's add some class about cost holding and handling. I want to have different costs and formulas for each buildings. |
| 0.04x |  **Goal :**   Now is time to do some logic about formula handling (and values)                              |

## Chapter 3. ##

---

| 0.033 |  I also need manual clicking on resources. |
|:------|:-------------------------------------------|
| 0.032 | I'll update the front now, to have a nice visual of what's been done until now. |
| 0.031 | I have an idea where I want to go from here. I need another resource for it, food. I'll come back to visuals later. <br> I need a lot of resources and buildings. And a better handling of those (not by hand I mean). <br>
<tr><td> 0.030 </td><td> I need to have separated resources amounts, not just a massive toString one the biggest container. </td></tr>
<tr><td> 0.03x </td><td> <b>Goal :</b>  Ok, I have something really cool now, and what Wicket provides is OVER 9000 !  <br> I'll smooth up the visual a bit, add the +1 resource mechanics. </td></tr></tbody></table>

## Chapter 2. ##

---

| 0.024 | I'll add some visual content and provide a manual mode, not always automatic mode. |
|:------|:-----------------------------------------------------------------------------------|
| 0.023 | I need to be able to see this as a client side Ajax thread and see the actual incrementing on screen. <br> OMG wicket is wicked ! <br>
<tr><td> 0.022 </td><td> I'll need to consider this game a web app at some point. I'll try "wicket", and add maven support and several cool tools. </td></tr>
<tr><td> 0.021 </td><td> A building costs resources, which are decremented from the total acquired.         </td></tr>
<tr><td> 0.020 </td><td> I actually can't find any other fun resource with the bottle caps so I’ll stick to basics. <br> I'm actually thinking about a viable way to keep this an incrementer game with multiple resources. <br> Each resource has to be clickable, and the player has to choose which one he wants to boost. <br> I'll use wood and metal at first, I’ll see later if I find something else. <br> I'll change the Buildings accordingly too. </td></tr>
<tr><td> 0.02x </td><td> <b>Goal :</b> Let's introduce other resources.                                     </td></tr></tbody></table>

<h2>Chapter 1.</h2>
<hr />
<table><thead><th> 0.014 </th><th> I'll add another building for testing   </th></thead><tbody>
<tr><td> 0.013 </td><td> I'll need to be able to update the increment value. With some levels on the bottle caps. That's what I want in games :p building levels. <br> In fact, a resource shouldn't be able to know how to increment itself. <br> It should be dependent on formulas based on buildings levels incrementing one or several resource. <br> (Several buildings may increment one resource) </td></tr>
<tr><td> 0.012 </td><td> I'll need to add some basic gaming main.. for science.. </td></tr>
<tr><td> 0.011 </td><td> My resource may be incremented, so I’ll have an amount by which it'll be incremented. <br> It will also know the quantity it currently has. I'll add some container too.  </td></tr>
<tr><td> 0.010 </td><td> First I want some data. Something I may manipulate.  A Concept to be incremented. <br> Why not several resources. I'll start with one. (Fallout's bottle caps :p I'll rename later :p) </td></tr>
<tr><td> 0.01x </td><td> <b>Goal :</b> Let's write some basics.  </td></tr>