Overview
This is a simple Tic-Tac-Toe game built using Android Studio for Android devices. It provides a two-player mode (Player 1 vs Player 2) where players take turns placing their markers ('X' or 'O') on a 3x3 grid. The first player to align three of their markers horizontally, vertically, or diagonally wins the game. It also includes a basic single-player mode, where the second player is controlled by the system.
Features
Two-Player Mode: Play with a friend on the same device.
Single-Player Mode: Play against an AI (robot).
Simple User Interface: Intuitive layout for an enjoyable experience.
Score Tracking: Keeps track of the score for each player.
Game Reset: Reset the game after each round
Prerequisites
Android Studio (for running or modifying the app).
Android device or Emulator for testing.
How It Works
The game consists of a 3x3 grid, where players alternately place 'X' and 'O' on empty cells.
Player 1 is assigned 'X' and Player 2 (or the AI) gets 'O'.
The game automatically checks if either player has won after every move. A player wins if they have three of their marks aligned in a row, column, or diagonal.
When a player wins, the game will display a Toast message showing the winner and then reset the game after a brief delay.
