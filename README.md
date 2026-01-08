## 2D Java Game

Simple top‑down 2D tile‑based game written in **Java** using **Swing**.  
You control a player that can walk around a scrolling tile map rendered from text files.

### Preview (Sprites & Tiles)

Some of the graphics used in the game:

- **Player – facing down**

<p align="left">
  <img src="res/Joueur/bas 1.png" width="48">
</p>

- **Player – facing up**
<p align="left">
  <img src="res/Joueur/haut 1.png" width="48">
</p>

- **Grass tiles**

<p align="left">
  <img src="res/Tuiles/Tile Grass/grass47.png" width="48">
</p>

- **Rock tiles**

<p align="left">
  <img src="res/Tuiles/Tile Rocks/tile2.png" width="48">
</p>

### Features

- **Top‑down 2D world**: Tile‑based world with a 50x50 map (`res/Map/map_50x50.txt`, `res/Map/map01.txt`).
- **Smooth movement**: Game loop capped at **60 FPS** in `GamePanel`.
- **Camera / scrolling**: The player stays centered on screen while the world scrolls around them.
- **Sprite animation**: Two‑frame walking animation for each direction (up, down, left, right) loaded in `Joueur`.
- **Collision checking**: Basic tile collision handled via `CollisionChecker`.

### Controls

- **Move up**: `W` or `↑`
- **Move down**: `S` or `↓`
- **Move left**: `A` or `←`
- **Move right**: `D` or `→`

### Project Structure

- **`src/main`**: Core game loop and window
  - `Main.java`: Entry point that creates the window and starts the game.
  - `GamePanel.java`: Main game panel (loop, update, draw, FPS).
  - `KeyHandler.java`: Keyboard input handling.
  - `CollisionChecker.java`: World collision logic.
- **`src/Entités`**:
  - `Entite.java`: Base entity class (position, sprites, collisions).
  - `Joueur.java`: Player logic, input handling, camera, and drawing.
- **`src/Tile`**:
  - `Tile.java`: Tile representation.
  - `TileManager.java`: Loads map files and draws tiles.
- **`res`**:
  - `Joueur/`: Player sprites in 4 directions with 2 frames each.
  - `Tuiles/Tile Grass/`: Grass tile images.
  - `Tuiles/Tile Rocks/`: Rock/obstacle tile images.
  - `Map/`: Text map definitions.

### Requirements

- **Java**: JDK 17+ (earlier versions may work but are not tested).
- **IDE**: IntelliJ IDEA (recommended) or any IDE that supports standard Java projects.

### How to Run

- **In IntelliJ IDEA**
  - Open the project folder `2DJavaGame` as a project.
  - Ensure the `res` folder is marked as a *Resources Root* (or on the classpath).
  - Open `Main.java` (in `src/main`) and run the `main` method.

- **From command line (basic setup)**
  - Navigate to the project root:

```bash
cd 2DJavaGame
javac -d out -sourcepath src src/main/Main.java
java -cp out;res main.Main
```

> On Unix/macOS replace `;` with `:` in the `-cp` value.

### Notes

- All resource loading in `Joueur` and tiles uses paths relative to the classpath (e.g. `/Joueur/bas 1.png`), so make sure the `res` folder is available on the runtime classpath.
- The code and comments are partially written in French (e.g. *Joueur*, *Tuiles*), but the structure is straightforward and easy to extend.

### Sources and Inspirations
A big thanks to @Ryisnow for his series "Building a 2d game in Java" that helped me learn about tiles, sprites, and how to program game logic to this project. He's done a better job than most of my teachers at University


