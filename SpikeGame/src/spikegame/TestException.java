package spikegame;

import net.slashie.libjcsi.CharKey;

public class TestException {

    static int checkGameOverLives = 0;
    static int checkIfPositiveScore = 0;
    static int testConstructor = 0;
    static int testCollisionLivesScore = 0;
    static int testReactTickSpikeBalloon = 0;
    static int testAdded = 0;

    public static void testConstructor() throws Exception {
        SpikeGame spikeGame = new SpikeGame();
        if (spikeGame.spike.height != 0 && spikeGame.spike.width != spikeGame.spike.MAX / 2) {
            throw new Exception("FAILURE: The spike does not start in the middle of the screen!");
        }
        if (!spikeGame.balloonDataStruct.isEmpty()) {
            throw new Exception("FAILURE: The balloonDataStruct doesn't start empty!");
        }
        if (spikeGame.livesLabel.lives != 2) {
            throw new Exception("FAILURE: You don't start with 2 LIVES");
        }
        if (spikeGame.scoreLabel.score != 0) {
            throw new Exception("FAILURE: You start with a score");
        }
        testConstructor++;

    }

    public static void checkIfPositiveScore(SpikeGame oSG, SpikeGame nSG) throws Exception {
        if (oSG.scoreLabel.score < 0 || nSG.scoreLabel.score < 0) {
            throw new Exception("Score is not positive");
        }
        checkIfPositiveScore++;
    }

    public static void checkGameOverLives(SpikeGame oSG, SpikeGame nSG) throws Exception {
        if (oSG.gameOver) {
            if (oSG.livesLabel.lives != 0) {
                throw new Exception("Still have lives & gameOver");
            }
        } else {
            if (oSG.livesLabel.lives == 0) {
                throw new Exception("0 Lives & gameNotOver");
            }
            if (nSG.gameOver) {
                if (nSG.livesLabel.lives != 0) {
                    throw new Exception("Still have lives & gameOver");
                }
            } else {
                if (nSG.livesLabel.lives == 0) {
                    throw new Exception("0 Lives & gameNotOver");
                }
            }
        }
        checkGameOverLives++;
    }

    public static void testCollisionLivesScore(SpikeGame oldSpikeGame, SpikeGame newSpikeGame) throws Exception {
        boolean collision = false;
        for (Balloon b : oldSpikeGame.balloonDataStruct) {
            if (b.height == newSpikeGame.spike.height) {
                // ONE OF THESE THINGS ARE TRUE
                collision = true;
                if (((oldSpikeGame.livesLabel.lives - 1) == newSpikeGame.livesLabel.lives)
                        && (oldSpikeGame.scoreLabel.score == newSpikeGame.scoreLabel.score)) {
                } else if (((oldSpikeGame.scoreLabel.score + 5) == newSpikeGame.scoreLabel.score)
                        && (oldSpikeGame.livesLabel.lives == newSpikeGame.livesLabel.lives)) {
                } else {
                    throw new Exception("ERROR! Lives & Score & Collision Messed Up" + oldSpikeGame.scoreLabel.score + " = " + newSpikeGame.scoreLabel.score);
                }
            }
        }
        // PROBLEM
            if (!collision) {
                if (!oldSpikeGame.isEqualToLiScGo(newSpikeGame)) {
                throw new Exception("ERROR! Lives & Score & Collision Equality Messed Up" + oldSpikeGame.scoreLabel.score + " = " + newSpikeGame.scoreLabel.score + " " + 
                        oldSpikeGame.livesLabel.lives + " = " + newSpikeGame.livesLabel.lives);
            }
                
           
        }
        testCollisionLivesScore++;
    }

    public static void testAdded(SpikeGame oldSpikeGame, SpikeGame newSpikeGame, CharKey rnb) throws Exception {
        boolean newBalloon = false;
        // Checking whether something is added 

        for (Balloon nb : newSpikeGame.balloonDataStruct) {
            boolean oldBalloon = false;
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                if (nb.identity == b.identity) {
                    oldBalloon = true;
                }
            }

            if (!oldBalloon) {
                newBalloon = true;
            }
        }

        if (!newBalloon && !oldSpikeGame.spike.isEqualTo(newSpikeGame.spike)) {
            throw new Exception("The new Balloon was not added");
        }

        if (newBalloon && oldSpikeGame.spike.isEqualTo(newSpikeGame.spike)) {
            throw new Exception("A new Balloon was added, but the spike didn't move");
        }
        testAdded++;
    }

    public static void testReactTickSpikeBalloon(SpikeGame oldSpikeGame, SpikeGame newSpikeGame, CharKey rnb) throws Exception {
        int dw = 0;
        if (rnb.isRightArrow()) {
            dw = 1;
        }
        if (rnb.isLeftArrow()) {
            dw = -1;
        }

        if (oldSpikeGame.spike.isEqualTo(newSpikeGame.spike)) {
            dw = 0;
        }

        if ((oldSpikeGame.spike.width + dw) != newSpikeGame.spike.width) {
            throw new Exception("MoveSpike doesn't work: Old: "
                    + oldSpikeGame.spike.width + "New:" + newSpikeGame.spike.width
                    + "dw = " + dw);
        }

        if (dw != 0) {
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                boolean found = false;
                for (Balloon nb : newSpikeGame.balloonDataStruct) {
                    if (b.identity == nb.identity) {
                        // the b.height - 1 should equal the new height
                        if ((b.height - 1) != nb.height) {
                            throw new Exception("Balloons don't move up! B: " + b.height
                                    + " NB: " + nb.height + "ID: " + nb.identity + " Key: " + rnb);
                        } else {
                            found = true;
                            break;
                        }
                    }
                }

                if (!found && (b.height - 1 != 0)) {
                    throw new Exception("The new balloon was not found at" + b.height);
                }
                if (found && (b.height - 1) == 0) {
                    throw new Exception("The removed balloon was found? " + b.height);
                }
            }
        } else {
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                for (Balloon nb : newSpikeGame.balloonDataStruct) {
                    if (b.identity == nb.identity) {
                        if (b.height != nb.height) {
                            throw new Exception("Balloons move up and the spike didn't move");
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        testReactTickSpikeBalloon++;
    }
}
