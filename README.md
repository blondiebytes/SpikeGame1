Game 1 : Spike Game
===================
Fall 2014: CS 203

3.2 Moving Blocks

Your challenge is to develop a game about moving blocks, like Tetris, Columns, 2048, etc. Some real games about blocks are quite complicated, so you may want to choose an abstracted version, like Simple Tetris.

It is up to you what kind of game you make, but it must contain a few elements, which I will describe using Tetris.
-- A field of play where the blocks move.

-- This is the grid where the Tetriminos lay.

-- A set of "live" blocks that are player controlled. (This "set" may contain one block.) This is the falling Tetrimino.

-- A set of "dead" blocks that are no longer player controlled. This is the set of Tetriminos that are resting on the ground due to gravity.

-- A scoring system. In Tetris you get points for filling rows and higher scores for more at the same time.

-- A win or fail state. In Tetris you fail when a Tetrimino would spawn in a resting state.

-- A control mechanism. In Tetris the player can move the live block left and right, rotate it, and drop it to the bottom of the field.

Graphics and control can be a major time sink in game projects. I highly recommend that you drastically simplify things and use either Game Worlds, Blacken, or libjcsi.

You should write a short game manual that describes the rules of your game. You should run this past me so we can agree that the game is complex and interesting enough. You should use the invariants of your game to design testable components. You should be able to build a completely automated version of your game for testing. For example, in my Tetris game, I might parameterize the game over a Tetrimino generator and a Input stream, so that I can test explicit sequences of inputs on Block sequences and ensure that the rules of the game are enforced.

Non-automated tests (i.e. opening up the game and playing) will not be acceptable.

3.2.1 Turn-in and Grading
You should turn in your manual, your source code, a console transcript of your test suite’s execution, and an essay which describes your approach to implementing and ensuring the correctness of your game. Your essay should probably make reference to specific lines in your source code and test transcript.

You will be graded on the persuasiveness of your essay.
